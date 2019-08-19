/** War Siblings
 * ModifierNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.ModifierEvent;
import listener_interfaces.ModifierListener;

public interface ModifierNotifier {
	void addModifierListener(ModifierListener m);

	void removeModifierListener(ModifierListener m);

	void notifyModifierListeners(ModifierEvent m);

	void notifyModifierListener(ModifierListener m, ModifierEvent e);
}
