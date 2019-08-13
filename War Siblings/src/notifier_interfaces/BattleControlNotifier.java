/** War Siblings
 * BattleNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.BattleControlEvent;
import listener_interfaces.BattleControlListener;

public interface BattleControlNotifier {
	void addBattleControlListener(BattleControlListener b);

	void removeBattleControlListener(BattleControlListener b);

	void notifyBattleControlListeners(BattleControlEvent b);

	void notifyBattleControlListener(BattleControlListener b, BattleControlEvent e);
}
