/** War Siblings
 * Effect_Modifier_Battle
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.TriggeredEffectEvent;

public class Effect_Modifier_Battle extends Effect_Battle_Triggered {
	protected Modifier mod;

	public Effect_Modifier_Battle(String modName, double modValue) {
		this.mod = new Modifier(modName, modValue);
	}
	
	public Effect_Modifier_Battle(Modifier mod) {
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
