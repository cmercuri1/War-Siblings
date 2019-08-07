/** War Siblings
 * HitpointAttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class HitpointAttributeEvent extends InfoEvent {
	public enum Task {
		NO_HP
	};

	protected Task task;
	protected double information;

	public HitpointAttributeEvent(Task t, double info, Object src) {
		super(info, src);
		this.task = t;
	}

	public Double getInformation() {
		return this.information;
	}

	public Task getTask() {
		return this.task;
	}
}
