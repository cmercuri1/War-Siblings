/** War Siblings
 * InventoryEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import items.Item;
import notifier_interfaces.InventoryNotifier;

public class InventoryEvent extends InfoEvent {
	public enum Task {
		RETURN_INVENTORY
	};

	protected Task task;
	protected Item information;
	protected InventoryNotifier source;

	public InventoryEvent(Task t, Item info, InventoryNotifier src) {
		super(info, src);
		this.information = info;
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Item getInformation() {
		return this.information;
	}

	public InventoryNotifier getSource() {
		return this.source;
	}
}
