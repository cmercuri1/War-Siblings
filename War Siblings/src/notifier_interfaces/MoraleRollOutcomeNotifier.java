/** War Siblings
 * MoraleListener
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.MoraleRollOutcomeEvent;
import listener_interfaces.MoraleRollOutcomeListener;

public interface MoraleRollOutcomeNotifier {
	void addMoraleListener(MoraleRollOutcomeListener m);

	void removeMoraleListener(MoraleRollOutcomeListener m);

	void notifyMoraleListeners(MoraleRollOutcomeEvent m);

	void notifyMoraleListener(MoraleRollOutcomeListener m, MoraleRollOutcomeEvent e);
}
