/** War Siblings
 * LevelAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.EventObject;
import event_classes.Type;
import event_classes.Observer;
import event_classes.Target;
import global_managers.GlobalManager;

/** Special Attribute used in helping manager a character's level */
public class LevelAttribute extends Attribute {
	private double currXP;
	private XPLevel nextLevel;

	public LevelAttribute(double value, Observer o) {
		super(1, o);
		this.nextLevel = GlobalManager.xp.getNextLevel((int) this.alteredMaxValue);
		this.giveXP(GlobalManager.xp.getCurrLevel((int) value).getTotalXp());
	}

	public void giveXP(double value) {
		this.currXP += value;
		while (this.currXP >= this.nextLevel.getTotalXp()) {
			this.levelUp();
		}
	}

	private void levelUp() {
		this.addModifier(new Modifier("Level" + this.alteredMaxValue + 1, 1, false, false, true));
		this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.LEVEL_UP, (int) this.alteredMaxValue, null));
		this.nextLevel = GlobalManager.xp.getNextLevel((int) this.alteredMaxValue);
	}

	public double getCurrXP() {
		return this.currXP;
	}

	public String toString() {
		return ((Integer) ((Double) this.alteredMaxValue).intValue()).toString() + " (" + this.currXP + "/"
				+ ((Integer) ((Double) this.nextLevel.getTotalXp()).intValue()).toString() + ")";
	}

	public String toStringFull() {
		return ((Integer) ((Double) this.alteredMaxValue).intValue()).toString() + " (" + this.currXP + "/"
				+ ((Integer) ((Double) this.nextLevel.getTotalXp()).intValue()).toString() + ")"
				+ this.stringModifiers();
	}
}
