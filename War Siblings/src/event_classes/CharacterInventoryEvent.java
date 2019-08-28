/** War Siblings
 * InventoryEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import items.EquipItem;
import notifier_interfaces.CharacterInventoryNotifier;

public class CharacterInventoryEvent extends InfoEvent {
	public enum Task {
		CHANGE_BODY, REMOVE_BODY, CHANGE_HEAD, REMOVE_HEAD, CHANGE_LEFT, REMOVE_LEFT, CHANGE_RIGHT, REMOVE_RIGHT,
		REMOVE_ALL
	};

	protected Task task;
	protected EquipItem information;
	protected CharacterInventoryNotifier source;

	public CharacterInventoryEvent(Task t, EquipItem info, CharacterInventoryNotifier src) {
		super(info, src);
		this.information = info;
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public EquipItem getInformation() {
		return this.information;
	}

	public CharacterInventoryNotifier getSource() {
		return this.source;
	}

}
