package global_managers;

import java.util.Random;

public class GlobalManager {
	public static BackgroundManager characters;
	public static BodyArmorManager bodyArmors;
	public static EffectManager effects;
	public static HeadgearManager headgears;
	public static ShieldManager shields;
	public static WeaponManager weapons;
	public static TraitManager traits;
	public static Random rng;
	
	public static final double UNUSED = 999;

	static {
		effects = new EffectManager();
		characters = new BackgroundManager();
		bodyArmors = new BodyArmorManager();
		headgears = new HeadgearManager();
		shields = new ShieldManager();
		weapons = new WeaponManager();
		traits = new TraitManager();
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

	public GlobalManager() {
	}

}
