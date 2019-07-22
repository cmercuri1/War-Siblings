/** War Siblings
 * Weapon Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */

package items;

import java.util.ArrayList;

import storage_classes.Ability;
import storage_classes.Attribute;

/** Class designed for storing items that function as usable weapons */
public class Weapon extends AbilityItem {
	protected Attribute minDam; // Minimum damage the weapon can deal to Hitpoints
	protected Attribute maxDam; // Maximum damage the weapon can deal to Hitpoints
	protected Attribute ignArm; // Percentage of damage that can ignore Armor of target
	protected Attribute armDam; // Percentage multiplier for damage against Armor
	protected Attribute range; // Range of the weapon

	protected double skillFat; // Additional/reduced fatigue on use of skills
	protected double hitChance; // Additional/reduced hit chance on skills
	protected double headShot; // Additional/reduced chance to hit head

	protected Attribute shieldDam; // Amount of damage on a shield when using Split Shield Skill
	protected int numHands; // Number of hands required to use weapon (1 or 2)
	protected String weapType; // Type of weapon (sword/axe/spear/etc)

	/** Constructor: single style to encompass all weapon types */
	public Weapon(String name, double value, String desc, double dura, double fatRed, double minDam, double maxDam,
			double ignArm, double armDam, double range, double skillFat, double hitChance, double headShot,
			double shieldDam, int numHands, String weapType, ArrayList<Ability> abilityList) {
		super(name, value, desc, dura, fatRed, abilityList);
		this.minDam = new Attribute(minDam);
		this.maxDam = new Attribute(maxDam);
		this.ignArm = new Attribute(ignArm);
		this.armDam = new Attribute(armDam);
		this.range = new Attribute(range);
		this.skillFat = skillFat;
		this.hitChance = hitChance;
		this.headShot = headShot;
		this.shieldDam = new Attribute(shieldDam);
		this.numHands = numHands;
		this.weapType = weapType;
	}

	/* Relevant Getters for each element */

	public Attribute getMinDam() {
		return this.minDam;
	}

	public Attribute getMaxDam() {
		return this.maxDam;
	}

	public Attribute getIgnArm() {
		return this.ignArm;
	}

	public Attribute getArmDam() {
		return this.armDam;
	}

	public Attribute getRange() {
		return this.range;
	}

	public double getSkillFat() {
		return this.skillFat;
	}

	public double getHitChance() {
		return this.hitChance;
	}

	public double getHeadShot() {
		return this.headShot;
	}

	public Attribute getShieldDam() {
		return this.shieldDam;
	}

	public int getNumHands() {
		return this.numHands;
	}

	public String getWeapType() {
		return this.weapType;
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
		System.out.println();
	}

	/** isTwoHanded: Checks if the weapon requires both hands to use */
	public boolean isTwoHanded() {
		return (this.numHands == 2);
	}
}
