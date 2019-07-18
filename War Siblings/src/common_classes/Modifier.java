package common_classes;

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
	protected boolean isUnique;

	public Modifier(String name, double val, boolean isMulti, boolean finalAdd, boolean isUnique) {
		this.name = name;
		this.value = val;
		this.isMulti = isMulti;
		this.finalAdd = finalAdd;
		this.isUnique = isUnique;
	}

	public String getName() {
		return name;
	}

	public double getValue() {
		return this.value;
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

	/** Checks if this modifier and another are the same */
	public boolean equals(Modifier other) {
		if (this.name.equals(other.getName()) && (this.value == other.getValue())
				&& (this.isMulti && other.getIsMulti()) && (this.finalAdd && other.getFinalAdd())
				&& (this.isUnique && other.getIsUnique())) {
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
		if (this.isMulti) {
			return "x" + this.value;
		} else {
			if (this.finalAdd) {
				return "+" + this.value + "F";
			} else {
				return "+" + this.value;
			}
		}
	}

	public void display() {
		System.out.println("-" + this.name + ": " + this.value + ", multiplicalive: " + this.isMulti + ", unique: "
				+ this.isUnique);
	}
}
