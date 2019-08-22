/** War Siblings
 * Effect_Determined
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.MoraleChangeEvent;
import event_classes.TriggeredEffectEvent;
import event_classes.TriggeredEffectEvent.Task;
import storage_classes.MoraleState;

public class Effect_Determined extends Effect_MoraleChange_Triggered {

	public Effect_Determined() {
	}

	@Override
	public void onMoraleChangeEvent(MoraleChangeEvent m) {
		switch (m.getTask()) {
		case INITIAL:
			if (((Double) m.getBestMoraleState()).intValue() == MoraleState.CONFIDENT.getValue())
				this.triggerStart();
			break;
		default:
			break;

		}
	}

	@Override
	protected void triggerStart() {
		this.notifyTriggeredEffectListeners(new TriggeredEffectEvent(Task.MORALE_REPLACE, MoraleState.CONFIDENT, this));
	}

	@Override
	protected void triggerEnd() {
	}

}
