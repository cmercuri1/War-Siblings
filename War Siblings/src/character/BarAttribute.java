package character;

import common_classes.Modifier;

/**
 * A class that adds to Attributes that have a current and a max value, such as
 * hit points and fatigue
 */
public class BarAttribute extends StarAttribute {
	protected final double MINIMUM = 0.0;

	protected double originalCurrentValue;
	protected double alteredCurrentValue;

	public BarAttribute(double value, int lMin) {
		super(value, lMin);
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
		this.alteredCurrentValue = multi * this.originalCurrentValue + add;
		this.currentChecker();
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
		String temp = ((Double) this.alteredCurrentValue).intValue() + "/" + ((Double) this.alteredMaxValue).intValue();
		if (this.numStars > 0) {
			temp += " and has " + this.numStars + " stars";
		}
		return temp;
	}

}
