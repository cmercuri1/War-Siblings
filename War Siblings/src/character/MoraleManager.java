package character;

import common_classes.Effect;
import common_classes.MoraleState;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.GenericObservee;
import event_classes.Observer;
import event_classes.Target;
import global_managers.GlobalManager;

/**
 * A class for managing the morale state of a character, along with any changes
 * that has on the rest of the character
 */
public class MoraleManager extends GenericObservee implements Observer {
	private MoraleState currentMorale;

	private double optimistModifier;
	private double pessimistModifier;
	private double specialModifier;

	private double currentResolve;

	private boolean isIrrational;
	private boolean isInsecure;

	public MoraleManager(Observer o) {
		this.setUpObservers();
		this.registerObserver(o);

		this.optimistModifier = 0;
		this.pessimistModifier = 0;
		this.specialModifier = 0;
		this.isIrrational = false;
		this.isInsecure = false;
	}

	/** Needs to be called after abilities are set up */
	public void setUp() {
		this.changeState(MoraleState.STEADY);
	}

	public MoraleState getCurrentState() {
		return this.currentMorale;
	}

	public void makePositiveCheck() {
		if (this.makeCheck(this.optimistModifier)) {
			if (this.currentMorale.getValue() < MoraleState.CONFIDENT.getValue()) {
				this.changeState(MoraleState.valueOfMoraleValue(currentMorale.getValue() + 1));
				if (this.isInsecure && this.currentMorale.equals(MoraleState.CONFIDENT)) {
					this.changeState(MoraleState.valueOfMoraleValue(currentMorale.getValue() - 1));
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
		this.notifyObservers(
				new EventObject(Target.ABILITY, EventType.ADD, GlobalManager.morale.getMoraleAbility(state), null));
		this.currentMorale = state;
	}

	public void display() {
		System.out.println("This character is currently: " + this.currentMorale);
	}

	@Override
	public void onEventHappening(EventObject information) {
		switch (information.getTask().value) {
		case 1:
			this.setEffect((Effect) information.getInformation());
			break;
		case 2:
			this.removeEffect((Effect) information.getInformation());
			break;
		case 4:
			this.setResolve((double) information.getInformation());
			break;
		}
	}

}
