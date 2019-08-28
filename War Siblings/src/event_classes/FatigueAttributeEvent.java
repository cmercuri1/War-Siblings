/** War Siblings
 * FatigueAttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.FatigueAttributeNotifier;

public class FatigueAttributeEvent extends InfoEvent {
	public enum Task {
		FULL_FATIGUE
	};

	protected Task task;
	protected double information;
	protected FatigueAttributeNotifier source;

	public FatigueAttributeEvent(Task t, double info, FatigueAttributeNotifier src) {
		super(info, src);
		this.information = info;
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Double getInformation() {
		return this.information;
	}

	public FatigueAttributeNotifier getSource() {
		return this.source;
	}
}
