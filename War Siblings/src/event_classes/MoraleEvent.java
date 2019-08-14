/** War Siblings
 * MoraleEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.MoraleNotifier;

public class MoraleEvent extends InfoEvent {
	public enum Task {
		POSITIVE_ROLL_SUCCESS, POSITIVE_ROLL_FAIL, NEGATIVE_ROLL_SUCCESS, NEGATIVE_ROLL_FAIL, SPECIAL_ROLL_SUCCESS,
		SPECIAL_ROLL_FAIL,
	};

	protected Task task;
	protected MoraleNotifier source;

	public MoraleEvent(Task t, Object info, MoraleNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public MoraleNotifier getSource() {
		return this.source;
	}
}
