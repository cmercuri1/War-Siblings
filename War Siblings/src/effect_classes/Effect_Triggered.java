/** War Siblings
 * TriggeredEffect
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.TriggeredEffectEvent;
import listener_interfaces.TriggeredEffectListener;
import notifier_interfaces.TriggeredEffectNotifier;
import storage_classes.ArrayList;

public interface Effect_Triggered extends TriggeredEffectNotifier {

	ArrayList<TriggeredEffectListener> triggeredEffectListeners = new ArrayList<TriggeredEffectListener>();;

	void triggerStart();

	void triggerEnd();

	@Override
	public default void addTriggeredEffectListener(TriggeredEffectListener t) {
		triggeredEffectListeners.add(t);
	}

	@Override
	public default void removeTriggeredEffectListener(TriggeredEffectListener t) {
		triggeredEffectListeners.remove(t);
	}

	@Override
	public default void notifyTriggeredEffectListeners(TriggeredEffectEvent t) {
		triggeredEffectListeners.forEach(l -> l.onTriggeredEffectEvent(t));
	}

	@Override
	public default void notifyTriggeredEffectListener(TriggeredEffectListener t, TriggeredEffectEvent e) {
		triggeredEffectListeners.get(t).onTriggeredEffectEvent(e);
	}
}
