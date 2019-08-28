/** War Siblings
 * CombatEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.CombatNotifier;

public class CombatEvent extends InfoEvent {
	public enum Task {
		HIT, MISS
	};

	protected Task task;
	protected CombatNotifier source;

	public CombatEvent(Task t, Object info, CombatNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public CombatNotifier getSource() {
		return this.source;
	}

}
