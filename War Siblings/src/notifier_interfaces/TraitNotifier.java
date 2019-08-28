/** War Siblings
 * TraitNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.TraitEvent;
import listener_interfaces.TraitListener;

public interface TraitNotifier {
	void addTraitListener(TraitListener t);

	void removeTraitListener(TraitListener t);

	void notifyTraitListeners(TraitEvent t);

	void notifyTraitListener(TraitListener t, TraitEvent e);
}
