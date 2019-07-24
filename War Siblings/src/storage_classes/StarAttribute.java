/** War Siblings
 * StarAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.Observer;
import global_managers.GlobalManager;

/**
 * A class that adds the potential for and tracks the number of stars a
 * particular Attribute may have as well as the resulting minimum and maximum
 * rolls for it used in determining level up potential rolls
 */
public class StarAttribute extends Attribute {
	protected int numStars;

	private DualValue levelUp; // Minimum and Maximum Rolls on level up,
								// affected by Stars

	private final int THRESHOLD1 = 35; // Minimum roll to get 1 stars
	private final int THRESHOLD2 = 65; // Minimum roll to get 2 stars
	private final int THRESHOLD3 = 85; // Minimum roll to get 3 stars

	public StarAttribute(double value, int lMin, Observer o) {
		super(value, o);
		this.levelUp = new DualValue(lMin, lMin + 2);
		this.numStars = 0;
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
		} else if (roll > THRESHOLD2) {
			this.numStars = 2;
			this.levelUp.update(2, 0);
		} else if (roll > THRESHOLD1) {
			this.numStars = 1;
			this.levelUp.update(1, 0);
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

	public String toString() {
		String temp = "" + ((Double) this.alteredMaxValue).intValue();
		if (this.numStars > 0) {
			temp += " and has " + this.numStars + " stars";
		}
		return temp+this.stringModifiers();
	}
}
