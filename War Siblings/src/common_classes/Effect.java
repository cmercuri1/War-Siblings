package common_classes;

public class Effect {
	protected String effectName;
	protected double value;

	public Effect(String eName, double val) {
		this.effectName = eName;
		this.value = val;
	}

	public void display() {
		System.out.println(this.effectName + ": " + this.value);
	}
}
