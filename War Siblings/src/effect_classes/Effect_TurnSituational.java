/** War Siblings
 * Effect_TurnSituational
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.TurnControlEvent;
import listener_interfaces.TurnControlListener;

public abstract class Effect_TurnSituational extends Effect_Triggered implements TurnControlListener {

	@Override
	public void onTurnControlEvent(TurnControlEvent t) {
		switch (t.getTask()) {
		case END_TURN:
			this.triggerEnd();
			break;
		case START_TURN:
			this.triggerStart();
			break;
		}
	}

}
