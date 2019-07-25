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
	ADD, REMOVE, GET, GOT, GET_OTHER, GOT_OTHER, ROLL, START_BATTLE, END_BATTLE, START_TURN, END_TURN, HIT, MISS,
	UNDEFINED, UPDATE, BROKEN, NO_FATIGUE, NO_HP, LEVEL_UP, APPLY_LEVEL_UP, HEALED, FAILED_SPECIAL_ROLL, ROLL_POSITIVE,
	ROLL_NEGATIVE, ROLL_SPECIAL, CHANGE_RIGHT, CHANGE_LEFT, CHANGE_HEAD, CHANGE_BODY, CHANGE_2H, RETURN_INVENTORY, DROP;
}
