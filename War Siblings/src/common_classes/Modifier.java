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

	public Modifier(String name, double val, boolean isMulti) {
		this.name = name;
		this.value = val;
	}
	
	public Modifier(String name, double val, boolean isMulti, boolean finalAdd) {
		this.name = name;
		this.value = val;
		this.isMulti = isMulti;
		this.finalAdd = finalAdd;
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
		System.out.println("-" + this.name + ": " + this.value + ", " + this.isMulti);
	}
}
