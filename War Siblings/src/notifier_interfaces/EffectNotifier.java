/** War Siblings
 * EffectNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.EffectEvent;
import listener_interfaces.EffectListener;

public interface EffectNotifier {
	void addEffectListener(EffectListener e);
	void removeEffectListener(EffectListener e);
	void notifyEffectListeners(EffectEvent e);
}
