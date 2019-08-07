/** War Siblings
 * FatigueAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.FatigueAttributeEvent;
import listener_interfaces.FatigueAttributeListener;
import notifier_interfaces.FatigueAttributeNotifier;
import old_event_classes.EventObject;
import old_event_classes.Observer;
import old_event_classes.Target;
import old_event_classes.Type;

/** Special Attribute used for Fatigue */
public class FatigueAttribute extends BarStarAttribute implements FatigueAttributeNotifier {
	protected static final double HIT = 4;
	protected static final double MISS = 2;

	protected ArrayList<FatigueAttributeListener> fatigueAttributeListeners;

	public FatigueAttribute(double value, int lMin) {
		super(value, lMin);
	}

	protected void setUpNotificationSystem() {
		super.setUpNotificationSystem();
		this.fatigueAttributeListeners = new ArrayList<FatigueAttributeListener>();
	}

	/**
	 * Method that checks if altered current value is within relevant bounds and
	 * resets it within otherwise
	 */
	protected void currentChecker() {
		if (this.alteredCurrentValue < MINIMUM) {
			this.alteredCurrentValue = MINIMUM;
		} else if (this.alteredCurrentValue > this.alteredMaxValue) {
			this.alteredCurrentValue = this.alteredMaxValue;
			this.notifyFatigueAttributeListeners(
					new FatigueAttributeEvent(FatigueAttributeEvent.Task.FULL_FATIGUE, 0.0, this));
		}
	}

	public void onHit() {
		this.alterCurrent(HIT);
	}

	public void onMiss() {
		this.alterCurrent(MISS);
	}

	@Override
	public void addFatigueAttributeListener(FatigueAttributeListener f) {
		this.fatigueAttributeListeners.add(f);
	}

	@Override
	public void removeFatigueAttributeListener(FatigueAttributeListener f) {
		this.fatigueAttributeListeners.remove(f);
	}

	@Override
	public void notifyFatigueAttributeListeners(FatigueAttributeEvent f) {
		this.fatigueAttributeListeners.forEach(l -> l.onFatigueAttributeEvent(f));
	}

}
