/** War Siblings
 * TriggeredEffect
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.TriggeredEffectEvent;
import listener_interfaces.TriggeredEffectListener;
import notifier_interfaces.TriggeredEffectNotifier;
import storage_classes.ArrayList;

public abstract class Effect_Triggered extends Effect implements TriggeredEffectNotifier {

	protected ArrayList<TriggeredEffectListener> triggeredEffectListeners = new ArrayList<TriggeredEffectListener>();

	abstract protected void triggerStart();

	abstract protected void triggerEnd();

	@Override
	public void addTriggeredEffectListener(TriggeredEffectListener t) {
		this.triggeredEffectListeners.add(t);
	}

	@Override
	public void removeTriggeredEffectListener(TriggeredEffectListener t) {
		this.triggeredEffectListeners.remove(t);
	}

	@Override
	public void notifyTriggeredEffectListeners(TriggeredEffectEvent t) {
		this.triggeredEffectListeners.forEach(l -> l.onTriggeredEffectEvent(t));
	}

	@Override
	public void notifyTriggeredEffectListener(TriggeredEffectListener t, TriggeredEffectEvent e) {
		this.triggeredEffectListeners.get(t).onTriggeredEffectEvent(e);
	}
}
