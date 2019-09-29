/** War Siblings
 * Effect_Inventory_Situation_Multiple
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.InventorySituationEvent;
import event_classes.InventorySituationEvent.Task;
import listener_interfaces.InventorySituationListener;
import storage_classes.ArrayList;

public abstract class Effect_Inventory_Situation_Multiple extends Effect_Triggered
		implements InventorySituationListener {
	protected ArrayList<Task> specificTasks;

	public Effect_Inventory_Situation_Multiple(Task... match) {
		this.specificTasks = new ArrayList<Task>();
		for (int i = 0; i < match.length; i++) {
			this.specificTasks.add(match[i]);
		}
	}

	@Override
	public void onInventorySituationEvent(InventorySituationEvent i) {
		if (this.specificTasks.contains(i.getTask())) {
			this.triggerStart();
		} else {
			this.triggerEnd();
		}
	}

}
