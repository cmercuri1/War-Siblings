/** War Siblings
 * MoraleNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.MoraleRollEvent;
import listener_interfaces.MoraleRollListener;

public interface MoraleRollNotifier {
	void addMoraleListener(MoraleRollListener l);

	void removeMoraleListener(MoraleRollListener l);

	void notifyMoraleListeners(MoraleRollEvent l);

	void notifyMoraleListener(MoraleRollListener l, MoraleRollEvent e);
}
