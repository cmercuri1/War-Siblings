/** War Siblings
 * BattleNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.BattleEvent;
import listener_interfaces.BattleListener;

public interface BattleNotifier {
	void addBattleListener(BattleListener b);

	void removeBattleListener(BattleListener b);

	void notifyBattleListeners(BattleEvent b);

	void notifyBattleListener(BattleListener b, BattleEvent e);
}
