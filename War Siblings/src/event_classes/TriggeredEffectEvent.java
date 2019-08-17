/** War Siblings
 * TriggeredEffectEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.TriggeredEffectNotifier;

public class TriggeredEffectEvent extends InfoEvent {

	public enum Task {
		DAMAGE, IMPEDE, APPLY, REMOVE
	};

	protected Task task;
	protected TriggeredEffectNotifier source;

	public TriggeredEffectEvent(Task t, Object info, TriggeredEffectNotifier src) {
		super(info, src);
		this.task = t;
		this.source = src;
	}

	public Task getTask() {
		return this.task;
	}

	public TriggeredEffectNotifier getSource() {
		return this.source;
	}
}
