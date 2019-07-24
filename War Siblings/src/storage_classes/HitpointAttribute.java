/** War Siblings
 * HitpointAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.Observer;

/** Special Attribute used for Hitpoints */
public class HitpointAttribute extends BarAttribute {

	public HitpointAttribute(double value, int lMin, Observer o) {
		super(value, lMin, o);
		this.originalCurrentValue = this.originalMaxValue;
		this.alteredCurrentValue = this.originalCurrentValue;
	}

	/**
	 * Method that checks if altered current value is within relevant bounds and
	 * resets it within otherwise
	 */
	protected void currentChecker() {
		if (this.alteredCurrentValue < MINIMUM) {
			this.alteredCurrentValue = MINIMUM;
			this.notifyObservers(new EventObject(null, EventType.NO_HP, null, null));
		} else if (this.alteredCurrentValue > this.alteredMaxValue) {
			this.alteredCurrentValue = this.alteredMaxValue;
		}
	}

}
