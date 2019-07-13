package character;

import common_classes.MoraleState;
import common_classes.Observer;
import common_classes.Observeree;

/**
 * A class for managing the morale state of a character, along with any changes
 * that has on the rest of the character
 */
public class MoraleManager extends Observeree {
	private MoraleState currentMorale;

	public MoraleManager(Observer o) {
		this.setUpObservers();
		this.registerObserver(o);
		this.currentMorale = MoraleState.STEADY;
	}

	public MoraleState getCurrentState() {
		return this.currentMorale;
	}
	
	public void changeState() {
		
	}
}
