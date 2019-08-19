/** War Siblings
 * Resolve_vs_Enemy
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes_specific;

import effect_classes.Effect_Modifier_Battle_Situational;
import effect_classes.Modifier;
import storage_classes.BattleConditions.Foes;

public class Resolve_vsEnemy extends Effect_Modifier_Battle_Situational {

	public Resolve_vsEnemy(Foes foes, double modValue) {
		super(null, foes, null, new Modifier("resolve", modValue, false, false, false));
	}

}
