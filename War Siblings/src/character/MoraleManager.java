package character;

import common_classes.MoraleState;

/**
 * A class for managing the morale state of a character, along with any changes
 * that has on the rest of the character
 */
public class MoraleManager {
	private MoraleState currentMorale;

	public MoraleManager() {
		this.currentMorale = MoraleState.STEADY;
	}

	public MoraleState getCurrentState() {
		return this.currentMorale;
	}
}
