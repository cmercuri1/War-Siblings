/** War Siblings
 * CharacterInventoryNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.CharacterInventoryEvent;
import listener_interfaces.CharacterInventoryListener;

public interface CharacterInventoryNotifier {
	void addCharacterInventoryListener(CharacterInventoryListener c);

	void removeCharacterInventoryListener(CharacterInventoryListener c);

	void notifyCharacterInventoryListeners(CharacterInventoryEvent c);

	void notifyCharacterInventoryListener(CharacterInventoryListener c, CharacterInventoryEvent e);
}
