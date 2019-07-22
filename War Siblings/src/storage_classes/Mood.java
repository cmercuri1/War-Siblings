/** War Siblings
 * Mood class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

/**
 * Storage class for storing Mood information, which is used in determining
 * starting Morale for combat as well as potential to leave party
 */
public class Mood {
	protected String name;
	protected String desc;
	protected double minMood;
	protected double maxMood;
	protected double bestMoraleState;
	protected double bestMoralechance;
	protected double desertionChance;

	public Mood(String name, String desc, double minMood, double maxMood, double bestMoraleState,
			double bestMoraleChance, double desertChance) {
		this.name = name;
		this.desc = desc;
		this.minMood = minMood;
		this.maxMood = maxMood;
		this.bestMoraleState = bestMoraleState;
		this.bestMoralechance = bestMoraleChance;
		this.desertionChance = desertChance;
	}

	public boolean checkIfMood(double value) {
		if ((value >= this.minMood) && (value <= this.maxMood)) {
			return true;
		}
		return false;
	}

	/* Getters */

	public String getName() {
		return this.name;
	}

	public String getDesc() {
		return this.desc;
	}

	public double getBestMoraleState() {
		return this.bestMoraleState;
	}

	public double getBestMoralechance() {
		return this.bestMoralechance;
	}

	public double getDesertionChance() {
		return this.desertionChance;
	}

	public String toString() {
		return this.name + ": " + this.desc;
	}

}
