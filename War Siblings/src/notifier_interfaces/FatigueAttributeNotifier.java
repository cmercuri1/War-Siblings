/** War Siblings
 * FatigueAttributeNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.FatigueAttributeEvent;
import listener_interfaces.FatigueAttributeListener;

public interface FatigueAttributeNotifier {
	void addFatigueAttributeListener(FatigueAttributeListener f);
	void removeFatigueAttributeListener(FatigueAttributeListener f);
	void notifyFatigueAttributeListeners(FatigueAttributeEvent f);
}
