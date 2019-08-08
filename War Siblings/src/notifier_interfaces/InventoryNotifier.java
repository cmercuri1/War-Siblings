/** War Siblings
 * InventoryNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.InventoryEvent;
import listener_interfaces.InventoryListener;

public interface InventoryNotifier {
	void addInventoryListener(InventoryListener i);

	void removeInventoryListener(InventoryListener i);

	void notifyInventoryListeners(InventoryEvent i);

	void notifyInventoryListener(InventoryListener i, InventoryEvent e);
}
