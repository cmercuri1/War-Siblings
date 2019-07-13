package character;

import common_classes.MoraleState;
import common_classes.Observer;
import common_classes.Observeree;
import event_classes.EventMorale;

/**
 * A class for managing the morale state of a character, along with any changes
 * that has on the rest of the character
 */
public class MoraleManager extends Observeree {
	private MoraleState currentMorale;

	public MoraleManager(Observer o) {
		this.setUpObservers();
		this.registerObserver(o);
		this.changeState(MoraleState.STEADY);
	}

	public MoraleState getCurrentState() {
		return this.currentMorale;
	}

	public void changeState(MoraleState state) {
		MoraleState[] temp = {this.currentMorale, state};
		this.notifyObservers(new EventMorale("Changed Morale", temp));
		this.currentMorale = state;
	}
	
	public void display() {
		System.out.println("This character is currently: " + this.currentMorale);
	}
}
