/** War Siblings
 * Necklace
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import effect_classes.Modifier;
import storage_classes.ArrayList;

public class ResolveNecklace extends Accessory {

	protected Modifier resolveBoost;

	public ResolveNecklace(String name, double value, String desc, double resolveBoost) {
		super(name, value, desc);
		this.resolveBoost = new Modifier("resolve_Unique", resolveBoost);
	}

	@Override
	public ArrayList<Modifier> onEquipSituation() {
		ArrayList<Modifier> temp = new ArrayList<Modifier>();

		temp.add(this.resolveBoost);
		
		return temp;
	}
}
