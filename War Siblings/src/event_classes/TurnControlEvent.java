/** War Siblings
 * TurnControlEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.TurnControlNotifier;

public class TurnControlEvent extends InfoEvent {
	public enum Task {
		START_TURN, END_TURN
	};

	protected Task task;
	protected TurnControlNotifier source;

	public TurnControlEvent(Task t, Object info, TurnControlNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}
	
	public TurnControlNotifier getSource() {
		return this.source;
	}

}
