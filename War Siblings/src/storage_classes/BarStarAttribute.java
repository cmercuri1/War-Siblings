/** War Siblings
 * BarAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.Observer;
import event_classes.Target;

/**
 * A class that adds to Attributes that have a current and a max value, such as
 * hit points and fatigue
 */
public class BarStarAttribute extends StarAttribute {
	protected final double MINIMUM = 0.0;

	protected double originalCurrentValue;
	protected double alteredCurrentValue;

	public BarStarAttribute(double value, int lMin, Observer o) {
		super(value, lMin, o);
	}

	public void alterCurrent(double value) {
		this.alteredCurrentValue += value;
		this.currentChecker();
		Object[] temp = { this, this.alteredCurrentValue };
		this.notifyObservers(new EventObject(Target.ATTRIBUTE, EventType.UPDATE, temp, null));
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

}
