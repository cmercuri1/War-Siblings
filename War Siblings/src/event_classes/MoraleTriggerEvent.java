/** War Siblings
 * MoraleTriggerEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.MoraleTriggerNotifier;

public class MoraleTriggerEvent extends InfoEvent {
	public enum Task {
		ALLY_DEATH, INJURY, WOUNDED, ENEMY_MOVEMENT, ALLY_FAIL_CHECK
	};

	protected Task task;
	protected MoraleTriggerNotifier source;

	public MoraleTriggerEvent(Task task, Object info, MoraleTriggerNotifier src) {
		super(info, src);
		this.task = task;
		this.source = src;
	}

	public Task getTask() {
		return this.task;
	}

	public MoraleTriggerNotifier getSource() {
		return this.source;
	}

}
