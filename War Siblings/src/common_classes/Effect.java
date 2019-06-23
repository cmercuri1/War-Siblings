package common_classes;

import global_managers.GlobalManager;

public class Effect {
	protected String effectName;
	protected double value;

	public Effect(String eName, double val) {
		this.effectName = eName;
		this.value = val;
	}

	public Effect(String eName) {
		this.effectName = eName;
		this.value = GlobalManager.UNUSED;
	}

	public void display() {
		System.out.println("-" + this.effectName + ": " + this.value);
	}
}
