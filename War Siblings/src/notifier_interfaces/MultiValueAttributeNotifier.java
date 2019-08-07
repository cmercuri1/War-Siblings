/** War Siblings
 * MultiValueAttributeNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.MultiValueAttributeEvent;
import listener_interfaces.MultiValueAttributeListener;

public interface MultiValueAttributeNotifier {
	void addMultiValueAttributeListener(MultiValueAttributeListener a);
	void removeMultiValueAttributeListener(MultiValueAttributeListener a);
	void notifyMultiValueAttributeListeners(MultiValueAttributeEvent a);
}
