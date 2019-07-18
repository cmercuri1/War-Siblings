package character;

import common_classes.Modifier;

public class DefenseAttribute extends StarAttribute {
	private final static double SOFT_CAP = 45.0;

	public DefenseAttribute(double value, int lMin) {
		super(value, lMin);
	}
	
	/**
	 * Updates the altered value of the attribute taking into account all the
	 * modifiers. It then applies bounding on defense attributes.
	 */
	protected void updateAltered() {
		double multi = 1;
		double add = 0;
		double finalAdd = 0;
		
		double temp;

		for (Modifier m : modifiers) {
			if (m.getIsMulti()) {
				multi *= (1 + m.getValue() / 100);
			} else {
				if (m.getFinalAdd()) {
					finalAdd += m.getValue();
				} else {
					add += m.getValue();
				}
			}
		}
		temp = multi * (this.originalMaxValue + add) + finalAdd;
		
		if (temp > SOFT_CAP) {
			this.alteredMaxValue = SOFT_CAP + (temp-SOFT_CAP)/2;
		} else {
			this.alteredMaxValue = temp;
		}
	}
}
