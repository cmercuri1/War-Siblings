/** War Siblings
 * Effect_Morale_Triggered
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.MoraleTriggerEvent;
import event_classes.MoraleTriggerEvent.Task;
import listener_interfaces.MoraleTriggerListener;

public abstract class Effect_Morale_Triggered extends Effect_Triggered implements MoraleTriggerListener {
	protected Task task;

	public Effect_Morale_Triggered(Task t) {
		this.task = t;
	}

	@Override
	public void onMoraleTriggerEvent(MoraleTriggerEvent m) {
		if (this.task.equals(m.getTask()))
			this.triggerStart();
	}
}
