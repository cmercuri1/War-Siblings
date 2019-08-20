/** War Siblings
 * Damage_WeaponType
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.InventorySituationEvent;
import event_classes.InventorySituationEvent.Task;
import listener_interfaces.InventorySituationListener;

public abstract class Effect_Inventory_Situation extends Effect_Triggered implements InventorySituationListener {
	protected Task specificTask;
	
	public Effect_Inventory_Situation(Task match) {
		this.specificTask = match;
	}

	@Override
	public void onInventorySituationEvent(InventorySituationEvent i) {
		if (i.getTask().equals(specificTask)) {
			this.triggerStart();
		} else {
			this.triggerEnd();
		}
	}

}
