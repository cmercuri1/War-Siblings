/** War Siblings
 * Effect_Modifier_Battle_Situational
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.TriggeredEffectEvent;
import storage_classes.BattleConditions.Battlefield;
import storage_classes.BattleConditions.Foes;
import storage_classes.BattleConditions.TimeOfDay;

public class Effect_Modifier_Battle_Situational extends Effect_Battle_Situational {
	protected Modifier mod;

	public Effect_Modifier_Battle_Situational(Battlefield battlefield, Foes foes, TimeOfDay time, String modName,
			double modValue) {
		super(battlefield, foes, time);
		this.mod = new Modifier(modName, modValue);
	}

	public Effect_Modifier_Battle_Situational(Battlefield battlefield, Foes foes, TimeOfDay time, Modifier mod) {
		super(battlefield, foes, time);
		this.mod = mod;
	}

	public Modifier getMod() {
		return this.mod;
	}

	@Override
	protected void triggerStart() {
		this.notifyTriggeredEffectListeners(new TriggeredEffectEvent(TriggeredEffectEvent.Task.APPLY, mod, this));
	}

	@Override
	protected void triggerEnd() {
		this.notifyTriggeredEffectListeners(new TriggeredEffectEvent(TriggeredEffectEvent.Task.REMOVE, mod, this));
	}

	@Override
	public void display() {

	}

}
