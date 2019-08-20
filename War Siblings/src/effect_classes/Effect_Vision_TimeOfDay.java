/** War Siblings
 * Vision_TimeOfDay
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import storage_classes.BattleConditions.TimeOfDay;

public class Effect_Vision_TimeOfDay extends Effect_Modifier_Battle_Situational {

	public Effect_Vision_TimeOfDay(TimeOfDay time, double modValue) {
		super(null, null, time, new Modifier("vision", modValue, false, false, false));
	}

}
