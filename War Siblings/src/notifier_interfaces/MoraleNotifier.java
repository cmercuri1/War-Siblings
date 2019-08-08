/** War Siblings
 * MoraleNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.MoraleEvent;
import listener_interfaces.MoraleListener;

public interface MoraleNotifier {
	void addMoraleListener(MoraleListener l);

	void removeMoraleListener(MoraleListener l);

	void notifyMoraleListeners(MoraleEvent l);

	void notifyMoraleListener(MoraleListener l, MoraleEvent e);
}
