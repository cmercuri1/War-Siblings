/** War Siblings
 * MoraleManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.GenericObservee;
import event_classes.Observer;
import event_classes.Target;
import global_managers.GlobalManager;
import storage_classes.Effect;
import storage_classes.MoodAttribute;
import storage_classes.MoraleState;

/**
 * A class for managing the morale state of a character, along with any changes
 * that has on the rest of the character
 */
public class MoraleManager extends GenericObservee implements Observer {
	private static double DEFAULTMORALE = 6;

	private MoraleState currentMorale;

	private MoodAttribute mood;

	private double startingMorale;
	private double optimistModifier;
	private double pessimistModifier;
	private double specialModifier;
	private double currentResolve;

	private boolean isIrrational;
	private boolean isInsecure;
	private boolean isDetermined;

	public MoraleManager(Observer o) {
		this.setUpObservers();
		this.registerObserver(o);

		this.mood = new MoodAttribute(50, this);

		this.startingMorale = DEFAULTMORALE;
		this.optimistModifier = 0;
		this.pessimistModifier = 0;
		this.specialModifier = 0;

		this.isIrrational = false;
		this.isInsecure = false;
		this.isDetermined = false;
	}

	/** Needs to be called after abilities are set up */
	public void setMorale() {
		double temp;
		double chanceRoll;

		if ((this.mood.getCurrentMood().getBestMoraleState() == MoraleState.CONFIDENT.getValue())
				&& (this.isDetermined)) {
			chanceRoll = 100;
		} else {
			chanceRoll = this.mood.getCurrentMood().getBestMoralechance();
		}

		if (GlobalManager.d100Roll() > chanceRoll) {
			temp = this.mood.getCurrentMood().getBestMoraleState() - 1;
		} else {
			temp = this.mood.getCurrentMood().getBestMoraleState();
		}

		if (temp > this.startingMorale) {
			this.changeState(MoraleState.valueOfMoraleValue((int) this.startingMorale));
		} else {
			this.changeState(MoraleState.valueOfMoraleValue((int) temp));
		}
	}

	public void removeMorale() {
		this.changeState(null);
	}

	public MoraleState getCurrentState() {
		return this.currentMorale;
	}

	public void makePositiveCheck() {
		if (this.makeCheck(this.optimistModifier)) {
			if (this.currentMorale.getValue() < MoraleState.CONFIDENT.getValue()) {
				if (!(this.currentMorale.equals(MoraleState.STEADY) && this.isInsecure)) {
					this.changeState(MoraleState.valueOfMoraleValue(currentMorale.getValue() + 1));
				}
			}
		}
	}

	public void makeNegativeCheck() {
		if (!this.makeCheck(this.pessimistModifier)) {
			if (this.currentMorale.getValue() > MoraleState.FLEEING.getValue()) {
				this.changeState(MoraleState.valueOfMoraleValue(currentMorale.getValue() - 1));
			}
		}
	}

	public void makeSpecialCheck() {
		if (!this.makeCheck(this.specialModifier + this.pessimistModifier)) {

		}
	}

	private boolean makeCheck(Double modifier) {
		this.getResolve();
		double temp = 0;

		if (this.isIrrational) {
			if (GlobalManager.d100Roll() <= 50) {
				temp = -10;
			} else {
				temp = 10;
			}
		}

		int roll = GlobalManager.d100Roll();

		if (roll > (this.currentResolve += temp + modifier)) {
			System.out.println("Failed: rolled: " + roll + ", needed: " + this.currentResolve);
			return false;
		} else {
			System.out.println("Success: rolled: " + roll + ", needed: " + this.currentResolve);
			return true;
		}
	}

	private void getResolve() {
		this.notifyObservers(new EventObject(Target.ATTRIBUTE, EventType.GET, "resolve", this));
	}

	public void setResolve(double resolve) {
		this.currentResolve = resolve;
	}

	public void changeMood(Effect e) {
		this.mood.addModifier(e.getModifier());
	}

	public void setEffect(Effect e) {
		if (e.getName().equals("Morale_Optimist")) {
			this.optimistModifier = e.getValue();
		} else if (e.getName().equals("Morale_Pessimist")) {
			this.pessimistModifier = e.getValue();
		} else if (e.getName().equals("Morale_Special")) {
			this.specialModifier = e.getValue();
		} else if (e.getName().equals("Morale_Irrational")) {
			this.isIrrational = true;
		} else if (e.getName().equals("Morale_Insecure")) {
			this.isInsecure = true;
		} else if (e.getName().equals("Morale_Determined")) {
			this.isDetermined = true;
		} else if (e.getName().equals("Morale_Starting")) {
			this.startingMorale = e.getValue();
		}
	}

	public void removeEffect(Effect e) {
		if (e.getName().equals("Morale_Optimist")) {
			this.optimistModifier = 0;
		} else if (e.getName().equals("Morale_Pessimist")) {
			this.pessimistModifier = 0;
		} else if (e.getName().equals("Morale_Special")) {
			this.specialModifier = 0;
		} else if (e.getName().equals("Morale_Irrational")) {
			this.isIrrational = false;
		} else if (e.getName().equals("Morale_Insecure")) {
			this.isInsecure = false;
		} else if (e.getName().equals("Morale_Determined")) {
			this.isDetermined = false;
		} else if (e.getName().equals("Morale_Starting")) {
			this.startingMorale = DEFAULTMORALE;
		}
	}

	private void changeState(MoraleState state) {
		MoraleState[] temp = { this.currentMorale, state };
		this.notifyObservers(new EventObject(Target.UNDEFINED, EventType.UNDEFINED, temp, null));

		try {
			this.notifyObservers(new EventObject(Target.ABILITY, EventType.REMOVE,
					GlobalManager.morale.getMoraleAbility(this.currentMorale), null));
		} catch (NullPointerException nu) {

		}
		try {
			this.notifyObservers(
					new EventObject(Target.ABILITY, EventType.ADD, GlobalManager.morale.getMoraleAbility(state), null));
		} catch (NullPointerException nu) {

		}

		this.currentMorale = state;
	}

	public void display() {
		System.out.println("This character's mood is currently " + this.mood.getCurrentMood().toString());
		System.out.println("This character is currently: " + this.currentMorale);
	}

	@Override
	public void onEventHappening(EventObject information) {
		switch (information.getTask()) {
		case ADD:
			this.setEffect((Effect) information.getInformation());
			break;
		case REMOVE:
			this.removeEffect((Effect) information.getInformation());
			break;
		case GOT:
			this.setResolve((double) information.getInformation());
			break;
		case START:
			this.setMorale();
			break;
		case END:
			this.removeMorale();
			break;
		default:
			break;
		}
	}

}
