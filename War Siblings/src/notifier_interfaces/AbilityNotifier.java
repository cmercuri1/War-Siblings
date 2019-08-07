/** War Siblings
 * AbilityNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.AbilityEvent;
import listener_interfaces.AbilityListener;

public interface AbilityNotifier {
	void addAbilityListener(AbilityListener a);
	void removeAbilityListener(AbilityListener a);
	void notifyAbilityListeners(AbilityEvent a);
}
