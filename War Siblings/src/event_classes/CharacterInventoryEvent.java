/** War Siblings
 * InventoryEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import items.Item;
import notifier_interfaces.CharacterInventoryNotifier;

public class CharacterInventoryEvent extends InfoEvent {
	public enum Task {
		CHANGE_BODY, REMOVE_BODY, CHANGE_HEAD, REMOVE_HEAD, CHANGE_LEFT, REMOVE_LEFT, CHANGE_RIGHT, REMOVE_RIGHT
	};

	protected Task task;
	protected Item information;
	protected CharacterInventoryNotifier source;

	public CharacterInventoryEvent(Task t, Item info, CharacterInventoryNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Item getInformation() {
		return this.information;
	}

	public CharacterInventoryNotifier getSource() {
		return this.source;
	}

}
