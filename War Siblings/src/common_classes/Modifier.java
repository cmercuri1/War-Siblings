package common_classes;

/**
 * Generic modifier class for all things that can affect attributes, handled by
 * the Attribute Manager
 */
public class Modifier {
	private String name;
	private double value;
	private boolean isMulti; // Used to distinguish between mulitplicative
								// modifiers and additive modifiers

	public Modifier(String name, double val, boolean isMulti) {
		this.name = name;
		this.value = val;
		this.isMulti = isMulti;
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
}
