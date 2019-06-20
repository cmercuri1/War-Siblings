package character;

/** Special Attribute used for Fatigue */
public class FatigueAttribute extends BarAttribute {
	protected boolean isOutOfBreath;

	public FatigueAttribute(double value, int lMin) {
		super(value, lMin);
		this.isOutOfBreath = false;
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
			this.isOutOfBreath = true;
		} else {
			this.isOutOfBreath = false;
		}
	}

}
