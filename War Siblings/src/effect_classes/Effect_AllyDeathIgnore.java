/** War Siblings
 * Effect_AllyDeathIgnore
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.MoraleTriggerEvent.Task;
import event_classes.TriggeredEffectEvent;

public class Effect_AllyDeathIgnore extends Effect_Morale_Triggered {

	public Effect_AllyDeathIgnore() {
		super(Task.ALLY_DEATH);
	}

	@Override
	protected void triggerStart() {
		this.notifyTriggeredEffectListeners(new TriggeredEffectEvent(TriggeredEffectEvent.Task.IGNORE_MORALE, task, this));
	}

	@Override
	protected void triggerEnd() {
		// NOTHING
	}

	@Override
	public void display() {

	}

}
