/** War Siblings
 * MultiValueAttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.MultiValueAttributeNotifier;

public class MultiValueAttributeEvent extends InfoEvent {
	public enum Task {
		UPDATE_CURRENT
	};

	protected Task task;
	protected double information;
	protected MultiValueAttributeNotifier source;

	public MultiValueAttributeEvent(Task t, double info, MultiValueAttributeNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Double getInformation() {
		return this.information;
	}

	public MultiValueAttributeNotifier getSource() {
		return this.source;
	}
}
