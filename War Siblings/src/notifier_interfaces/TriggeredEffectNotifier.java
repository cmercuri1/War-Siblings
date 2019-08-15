/** War Siblings
 * TriggeredTriggeredEffectNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.TriggeredEffectEvent;
import listener_interfaces.TriggeredEffectListener;

public interface TriggeredEffectNotifier {
	void addTriggeredEffectListener(TriggeredEffectListener t);

	void removeTriggeredEffectListener(TriggeredEffectListener t);

	void notifyTriggeredEffectListeners(TriggeredEffectEvent t);

	void notifyTriggeredEffectListener(TriggeredEffectListener t, TriggeredEffectEvent e);
}
