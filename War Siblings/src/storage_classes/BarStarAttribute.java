/** War Siblings
 * BarAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.MultiValueAttributeEvent;
import listener_interfaces.MultiValueAttributeListener;
import notifier_interfaces.MultiValueAttributeNotifier;

/**
 * A class that adds to Attributes that have a current and a max value, such as
 * hit points and fatigue
 */
public class BarStarAttribute extends StarAttribute implements MultiValueAttributeNotifier {
	protected final double MINIMUM = 0.0;

	protected double alteredCurrentValue;

	protected ArrayList<MultiValueAttributeListener> mvAttributeListeners;

	public BarStarAttribute(double value, int lMin) {
		super(value, lMin);
	}

	protected void setUpNotificationSystem() {
		super.setUpNotificationSystem();
		this.mvAttributeListeners = new ArrayList<MultiValueAttributeListener>();
	}

	public void updateAltered() {
		super.updateAltered();
		this.currentChecker();
	}

	public void alterCurrent(double value) {
		this.alteredCurrentValue += value;
		this.currentChecker();
		this.notifyMultiValueAttributeListeners(new MultiValueAttributeEvent(
				MultiValueAttributeEvent.Task.UPDATE_CURRENT, this.alteredCurrentValue, this));
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
		}
	}

	public double getAlteredCurrentValue() {
		return this.alteredCurrentValue;
	}

	public String toString() {
		return ((Double) this.alteredCurrentValue).intValue() + "/" + ((Double) this.alteredMaxValue).intValue();
	}

	public String toStringFull() {
		String temp = ((Double) this.alteredCurrentValue).intValue() + "/" + ((Double) this.alteredMaxValue).intValue();
		if (this.numStars > 0) {
			temp += " and has " + this.numStars + " stars";
		}
		return temp + this.stringModifiers();
	}

	@Override
	public void addMultiValueAttributeListener(MultiValueAttributeListener a) {
		this.mvAttributeListeners.add(a);
	}

	@Override
	public void removeMultiValueAttributeListener(MultiValueAttributeListener a) {
		this.mvAttributeListeners.remove(a);
	}

	@Override
	public void notifyMultiValueAttributeListeners(MultiValueAttributeEvent a) {
		this.mvAttributeListeners.forEach(m -> m.onMultiValueAttributeEvent(a));
	}

	@Override
	public void notifyMultiValueAttributeListener(MultiValueAttributeListener m, MultiValueAttributeEvent a) {
		this.mvAttributeListeners.get(m).onMultiValueAttributeEvent(a);
	}
}
