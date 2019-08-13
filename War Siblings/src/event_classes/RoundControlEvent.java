/** War Siblings
 * RoundControlEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class RoundControlEvent extends InfoEvent {
	public enum Task {
		START_ROUND, END_ROUND
	};

	protected Task task;

	public RoundControlEvent(Task t, Object info, Object src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}
}
