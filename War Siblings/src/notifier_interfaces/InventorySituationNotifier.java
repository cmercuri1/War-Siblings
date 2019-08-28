/** War Siblings
 * InventorySituationNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.InventorySituationEvent;
import listener_interfaces.InventorySituationListener;

public interface InventorySituationNotifier {
	void addInventorySituationListener(InventorySituationListener i);

	void removeInventorySituationListener(InventorySituationListener i);

	void notifyInventorySituationListeners(InventorySituationEvent i);

	void notifyInventorySituationListener(InventorySituationListener i, InventorySituationEvent e);
}
