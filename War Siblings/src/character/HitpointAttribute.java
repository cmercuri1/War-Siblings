package character;

/** Special Attribute used for Hitpoints */
public class HitpointAttribute extends BarAttribute {
	protected boolean isDead;

	public HitpointAttribute(double value, int lMin) {
		super(value, lMin);
		this.originalCurrentValue = this.originalMaxValue;
		this.alteredCurrentValue = this.originalCurrentValue;
		this.isDead = false;
	}
	
	/**
	 * Method that checks if altered current value is within relevant bounds and
	 * resets it within otherwise
	 */
	protected void currentChecker() {
		if (this.alteredCurrentValue < MINIMUM) {
			this.alteredCurrentValue = MINIMUM;
			this.isDead = true;
		} else if (this.alteredCurrentValue > this.alteredMaxValue) {
			this.alteredCurrentValue = this.alteredMaxValue;
		}
	}

}
