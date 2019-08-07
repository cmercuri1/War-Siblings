/** War Siblings
 * ItemNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.ItemEvent;
import listener_interfaces.ItemListener;

public interface ItemNotifier {
	void addItemListener(ItemListener i);
	void removeItemListener(ItemListener i);
	void notifyItemListeners(ItemEvent i);
}
