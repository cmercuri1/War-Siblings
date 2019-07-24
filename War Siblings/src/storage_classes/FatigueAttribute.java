/** War Siblings
 * FatigueAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.Observer;

/** Special Attribute used for Fatigue */
public class FatigueAttribute extends BarAttribute {

	public FatigueAttribute(double value, int lMin, Observer o) {
		super(value, lMin, o);
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
			this.notifyObservers(new EventObject(null, EventType.NO_FATIGUE, null, null));
		}
	}

}
