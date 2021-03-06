/** War Siblings
 * DefenseAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package attributes;

import effect_classes.Modifier;
import event_classes.AttributeEvent;

/**
 * Special Attribute used in managing a character's defense attributes which
 * have different scaling as they get higher
 */
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

		if ((this.originalMaxValue + add) >= 0)
			temp = (this.originalMaxValue + add) * multi + finalAdd;
		else
			temp = (this.originalMaxValue + add) / multi + finalAdd;

		if (temp > SOFT_CAP) {
			this.alteredMaxValue = SOFT_CAP + (temp - SOFT_CAP) / 2;
		} else {
			this.alteredMaxValue = temp;
		}

		this.notifyAttributeListeners(new AttributeEvent(AttributeEvent.Task.UPDATE, this.alteredMaxValue, this));
	}
}
