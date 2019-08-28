/** War Siblings
 * AttributeNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.AttributeEvent;
import listener_interfaces.AttributeListener;

public interface AttributeNotifier {
	void addAttributeListener(AttributeListener a);

	void removeAttributeListener(AttributeListener a);

	void notifyAttributeListeners(AttributeEvent a);

	void notifyAttributeListener(AttributeListener a, AttributeEvent e);
}
