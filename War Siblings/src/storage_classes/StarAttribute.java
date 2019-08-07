/** War Siblings
 * StarAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.StarAttributeEvent;
import global_managers.GlobalManager;
import listener_interfaces.StarAttributeListener;
import notifier_interfaces.StarAttributeNotifier;

/**
 * A class that adds the potential for and tracks the number of stars a
 * particular Attribute may have as well as the resulting minimum and maximum
 * rolls for it used in determining level up potential rolls
 */
public class StarAttribute extends Attribute implements StarAttributeNotifier {
	protected int numStars;

	protected DualValue levelUp; // Minimum and Maximum Rolls on level up,
								// affected by Stars

	protected final int THRESHOLD1 = 35; // Minimum roll to get 1 stars
	protected final int THRESHOLD2 = 65; // Minimum roll to get 2 stars
	protected final int THRESHOLD3 = 85; // Minimum roll to get 3 stars

	protected ArrayList<StarAttributeListener> starAttributeListeners;
	
	public StarAttribute(double value, int lMin) {
		super(value);
		this.levelUp = new DualValue(lMin, lMin + 2);
		this.numStars = 0;
	}
	
	protected void setUpNotificationSystem() {
		super.setUpNotificationSystem();
		this.starAttributeListeners = new ArrayList<StarAttributeListener>();
	}

	/**
	 * Method that randomly determines the number of stars an Attribute may have as
	 * determined by the above thresholds
	 */
	public void setNumStars() {
		int roll = GlobalManager.d100Roll();

		if (roll > THRESHOLD3) {
			this.numStars = 3;
			this.levelUp.update(2, 1);
			this.notifyStarAttributeListeners(new StarAttributeEvent(StarAttributeEvent.Task.STAR_ASSIGNED, this.numStars, this));
		} else if (roll > THRESHOLD2) {
			this.numStars = 2;
			this.levelUp.update(2, 0);
			this.notifyStarAttributeListeners(new StarAttributeEvent(StarAttributeEvent.Task.STAR_ASSIGNED, this.numStars, this));
		} else if (roll > THRESHOLD1) {
			this.numStars = 1;
			this.levelUp.update(1, 0);
			this.notifyStarAttributeListeners(new StarAttributeEvent(StarAttributeEvent.Task.STAR_ASSIGNED, this.numStars, this));
		} else {
			this.numStars = 0;
		}

	}

	/**
	 * Randomly determines a value that this Attribute can be increased by at level
	 * up
	 */
	public double getLevelup() {
		return (double) this.levelUp.getRand();
	}

	public int getNumStars() {
		return this.numStars;
	}

	public String toStringFull() {
		String temp = "" + ((Double) this.alteredMaxValue).intValue();
		if (this.numStars > 0) {
			temp += " and has " + this.numStars + " stars";
		}
		return temp + this.stringModifiers();
	}

	@Override
	public void addStarAttributeListener(StarAttributeListener s) {
		this.starAttributeListeners.add(s);
	}

	@Override
	public void removeStarAttributeListener(StarAttributeListener s) {
		this.starAttributeListeners.remove(s);
	}

	@Override
	public void notifyStarAttributeListeners(StarAttributeEvent s) {
		this.starAttributeListeners.forEach(l -> l.onStarAttributeEvent(s));
	}
}
