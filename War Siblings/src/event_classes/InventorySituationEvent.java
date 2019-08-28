/** War Siblings
 * InventorySituationEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.InventorySituationNotifier;

public class InventorySituationEvent extends InfoEvent {
	public enum Task {
		MELEE, UNARMED, RANGED
	};

	protected Task task;
	protected InventorySituationNotifier source;

	public InventorySituationEvent(Task t, Object info, InventorySituationNotifier src) {
		super(info, src);
		this.task = t;
		this.source = src;
	}

	public Task getTask() {
		return this.task;
	}

	public InventorySituationNotifier getSource() {
		return this.source;
	}

}
