/** War Siblings
 * Effect_Modifier_Inventory_Situation
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.TriggeredEffectEvent;
import event_classes.InventorySituationEvent.Task;

public class Effect_Modifier_Inventory_Situation extends Effect_Inventory_Situation {

	protected Modifier mod;
	
	public Effect_Modifier_Inventory_Situation(Task match, Modifier mod) {
		super(match);
		this.mod = mod;
	}
	
	public Effect_Modifier_Inventory_Situation(Task match, String modName, double modValue) {
		super(match);
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
