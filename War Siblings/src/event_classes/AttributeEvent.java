/** War Siblings
 * AttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class AttributeEvent extends InfoEvent {
	public enum Task {
		UPDATE
	};

	protected Task task;
	protected double information;

	public AttributeEvent(Task t, double info, Object src) {
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
