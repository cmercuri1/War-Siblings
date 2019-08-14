/** War Siblings
 * MoraleEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.MoraleRollNotifier;

public class MoraleRollEvent extends InfoEvent {
	public enum Task {
		ROLL_POSITIVE, ROLL_NEGATIVE, ROLL_SPECIAL
	};

	protected Task task;
	protected double information;
	protected MoraleRollNotifier source;

	public MoraleRollEvent(Task t, double info, MoraleRollNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Double getInformation() {
		return this.information;
	}

	public MoraleRollNotifier getSource() {
		return this.source;
	}
}
