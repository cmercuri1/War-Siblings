/** War Siblings
 * AttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.AttributeNotifier;

public class AttributeEvent extends InfoEvent {
	public enum Task {
		UPDATE
	};

	protected Task task;
	protected double information;
	protected AttributeNotifier source;

	public AttributeEvent(Task t, double info, AttributeNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Double getInformation() {
		return this.information;
	}

	public AttributeNotifier getSource() {
		return this.source;
	}
}
