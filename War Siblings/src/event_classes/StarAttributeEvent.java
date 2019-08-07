/** War Siblings
 * StarAttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class StarAttributeEvent extends InfoEvent {
	public enum Task {
		STAR_ASSIGNED
	};

	protected Task task;
	protected double information;

	public StarAttributeEvent(Task t, double info, Object src) {
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
