/** War Siblings
 * PermanentInjuryNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.PermanentInjuryEvent;
import listener_interfaces.PermanentInjuryListener;

public interface PermanentInjuryNotifier {
	void addPermanentInjuryListener(PermanentInjuryListener p);
	void removePermanentInjuryListener(PermanentInjuryListener p);
	void notifyPermanentInjuryListeners(PermanentInjuryEvent p);
}
