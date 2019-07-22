/** War Siblings
 * BarAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

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
	
	public void alterCurrent(double value) {
		this.alteredCurrentValue += value;
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
		return temp + this.stringModifiers();
	}

}
