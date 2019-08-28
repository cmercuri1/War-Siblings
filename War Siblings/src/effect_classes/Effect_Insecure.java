/** War Siblings
 * Effect_Insecure
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.MoraleChangeEvent;
import event_classes.TriggeredEffectEvent;
import event_classes.TriggeredEffectEvent.Task;
import storage_classes.MoraleState;

public class Effect_Insecure extends Effect_MoraleChange_Triggered {

	public Effect_Insecure() {
	}

	@Override
	public void onMoraleChangeEvent(MoraleChangeEvent m) {
		switch (m.getInformation()) {
		case CONFIDENT:
			this.triggerStart();
			break;
		default:
			break;
		
		}
	}

	@Override
	protected void triggerStart() {
		this.notifyTriggeredEffectListeners(new TriggeredEffectEvent(Task.MORALE_REPLACE, MoraleState.STEADY, this));
	}

	@Override
	protected void triggerEnd() {
		
	}

}
