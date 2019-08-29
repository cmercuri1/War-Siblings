/** War Siblings
 * Necklace
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import effect_classes.Modifier;

public class ResolveNecklace extends Accessory {

	protected Modifier resolveBoost;

	public ResolveNecklace(String name, double value, String desc, double resolveBoost) {
		super(name, value, desc);
		this.resolveBoost = new Modifier("resolve_Unique", resolveBoost);

		this.temp.add(this.resolveBoost);
	}

}
