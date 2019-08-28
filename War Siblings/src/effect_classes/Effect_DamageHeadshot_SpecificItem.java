/** War Siblings
 * DamageHeadshot_SpecificItem
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.InventorySituationEvent.Task;

public class Effect_DamageHeadshot_SpecificItem extends Effect_Modifier_Inventory_Situation {

	public Effect_DamageHeadshot_SpecificItem(Task match, double modValue) {
		super(match, "damageHeadshot_Percent_Unique", modValue);
	}

}
