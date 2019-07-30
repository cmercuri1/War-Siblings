package storage_classes;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.Observer;
import event_classes.Target;

public class BarAttribute extends Attribute {

	protected final double MINIMUM = 0.0;

	protected double originalCurrentValue;
	protected double alteredCurrentValue;

	public BarAttribute(double value, Observer o) {
		super(value, o);
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
		return ((Double) this.alteredCurrentValue).intValue() + "/" + ((Double) this.alteredMaxValue).intValue()
				+ this.stringModifiers();
	}
}
