/** War Siblings
 * TurnControlEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class TurnControlEvent extends InfoEvent {
	public enum Task {
		START_TURN, END_TURN
	};

	protected Task task;

	public TurnControlEvent(Task t, Object info, Object src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

}
