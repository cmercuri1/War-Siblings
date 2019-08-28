/** War Siblings
 * CharacterNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.CharacterEvent;
import listener_interfaces.CharacterListener;

public interface CharacterNotifier {
	void addCharacterListener(CharacterListener c);

	void removeCharacterListener(CharacterListener c);

	void notifyCharacterListeners(CharacterEvent c);

	void notifyCharacterListener(CharacterListener c, CharacterEvent e);
}
