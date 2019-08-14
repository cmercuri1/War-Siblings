/** War Siblings
 * RoundControlEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.RoundControlNotifier;

public class RoundControlEvent extends InfoEvent {
	public enum Task {
		START_ROUND, END_ROUND
	};

	protected Task task;
	protected RoundControlNotifier source;

	public RoundControlEvent(Task t, Object info, RoundControlNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public RoundControlNotifier getSource() {
		return this.source;
	}
}
