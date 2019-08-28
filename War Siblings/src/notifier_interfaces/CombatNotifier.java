/** War Siblings
 * CombatNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.CombatEvent;
import listener_interfaces.CombatListener;

public interface CombatNotifier {
	void addCombatListener(CombatListener c);

	void removeCombatListener(CombatListener c);

	void notifyCombatListeners(CombatEvent c);

	void notifyCombatListener(CombatListener c, CombatEvent e);
}
