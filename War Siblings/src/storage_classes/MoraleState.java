/** War Siblings
 * MoraleState enum
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import java.util.HashMap;
import java.util.Map;

/**
 * An Enum used to differentiate between all the possible states a character's
 * morale can be in, is then used to get relevant morale ability information
 */
public enum MoraleState {
	FLEEING(0), BREAKING(1), WAVERING(2), STEADY(3), CONFIDENT(4), UNBREAKABLE(999);

	private static final Map<Integer, MoraleState> BY_VALUE = new HashMap<>();

	static {
		for (MoraleState m : values()) {
			BY_VALUE.put(m.value, m);
		}
	}

	private final int value;

	private MoraleState(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static MoraleState valueOfMoraleValue(int value) {
		return BY_VALUE.get(value);
	}
}
