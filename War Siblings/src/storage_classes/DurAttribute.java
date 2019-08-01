/** War Siblings
 * DurAttribute Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.Observer;

/** Special Attribute handling item durability */
public class DurAttribute extends Attribute {
	private final double MINIMUM = 0.0;

	protected double originalCurrentValue;
	protected double alteredCurrentValue;

	public DurAttribute(double value, Observer o) {
		super(value, o);
		this.originalCurrentValue = this.originalMaxValue;
		this.alteredCurrentValue = this.originalCurrentValue;
	}

	public void updateAltered() {
		super.updateAltered();
		this.currentChecker();
	}
	
	public void alterItem(double value) {
		this.alteredCurrentValue += value;
		this.currentChecker();
		if (this.alteredCurrentValue == MINIMUM) {
			this.notifyObservers(new EventObject(null, EventType.BROKEN, null, null));
		}
	}

	/**
	 * Method that checks if altered current value is within relevant bounds and
	 * resets it within otherwise
	 */
	private void currentChecker() {
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

}
