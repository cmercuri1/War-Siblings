/** War Siblings
 * TurnControlNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.TurnControlEvent;
import listener_interfaces.TurnControlListener;

public interface TurnControlNotifier {
	void addTurnControlListener(TurnControlListener t);

	void removeTurnControlListener(TurnControlListener t);

	void notifyTurnControlListeners(TurnControlEvent t);

	void notifyTurnControlListener(TurnControlListener t, TurnControlEvent e);
}
