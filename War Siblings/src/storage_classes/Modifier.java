/** War Siblings
 * Modifier class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

/**
 * Generic modifier class for all things that can affect attributes, handled by
 * the Attribute Manager
 */
public class Modifier {
	protected String name;
	protected double value;
	protected boolean isMulti; // Used to distinguish between mulitplicative
								// modifiers and additive modifiers
	protected boolean finalAdd; // Used to determine if added after multiplicative or before
	protected boolean isUnique; // Used to determine if can have more than one of the same modifier affecting

	public Modifier(String name, double val, boolean isMulti, boolean finalAdd, boolean isUnique) {
		this.name = name;
		this.value = val;
		this.isMulti = isMulti;
		this.finalAdd = finalAdd;
		this.isUnique = isUnique;
	}

	/** Checks if this modifier and another are the same */
	public boolean equals(Modifier other) {
		if (this.name.equals(other.getName()) && (((Double)this.value).equals(other.getValue()))
				&& (this.isMulti == other.getIsMulti()) && (this.finalAdd == other.getFinalAdd())
				&& (this.isUnique == other.getIsUnique())) {
			return true;
		}
		return false;
	}

	/**
	 * Compares this to another modifier. If they are different in some capacity
	 * other than value returns null. Otherwise returns the one with the greater
	 * value
	 */
	public Modifier compareTo(Modifier other) {
		if (this.name.equals(other.getName()) && (this.isMulti && other.getIsMulti())
				&& (this.finalAdd && other.getFinalAdd()) && (this.isUnique && other.getIsUnique())) {
			if (this.value >= other.getValue()) {
				return this;
			} else {
				return other;
			}
		} else {
			return null;
		}
	}

	public String toString() {
		String temp = this.name + ": ";
		if (this.isMulti) {
			temp += "x" + this.value;
		} else {
			temp += "+" + this.value;
			if (this.finalAdd) {
				temp += "F";
			}
		}
		if (this.isUnique) {
			temp += ", U";
		}
		return temp;
	}

	/* Getters */

	public String getName() {
		return name;
	}

	public double getValue() {
		return this.value;
	}

	public void alterValue(double value) {
		this.value += value;
	}

	public boolean getIsMulti() {
		return this.isMulti;
	}

	public boolean getFinalAdd() {
		return this.finalAdd;
	}

	public boolean getIsUnique() {
		return this.isUnique;
	}

	public void display() {
		System.out.println("-" + this.name + ": " + this.value + ", multiplicalive: " + this.isMulti + ", unique: "
				+ this.isUnique);
	}
}
