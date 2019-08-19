/** War Siblings
 * Effect_Modifier_Triggered
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.TriggeredEffectEvent;

public class Effect_Modifier_Triggered extends Effect_Triggered {

	protected Modifier mod;

	public Effect_Modifier_Triggered(String modName, double modValue) {
		this.mod = new Modifier(modName, modValue);
	}

	public Effect_Modifier_Triggered(Modifier mod) {
		this.mod = mod;
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
