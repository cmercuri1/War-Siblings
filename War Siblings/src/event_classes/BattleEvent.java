/** War Siblings
 * BattleEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class BattleEvent extends InfoEvent {
	public enum Task {
		START_BATTLE, END_BATTLE, FAILED_SPECIAL_ROLL
	};

	protected Task task;

	public BattleEvent(Task t, Object info, Object src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

}
