/** War Siblings
 * HitpointAttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.HitpointAttributeNotifier;

public class HitpointAttributeEvent extends InfoEvent {
	public enum Task {
		NO_HP
	};

	protected Task task;
	protected double information;
	protected HitpointAttributeNotifier source;

	public HitpointAttributeEvent(Task t, double info, HitpointAttributeNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Double getInformation() {
		return this.information;
	}

	public Task getTask() {
		return this.task;
	}

	public HitpointAttributeNotifier getSource() {
		return this.source;
	}
}
