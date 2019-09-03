/** War Siblings
 * DamageAttribute
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package attributes;

import effect_classes.Modifier;
import event_classes.AttributeEvent;
import event_classes.MultiValueAttributeEvent;
import listener_interfaces.MultiValueAttributeListener;
import notifier_interfaces.MultiValueAttributeNotifier;
import storage_classes.ArrayList;

public class DamageAttribute extends Attribute implements MultiValueAttributeNotifier {
	protected double originalMinValue; // the base unaltered value of attribute
	protected double alteredMinValue; // base value post alterations, most
										// commonly used in game

	protected ArrayList<MultiValueAttributeListener> mvAttributeListeners;

	public DamageAttribute(double minValue, double maxValue) {
		super(maxValue);

		this.originalMinValue = minValue;
		this.alteredMinValue = this.originalMinValue;
	}

	protected void setUpNotificationSystem() {
		super.setUpNotificationSystem();
		this.mvAttributeListeners = new ArrayList<MultiValueAttributeListener>();
	}

	public void addModifier(Modifier m) {
		if (m.getName().equals("damage_Min"))
			this.originalMinValue = m.getValue();
		else if (m.getName().equals("damage_Max"))
			this.originalMaxValue = m.getValue();
		else if (!m.getIsUnique()) {
			this.modifiers.add(m);
		} else {
			this.removeModifier(m);
			this.modifiers.add(m);
		}
		this.updateAltered();
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

		if ((this.originalMaxValue + add) >= 0) {
			this.alteredMaxValue = (this.originalMaxValue + add) * multi + finalAdd;
			this.alteredMinValue = (this.originalMinValue + add) * multi + finalAdd;
		} else {
			this.alteredMaxValue = (this.originalMaxValue + add) / multi + finalAdd;
			this.alteredMinValue = (this.originalMinValue + add) / multi + finalAdd;
		}
		this.notifyAttributeListeners(new AttributeEvent(AttributeEvent.Task.UPDATE, this.alteredMaxValue, this));
		this.notifyMultiValueAttributeListeners(
				new MultiValueAttributeEvent(MultiValueAttributeEvent.Task.UPDATE_CURRENT, this.alteredMinValue, this));
	}

	public String toString() {
		return ((Double) this.alteredMinValue).intValue() + " - " + ((Double) this.alteredMaxValue).intValue();
	}

	public String toStringFull() {
		return ((Double) this.alteredMinValue).intValue() + " - " + ((Double) this.alteredMaxValue).intValue()
				+ this.stringModifiers();
	}

	public double getAlteredMinValue() {
		return this.alteredMinValue;
	}

	@Override
	public void addMultiValueAttributeListener(MultiValueAttributeListener a) {
		this.mvAttributeListeners.add(a);
	}

	@Override
	public void removeMultiValueAttributeListener(MultiValueAttributeListener a) {
		this.mvAttributeListeners.remove(a);
	}

	@Override
	public void notifyMultiValueAttributeListeners(MultiValueAttributeEvent a) {
		this.mvAttributeListeners.forEach(m -> m.onMultiValueAttributeEvent(a));
	}

	@Override
	public void notifyMultiValueAttributeListener(MultiValueAttributeListener m, MultiValueAttributeEvent a) {
		this.mvAttributeListeners.get(m).onMultiValueAttributeEvent(a);
	}
}
