/** War Siblings
 * Effect_BattleSituational
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.BattleControlEvent;
import listener_interfaces.BattleControlListener;

public abstract class Effect_Battle_Triggered extends Effect_Triggered implements BattleControlListener {

	@Override
	public void onBattleControlEvent(BattleControlEvent b) {
		switch (b.getTask()) {
		case END_BATTLE:
			this.triggerEnd();
			break;
		case START_BATTLE:
			this.triggerStart();
			break;
		}
	}

}
