/** War Siblings
 * Attribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import storage_classes.ArrayList;

import event_classes.GenericObservee;
import event_classes.Observer;

/**
 * A class that manages a particular attribute, ensuring the original value can
 * be saved it needs to be retrieved after some for of altering happens to it.
 * For example, suffering a wound that reduces effective melee skill.
 * 
 * Uses Doubles for better handling of fractions
 */
public class Attribute extends GenericObservee {
	protected double originalMaxValue; // the base unaltered value of attribute
	protected double alteredMaxValue; // base value post alterations, most
										// commonly used in game

	protected ArrayList<Modifier> modifiers; // all the modifiers that will have
												// an effect on this Attribute

	public Attribute(double value, Observer o) {
		this.modifiers = new ArrayList<Modifier>();
		this.setUpObservers();

		this.originalMaxValue = value;
		this.observerObjects.add(o);
		this.alteredMaxValue = this.originalMaxValue;
	}

	public void addModifier(Modifier m) {
		if (!m.getIsUnique()) {
			this.modifiers.add(m);
			this.updateAltered();
		} else {
			this.removeModifier(m);
			this.modifiers.add(m);
			this.updateAltered();
		}
	}

	/**
	 * Removes a modifier from modifiers list, taking in the modifier name only,
	 * then updates the altered value.
	 */
	public void removeModifier(String modName) {
		for (Modifier m : this.modifiers) {
			if (m.getName().equals(modName)) {
				this.modifiers.remove(m);
				this.updateAltered();
				return;
			}
		}
	}

	public void removeModifier(Modifier mod) {
		for (Modifier m: this.modifiers) {
			if (m.equals(mod)) {
				this.modifiers.remove(m);
				this.updateAltered();
				return;
			}
		}
	}

	/** Method to remove all modifiers */
	public void removeAllModifiers() {
		this.modifiers.removeAll(modifiers);
		this.updateAltered();
	}

	/** Method to remove all additive modifiers */
	public void removeAllAdd() {
		for (Modifier m : this.modifiers) {
			if (m.getIsMulti() == false) {
				this.modifiers.remove(m);
			}
		}
		this.updateAltered();
	}

	/** Method to remove all multiplicative modifiers */
	public void removeAllMulti() {
		for (Modifier m : this.modifiers) {
			if (m.getIsMulti() == true) {
				this.modifiers.remove(m);
			}
		}
		this.updateAltered();
	}

	/**
	 * Updates the altered value of the attribute taking into account all the
	 * modifiers
	 */
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
	}

	public double getAlteredValue() {
		return this.alteredMaxValue;
	}

	public String toString() {
		return ((Integer) ((Double) this.alteredMaxValue).intValue()).toString() + this.stringModifiers();
	}

	protected String stringModifiers() {
		String temp = "(";
		for (Modifier m : this.modifiers) {
			temp += m.toString() + " ";
		}
		temp += ")";
		return temp;
	}
}
