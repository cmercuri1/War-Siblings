/** War Siblings
 * InventoryEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import items.Item;

public class InventoryEvent extends InfoEvent {
	public enum Task {
		RETURN_INVENTORY
	};

	protected Task task;
	protected Item information;

	public InventoryEvent(Task t, Item info, Object src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Item getInformation() {
		return this.information;
	}
}
