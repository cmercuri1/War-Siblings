/** War Siblings
 * CombatEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class CombatEvent extends InfoEvent {
	public enum Task {
		HIT, MISS
	};

	protected Task task;

	public CombatEvent(Task t, Object info, Object src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

}
