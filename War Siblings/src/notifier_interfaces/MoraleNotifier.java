/** War Siblings
 * MoraleListener
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.MoraleEvent;
import listener_interfaces.MoraleListener;

public interface MoraleNotifier {
	void addMoraleListener(MoraleListener m);

	void removeMoraleListener(MoraleListener m);

	void notifyMoraleListeners(MoraleEvent m);

	void notifyMoraleListener(MoraleListener m, MoraleEvent e);
}
