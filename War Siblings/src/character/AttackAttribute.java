package character;

import common_classes.Attribute;
import common_classes.Modifier;

public class AttackAttribute extends Attribute {
	private final static int MAXCAP = 95;
	private final static int MINCAP = 5;

	public AttackAttribute(double value) {
		super(value);
	}
	
	protected void updateAltered() {
		double multi = 1;
		double add = 0;
		double finalAdd = 0;

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
		this.alteredMaxValue = multi * (this.originalMaxValue + add) + finalAdd;
		
		if (this.alteredMaxValue > MAXCAP) {
			this.alteredMaxValue = MAXCAP;
		} else if (this.alteredMaxValue < MINCAP) {
			this.alteredMaxValue = MINCAP;
		}
	}

}
