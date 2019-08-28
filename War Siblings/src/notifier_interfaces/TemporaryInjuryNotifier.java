/** War Siblings
 * TemporaryInjuryNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.TemporaryInjuryEvent;
import listener_interfaces.TemporaryInjuryListener;

public interface TemporaryInjuryNotifier {
	void addTemporaryInjuryListener(TemporaryInjuryListener t);

	void removeTemporaryInjuryListener(TemporaryInjuryListener t);

	void notifyTemporaryInjuryListeners(TemporaryInjuryEvent t);

	void notifyTemporaryInjuryListener(TemporaryInjuryListener t, TemporaryInjuryEvent e);
}
