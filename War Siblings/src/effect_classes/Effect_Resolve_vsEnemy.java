/** War Siblings
 * Resolve_vs_Enemy
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import storage_classes.BattleConditions.Foes;

public class Effect_Resolve_vsEnemy extends Effect_Modifier_Battle_Situational {

	public Effect_Resolve_vsEnemy(Foes foe, double modValue) {
		super(null, foe, null, new Modifier("resolve", modValue, false, false, false));
	}

}
