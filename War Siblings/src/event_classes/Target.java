package event_classes;

import java.util.HashMap;
import java.util.Map;

public enum Target {
	ABILITY(1), ATTRIBUTE(2),MORALE(3), BATTLE(4), INVENTORY(5), UNDEFINED(6);

	private static final Map<Integer, Target> BY_VALUE = new HashMap<>();

	static {
		for (Target t : values()) {
			BY_VALUE.put(t.value, t);
		}
	}

	public final int value;

	private Target(int value) {
		this.value = value;
	}

	public static Target valueOfTarget(int value1) {
		return BY_VALUE.get(value1);
	}

}
