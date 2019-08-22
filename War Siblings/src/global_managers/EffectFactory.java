/** War Siblings
 * EffectFactory
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import effect_classes.Effect;
import effect_classes.Effect_AllyDeathIgnore;
import effect_classes.Effect_Bleed;
import effect_classes.Effect_Damage_SpecificItem;
import effect_classes.Effect_Deathwish;
import effect_classes.Effect_Determined;
import effect_classes.Effect_DoT;
import effect_classes.Effect_Insecure;
import effect_classes.Effect_Irrational;
import effect_classes.Effect_Modifier;
import effect_classes.Effect_Morale_Roll;
import effect_classes.Effect_Poison;
import effect_classes.Effect_Resolve_vsEnemy;
import effect_classes.Effect_Stage_2;
import effect_classes.Effect_Vision_TimeOfDay;
import event_classes.InventorySituationEvent.Task;
import storage_classes.BattleConditions.Foes;
import storage_classes.BattleConditions.TimeOfDay;
import storage_classes.Effect_Storage;

public class EffectFactory {

	public Effect getEffect(String effectName, double effectValue) {
		if (effectName == null)
			return null;
		Effect_Storage temp = GlobalManager.effects.search(effectName);

		if (temp.getEffectType().equalsIgnoreCase("Effect_Modifier"))
			return new Effect_Modifier(effectName, effectValue);

		if (temp.getEffectType().equalsIgnoreCase("Effect_Damage_SpecificItem")) {
			if (effectName.contains("_Melee"))
				return new Effect_Damage_SpecificItem(Task.MELEE, effectValue);
			if (effectName.contains("_Unarmed"))
				return new Effect_Damage_SpecificItem(Task.UNARMED, effectValue);
		}

		if (temp.getEffectType().equalsIgnoreCase("Effect_Irrational"))
			return new Effect_Irrational(effectValue);

		if (temp.getEffectType().equalsIgnoreCase("Effect_Morale_Roll")) {
			if (effectName.contains("_Optimist"))
				return new Effect_Morale_Roll("POSITIVE", effectValue);
			if (effectName.contains("_Pessimist"))
				return new Effect_Morale_Roll("NEGATIVE", effectValue);
			if (effectName.contains("_Special"))
				return new Effect_Morale_Roll("SPECIAL", effectValue);
		}

		if (temp.getEffectType().equalsIgnoreCase("Effect_Resolve_vsEnemy")) {
			if (effectName.contains("_Beasts"))
				return new Effect_Resolve_vsEnemy(Foes.BEASTS, effectValue);
			if (effectName.contains("_Greenskins"))
				return new Effect_Resolve_vsEnemy(Foes.GREENSKINS, effectValue);
			if (effectName.contains("_Undead"))
				return new Effect_Resolve_vsEnemy(Foes.UNDEAD, effectValue);
		}

		if (temp.getEffectType().equalsIgnoreCase("Effect_Stage_2"))
			return new Effect_Stage_2(effectName);

		if (temp.getEffectType().equalsIgnoreCase("Effect_Vision_TimeOfDay"))
			if (effectName.contains("_Night"))
				return new Effect_Vision_TimeOfDay(TimeOfDay.NIGHT, effectValue);

		return null;
	}
	
	public Effect getEffect(String effectName) {
		if (effectName == null)
			return null;
		
		Effect_Storage temp = GlobalManager.effects.search(effectName);

		if (temp.getEffectType().equalsIgnoreCase("Effect_AllyDeathIgnore"))
			return new Effect_AllyDeathIgnore();

		if (temp.getEffectType().equalsIgnoreCase("Effect_Deathwish"))
			return new Effect_Deathwish();
		
		if (temp.getEffectType().equalsIgnoreCase("Effect_Determined"))
			return new Effect_Determined();
		
		if (temp.getEffectType().equalsIgnoreCase("Effect_Insecure"))
			return new Effect_Insecure();
		
		return null;
	}

	public Effect_DoT getEffect(String effectName, double duration, double damage) {
		if (effectName == null)
			return null;
		Effect_Storage temp = GlobalManager.effects.search(effectName);

		if (temp.getEffectType().equalsIgnoreCase("Effect_Bleed"))
			return new Effect_Bleed(duration, damage);

		if (temp.getEffectType().equalsIgnoreCase("Effect_Poison"))
			return new Effect_Poison(duration, damage);

		return null;
	}

}
