/** War Siblings
 * Vision_TimeOfDay
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes_specific;

import effect_classes.Effect_Modifier_Battle_Situational;
import effect_classes.Modifier;
import storage_classes.BattleConditions.TimeOfDay;

public class Vision_TimeOfDay extends Effect_Modifier_Battle_Situational {

	public Vision_TimeOfDay(TimeOfDay time, double modValue) {
		super(null, null, time, new Modifier("vision", modValue, false, false, false));
	}

}
