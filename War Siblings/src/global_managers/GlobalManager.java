/** War Siblings
 * GlobalManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import java.util.Random;

/**
 * A class for Globally Storing and Managing all of the other managers that
 * handle storage of their own type of object to be accessed globally and
 * statically
 */
public class GlobalManager {
	public static BackgroundManager backgrounds;
	public static EffectManager effects;
	public static EquipmentManager equipment;
	public static TraitManager traits;
	public static ExperienceManager xp;
	public static MoraleAbilityManager morale;
	public static TemporyInjuryManager tempInjury;
	public static PermanentInjuryManager permInjury;
	public static MoodManager moods;

	public static Random rng;

	public static final double UNUSED = 999;

	static {
		effects = new EffectManager();
		xp = new ExperienceManager();
		traits = new TraitManager();
		equipment = new EquipmentManager();
		morale = new MoraleAbilityManager();
		tempInjury = new TemporyInjuryManager();
		permInjury = new PermanentInjuryManager();
		moods = new MoodManager();
		backgrounds = new BackgroundManager();

		rng = new Random();
	}

	/** A Globally callable d100 dice roll (generates a number between 1-100) */
	public static int d100Roll() {
		return 1 + rng.nextInt(100);
	}

	/** Generates a value between two numbers, inclusive */
	public static int rollBetween(int minRoll, int maxRoll) {
		return minRoll + rng.nextInt(maxRoll + 1 - minRoll);
	}
}
