/** War Siblings
 * VisionAttribute
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.AttributeEvent;

public class VisionAttribute extends Attribute {
	protected final double MINIMUM = 0.0;

	public VisionAttribute(double value) {
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

		if (this.alteredMaxValue < MINIMUM)
			this.alteredMaxValue = MINIMUM;
		this.notifyAttributeListeners(new AttributeEvent(AttributeEvent.Task.UPDATE, this.alteredMaxValue, this));
	}

}
