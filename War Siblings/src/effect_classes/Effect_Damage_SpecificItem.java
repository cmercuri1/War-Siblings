/** War Siblings
 * Damage_SpecificItem
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.InventorySituationEvent.Task;

public class Effect_Damage_SpecificItem extends Effect_Modifier_Inventory_Situation {

	public Effect_Damage_SpecificItem(Task match, double modValue) {
		super(match, "damage_Percent_Unique", modValue);
	}

}
