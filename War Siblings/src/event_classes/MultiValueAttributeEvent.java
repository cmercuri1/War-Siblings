/** War Siblings
 * MultiValueAttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class MultiValueAttributeEvent extends InfoEvent {
	public enum Task {
		UPDATE_CURRENT
	};

	protected Task task;
	protected double information;

	public MultiValueAttributeEvent(Task t, double info, Object src) {
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
