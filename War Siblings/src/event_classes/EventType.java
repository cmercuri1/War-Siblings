package event_classes;

import java.util.HashMap;
import java.util.Map;

public enum EventType {
	ADD(1), REMOVE(2), GET(3), GOT(4), GET_SPECIAL(5), GOT_SPECIAL(6), ROLL(7), SETUP(8), CLOSE(9), UNDEFINED(10);

	private static final Map<Integer, EventType> BY_VALUE = new HashMap<>();

	static {
		for (EventType m : values()) {
			BY_VALUE.put(m.value, m);
		}
	}

	public final int value;

	private EventType(int value) {
		this.value = value;
	}

	public static EventType valueOfEvent(int value1) {
		return BY_VALUE.get(value1);
	}
}
