/** War Siblings
 * Modifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

public class Modifier {
	protected String name;
	protected double value;
	protected boolean isMulti; // Used to distinguish between mulitplicative
								// modifiers and additive modifiers
	protected boolean finalAdd; // Used to determine if added after multiplicative or before
	protected boolean isUnique; // Used to determine if can have more than one of the same modifier affecting

	public Modifier(String name, double value, boolean isMulti, boolean finalAdd, boolean isUnique) {
		this.name = name;
		this.value = value;
		this.isMulti = isMulti;
		this.finalAdd = finalAdd;
		this.isUnique = isUnique;
	}

	public Modifier(String name, double value) {
		this.name = name;
		this.value = value;
		this.setBooleans();
	}

	protected void setBooleans() {
		if (!this.setIsMulti())
			this.setFinalAdd();
		this.setIsUnique();
	}

	protected boolean setIsMulti() {
		if (this.name.contains("_Percent")) {
			this.isMulti = true;
			this.name = this.name.replace("_Percent", "");
			return true;
		}
		this.isMulti = false;
		return false;
	}

	protected void setFinalAdd() {
		if (this.name.contains("_Final")) {
			this.finalAdd = true;
			this.name = this.name.replace("_Final", "");
		}
		this.finalAdd = false;
	}

	protected void setIsUnique() {
		if (this.name.contains("_Unique")) {
			this.isUnique = true;
			this.name = this.name.replace("_Unique", "");
		}
		this.isUnique = false;
	}

	/** Checks if this modifier and another are the same */
	public boolean equals(Modifier other) {
		if (this.name.equals(other.getName()) && (((Double) this.value).equals(other.getValue()))
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
