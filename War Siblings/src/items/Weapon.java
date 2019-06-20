package items;

import java.util.ArrayList;

import common_classes.Ability;
import common_classes.Attribute;

/** Weapon Class */
public class Weapon extends AbilityItem {
	protected Attribute minDam;
	protected Attribute maxDam;
	protected Attribute ignArm;
	protected double armDam;
	protected Attribute range;
	protected double skillFat;
	protected double hitChance;
	protected double headShot;
	protected Attribute shieldDam;
	protected int numHands;
	protected String weapType;

	public Weapon(String name, double value, String desc, double dura, double fatRed, double minDam, double maxDam,
			double ignArm, double armDam, double range, double skillFat, double hitChance, double headShot,
			double shieldDam, int numHands, String weapType, ArrayList<Ability> abilityList) {
		super(name, value, desc, dura, fatRed, abilityList);
		this.minDam = new Attribute(minDam);
		this.maxDam = new Attribute(maxDam);
		this.ignArm = new Attribute(ignArm);
		this.armDam = armDam;
		this.range = new Attribute(range);
		this.skillFat = skillFat;
		this.hitChance = hitChance;
		this.headShot = headShot;
		this.shieldDam = new Attribute(shieldDam);
		this.numHands = numHands;
		this.weapType = weapType;
	}

	public void display() {
		System.out.println(this.name);
		System.out.println(this.numHands + "-handed, " + this.weapType);
		System.out.println(this.durability.getAlteredCurrentValue() + "/" + this.durability.getAlteredValue());
		System.out.println(this.desc);
		System.out.println(
				"Deals " + this.minDam.getAlteredValue() + "-" + this.maxDam.getAlteredValue() + " Points of Damage");
		System.out.println("Ignores " + this.ignArm.getAlteredValue() + "% of Armor, and Deals " + this.armDam
				+ "% of its Damage to Armor");
		System.out.println("Reduces Max Fatigue by " + this.fatigueRed.getAlteredValue());
		System.out.println("Range of " + this.range.getAlteredValue() + " tile/s");
		System.out.println("Skills using this weapon gain " + this.skillFat + " additional fatigue");
		System.out.println("Bonus chance to hit " + this.hitChance);
		System.out.println("Bonus chance to hit head " + this.headShot);
		if (this.shieldDam.getAlteredValue() != 0.0) {
			System.out.println("Deals " + this.shieldDam.getAlteredValue() + " Points of Damage to Shields");
		} else {
			System.out.println("Deals 1.0 Points of Damage to Shields");
		}

		System.out.println("Grants use of: ");
		for (Ability a : this.abilityList) {
			System.out.print(" -");
			a.display();
		}
		System.out.println("");
	}

	public boolean isTwoHanded() {
		return (this.numHands == 2);
	}
}
