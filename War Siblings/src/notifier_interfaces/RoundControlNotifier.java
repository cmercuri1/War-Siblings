/** War Siblings
 * RoundControlNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.RoundControlEvent;
import listener_interfaces.RoundControlListener;

public interface RoundControlNotifier {
	void addRoundControlListener(RoundControlListener r);

	void removeRoundControlListener(RoundControlListener r);

	void notifyRoundControlListeners(RoundControlEvent r);

	void notifyRoundControlListener(RoundControlListener r, RoundControlEvent e);
}
