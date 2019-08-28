/** War Siblings
 * MoraleEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.MoraleRollOutcomeNotifier;

public class MoraleRollOutcomeEvent extends InfoEvent {
	public enum Task {
		POSITIVE_ROLL_SUCCESS, POSITIVE_ROLL_FAIL, NEGATIVE_ROLL_SUCCESS, NEGATIVE_ROLL_FAIL, SPECIAL_ROLL_SUCCESS,
		SPECIAL_ROLL_FAIL,
	};

	protected Task task;
	protected MoraleRollOutcomeNotifier source;

	public MoraleRollOutcomeEvent(Task t, Object info, MoraleRollOutcomeNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public MoraleRollOutcomeNotifier getSource() {
		return this.source;
	}
}
