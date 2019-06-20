package character;

/** A level up storage class used for helping in levelling up a character */
public class LevelUp {
	private String name;
	private double value;

	public LevelUp(String name, double value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public double getValue() {
		return this.value;
	}

}
