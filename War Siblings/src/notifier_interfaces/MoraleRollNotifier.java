/** War Siblings
 * MoraleNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.MoraleRollEvent;
import listener_interfaces.MoraleRollListener;

public interface MoraleRollNotifier {
	void addMoraleRollListener(MoraleRollListener m);

	void removeMoraleRollListener(MoraleRollListener m);

	void notifyMoraleRollListeners(MoraleRollEvent m);

	void notifyMoraleRollListener(MoraleRollListener m, MoraleRollEvent e);
}
