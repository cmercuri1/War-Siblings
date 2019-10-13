/** War Siblings
 * EquipItem
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import effect_classes.Modifier;
import storage_classes.ArrayList;

public interface Equipable {
	ArrayList<Modifier> onEquipSituation();
	ArrayList<Modifier> onBagSituation();
}
