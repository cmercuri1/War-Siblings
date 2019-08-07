/** War Siblings
 * FatigueAttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class FatigueAttributeEvent extends InfoEvent {
	public enum Task {
		FULL_FATIGUE
	};

	protected Task task;
	protected double information;

	public FatigueAttributeEvent(Task t, double info, Object src) {
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
