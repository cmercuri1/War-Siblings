package character;

import common_classes.Attribute;
import common_classes.Modifier;
import common_classes.XPLevel;
import global_managers.GlobalManager;

public class LevelAttribute extends Attribute {
	private double currXP;
	private XPLevel nextLevel;

	public LevelAttribute(double value) {
		super(value);
		this.setupXP();
	}

	private void setupXP() {
		this.currXP = GlobalManager.xp.getCurrLevel((int) this.alteredMaxValue).getTotalXp();
		this.nextLevel = GlobalManager.xp.getNextLevel((int) this.alteredMaxValue);
	}

	public void giveXP(double value) {
		this.currXP += value;
		if (this.currXP >= this.nextLevel.getTotalXp()) {
			this.levelUp();
		}
	}

	private void levelUp() {
		this.addModifier(new Modifier("Level" + this.alteredMaxValue + 1, 1, false, false, true));
		this.nextLevel = GlobalManager.xp.getNextLevel((int) this.alteredMaxValue);
	}

	public double getCurrXP() {
		return this.currXP;
	}

	public String toString() {
		return ((Integer) ((Double) this.alteredMaxValue).intValue()).toString() + " (" + this.currXP + "/"
				+ ((Integer) ((Double) this.nextLevel.getTotalXp()).intValue()).toString() + ")"
				+ this.stringModifiers();
	}
}
