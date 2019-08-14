/** War Siblings
 * StarAttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.StarAttributeNotifier;

public class StarAttributeEvent extends InfoEvent {
	public enum Task {
		STAR_ASSIGNED
	};

	protected Task task;
	protected double information;
	protected StarAttributeNotifier source;

	public StarAttributeEvent(Task t, double info, StarAttributeNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Double getInformation() {
		return this.information;
	}

	public StarAttributeNotifier getSource() {
		return this.source;
	}
}
