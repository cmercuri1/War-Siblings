/** War Siblings
 * Effect_Deathwish
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.MoraleTriggerEvent.Task;
import event_classes.TriggeredEffectEvent;

public class Effect_Deathwish extends Effect_Morale_Triggered {

	/**
	 * 
	 */
	public Effect_Deathwish() {
		super(Task.WOUNDED);
	}

	@Override
	protected void triggerStart() {
		this.notifyTriggeredEffectListeners(new TriggeredEffectEvent(TriggeredEffectEvent.Task.IGNORE_MORALE, task, this));
	}

	@Override
	protected void triggerEnd() {
		// DOES NOTHING
	}

	@Override
	public void display() {
	
	}

}
