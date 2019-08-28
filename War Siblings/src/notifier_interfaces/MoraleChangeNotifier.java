/** War Siblings
 * MoraleChangeNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.MoraleChangeEvent;
import listener_interfaces.MoraleChangeListener;

public interface MoraleChangeNotifier {
	void addMoraleChangeListener(MoraleChangeListener m);

	void removeMoraleChangeListener(MoraleChangeListener m);

	void notifyMoraleChangeListeners(MoraleChangeEvent m);

	void notifyMoraleChangeListener(MoraleChangeListener m, MoraleChangeEvent e);
}
