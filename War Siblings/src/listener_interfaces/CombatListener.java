/** War Siblings
 * CombatListener
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package listener_interfaces;

import event_classes.CombatEvent;

public interface CombatListener {
	void onCombatEvent(CombatEvent c);
}
