/** War Siblings
 * LevelUpAttribute
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class LevelUpAttributeEvent extends InfoEvent {
	public enum Task {
		LEVEL_UP
	};

	protected Task task;
	protected double information;

	public LevelUpAttributeEvent(Task t, double info, Object src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Double getInformation() {
		return this.information;
	}
}
