package items;

import common_classes.Attribute;
import common_classes.Modifier;

/** Special Attribute handling item durability */
public class DurAttribute extends Attribute {
	private final double MINIMUM = 0.0;

	protected double originalCurrentValue;
	protected double alteredCurrentValue;
	
	protected boolean isBroken;

	public DurAttribute(double value) {
		super(value);
		this.originalCurrentValue = this.originalMaxValue;
		this.alteredCurrentValue = this.originalCurrentValue;
		this.isBroken = false;
	}

	/**
	 * Updates the altered values of both current and maximum of the attribute
	 * taking into account all the modifiers
	 */
	protected void updateAltered() {
		double multi = 1;
		double add = 0;

		for (Modifier m : modifiers) {
			if (m.getIsMulti()) {
				multi *= m.getValue();
			} else {
				add += m.getValue();
			}
		}
		this.alteredMaxValue = multi * this.originalMaxValue + add;
	}
	
	public void alterItem(double value) {
		this.alteredCurrentValue += value;
		this.currentChecker();
		if (this.alteredCurrentValue == MINIMUM) {
			this.isBroken = true;
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

}
