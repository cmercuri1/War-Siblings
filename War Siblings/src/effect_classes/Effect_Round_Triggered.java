/** War Siblings
 * Effect_RoundSituational
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.RoundControlEvent;
import listener_interfaces.RoundControlListener;

public abstract class Effect_Round_Triggered extends Effect_Triggered implements RoundControlListener {

	@Override
	public void onRoundControlEvent(RoundControlEvent r) {
		switch (r.getTask()) {
		case END_ROUND:
			this.triggerEnd();
			break;
		case START_ROUND:
			this.triggerStart();
			break;

		}
	}

}
