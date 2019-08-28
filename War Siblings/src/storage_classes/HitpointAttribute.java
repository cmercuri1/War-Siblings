/** War Siblings
 * HitpointAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.HitpointAttributeEvent;
import listener_interfaces.HitpointAttributeListener;
import notifier_interfaces.HitpointAttributeNotifier;

/** Special Attribute used for Hitpoints */
public class HitpointAttribute extends BarStarAttribute implements HitpointAttributeNotifier {
	protected ArrayList<HitpointAttributeListener> hitpointAttributeListeners;

	public HitpointAttribute(double value, int lMin) {
		super(value, lMin);
		this.alteredCurrentValue = this.originalMaxValue;
	}

	protected void setUpNotificationSystem() {
		super.setUpNotificationSystem();
		this.hitpointAttributeListeners = new ArrayList<HitpointAttributeListener>();
	}

	/**
	 * Method that checks if altered current value is within relevant bounds and
	 * resets it within otherwise
	 */
	protected void currentChecker() {
		if (this.alteredCurrentValue < MINIMUM) {
			this.alteredCurrentValue = MINIMUM;
			this.notifyHitpointAttributeListeners(
					new HitpointAttributeEvent(HitpointAttributeEvent.Task.NO_HP, 0.0, this));
		} else if (this.alteredCurrentValue > this.alteredMaxValue) {
			this.alteredCurrentValue = this.alteredMaxValue;
		}
	}

	@Override
	public void addHitpointAttributeListener(HitpointAttributeListener h) {
		this.hitpointAttributeListeners.add(h);
	}

	@Override
	public void removeHitpointAttributeListener(HitpointAttributeListener h) {
		this.hitpointAttributeListeners.remove(h);
	}

	@Override
	public void notifyHitpointAttributeListeners(HitpointAttributeEvent h) {
		this.hitpointAttributeListeners.forEach(l -> l.onHitpointAttributeEvent(h));
	}

	@Override
	public void notifyHitpointAttributeListener(HitpointAttributeListener h, HitpointAttributeEvent e) {
		this.hitpointAttributeListeners.get(h).onHitpointAttributeEvent(e);
	}

}
