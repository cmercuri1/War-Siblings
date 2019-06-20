package common_classes;

import java.util.Random;

/**
 * A special class that has the minimum and maximum values for certain
 * Attributes that can have a range of values (Hitpoints, Fatigue, etc) and
 * allows for random generation of the final Attribute number at character
 * generation
 */
public class DualValue {
	private int min;
	private int max;

	public DualValue(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return this.min;
	}

	public int getMax() {
		return this.max;
	}

	private void updateMin(int addMin) {
		this.min = min + addMin;
	}

	private void updateMax(int addMax) {
		this.max = max + addMax;
	}

	public void update(int addMin, int addMax) {
		this.updateMin(addMin);
		this.updateMax(addMax);
	}

	// Generates a random value between the minimum and maximum values
	// (inclusive)
	public int getRand() {
		Random rand = new Random();
		return (this.min + rand.nextInt(this.max + 1 - this.min));
	}
}
