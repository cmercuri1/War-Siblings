/** War Siblings
 * MoraleListener
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.MoraleRollOutcomeEvent;
import listener_interfaces.MoraleRollOutcomeListener;

public interface MoraleRollOutcomeNotifier {
	void addMoraleRollOutcomeListener(MoraleRollOutcomeListener m);

	void removeMoraleRollOutcomeListener(MoraleRollOutcomeListener m);

	void notifyMoraleRollOutcomeListeners(MoraleRollOutcomeEvent m);

	void notifyMoraleRollOutcomeListener(MoraleRollOutcomeListener m, MoraleRollOutcomeEvent e);
}
