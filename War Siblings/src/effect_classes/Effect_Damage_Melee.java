/** War Siblings
 * Effect_Damage_Melee
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.TriggeredEffectEvent;
import event_classes.InventorySituationEvent.Task;

public class Effect_Damage_Melee extends Effect_Inventory_Situation_Multiple {

	protected Modifier mod;

	public Effect_Damage_Melee(Modifier mod) {
		super(Task.MELEE, Task.UNARMED);
		this.mod = mod;
	}

	public Effect_Damage_Melee(String modName, double modValue) {
		super(Task.MELEE, Task.UNARMED);
		this.mod = new Modifier(modName, modValue);
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
