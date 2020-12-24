/** War Siblings
 * LevelAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package attributes;

import effect_classes.Modifier;
import event_classes.LevelUpAttributeEvent;
import global_managers.GlobalManager;
import listener_interfaces.LevelUpAttributeListener;
import notifier_interfaces.LevelUpAttributeNotifier;
import storage_classes.ArrayList;
import storage_classes.XPLevel;

/** Special Attribute used in helping manager a character's level */
public class LevelAttribute extends Attribute implements LevelUpAttributeNotifier {
	protected double currXP;
	protected XPLevel nextLevel;

	protected ArrayList<LevelUpAttributeListener> lvlUpAttributeListeners;

	public LevelAttribute(double value) {
		super(1);
		this.nextLevel = GlobalManager.xp.getNextLevel((int) this.alteredMaxValue);
		this.giveXP(GlobalManager.xp.getCurrLevel((int) value).getTotalXp());
	}

	protected void setUpNotificationSystem() {
		super.setUpNotificationSystem();
		this.lvlUpAttributeListeners = new ArrayList<LevelUpAttributeListener>();
	}

	public void giveXP(double value) {
		this.currXP += value;
		while (this.currXP >= this.nextLevel.getTotalXp()) {
			this.levelUp();
		}
	}

	private void levelUp() {
		this.addModifier(new Modifier("Level" + this.alteredMaxValue + 1, 1, false, false, true));
		this.notifyLevelUpAttributeListeners(
				new LevelUpAttributeEvent(LevelUpAttributeEvent.Task.LEVEL_UP, this.alteredMaxValue, this));
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

	@Override
	public void addLevelUpAttributeListener(LevelUpAttributeListener l) {
		this.lvlUpAttributeListeners.add(l);
	}

	@Override
	public void removeLevelUpAttributeListener(LevelUpAttributeListener l) {
		this.lvlUpAttributeListeners.remove(l);
	}

	@Override
	public void notifyLevelUpAttributeListeners(LevelUpAttributeEvent l) {
		this.lvlUpAttributeListeners.forEach(e -> e.onLevelUpAttributeEvent(l));
	}

	@Override
	public void notifyLevelUpAttributeListener(LevelUpAttributeListener l, LevelUpAttributeEvent e) {
		this.lvlUpAttributeListeners.get(l).onLevelUpAttributeEvent(e);
	}
}
