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

	public void makePositiveCheck(double additionalModifier) {
		if (this.makeCheck(this.optimistModifier + additionalModifier)) {
			if (this.currentMorale.getValue() < MoraleState.CONFIDENT.getValue()) {
				if (!(this.currentMorale.equals(MoraleState.STEADY) && this.isInsecure)) {
					this.changeState(MoraleState.valueOfMoraleValue(currentMorale.getValue() + 1));
				}
			}
		}
	}

	public void makeNegativeCheck(double additionalModifier) {
		if (!this.makeCheck(this.pessimistModifier + additionalModifier)) {
			if (this.currentMorale.getValue() > MoraleState.FLEEING.getValue()) {
				this.changeState(MoraleState.valueOfMoraleValue(currentMorale.getValue() - 1));
			}
		}
	}

	public void makeSpecialCheck(double additionalModifier) {
		if (!this.makeCheck(this.specialModifier + this.pessimistModifier + additionalModifier)) {
			this.notifyObservers(new EventObject(Target.BATTLE, EventType.FAILED_SPECIAL_ROLL, null, null));
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
	public void onEventHappening(EventObject event) {
		switch (event.getTarget()) {
		case MORALE:
			switch (event.getTask()) {
			case ADD:
				this.setEffect((Effect) event.getInformation());
				break;
			case REMOVE:
				this.removeEffect((Effect) event.getInformation());
				break;
			case START_BATTLE:
				this.setMorale();
				break;
			case END_BATTLE:
				this.removeMorale();
				break;
			case ROLL_POSITIVE:
				this.makePositiveCheck((double) event.getInformation());
				break;
			case ROLL_NEGATIVE:
				this.makeNegativeCheck((double) event.getInformation());
				break;
			case ROLL_SPECIAL:
				this.makeSpecialCheck((double) event.getInformation());
				break;
			default:
				break;
			}
			break;
		case UNDEFINED:
			switch (event.getTask()) {
			case GOT:
				Object[] temp = (Object[]) event.getInformation();
				this.setResolve((double) temp[1]);
				break;
			default:
				break;
			}
		default:
			break;
		}
		
	}

}
