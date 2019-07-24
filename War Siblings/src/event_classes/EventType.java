/** War Siblings
 * EventType enum
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

/**
 * An enum used to tell EventObjects what their role is when they arrive at the
 * desired target
 */
public enum EventType {
	ADD, REMOVE, GET, GOT, GET_OTHER, GOT_OTHER, ROLL, START, END, HIT, MISS, UNDEFINED, UPDATE, BROKEN, NO_FATIGUE, NO_HP, LEVEL_UP, HEALED;
}
