/** War Siblings
 * Weapon Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */

package items;

import storage_classes.ArrayList;

import javax.swing.ImageIcon;

import abilities.ActivatedAbility;
import attributes.Attribute;
import effect_classes.Modifier;

/** Class designed for storing items that function as usable weapons */
public class Weapon extends AbilityItem {
	protected Attribute minDam; // Minimum damage the weapon can deal to Hitpoints
	protected Attribute maxDam; // Maximum damage the weapon can deal to Hitpoints
	protected Attribute ignArm; // Percentage of damage that can ignore Armor of target
	protected Attribute armDam; // Percentage multiplier for damage against Armor
	protected Attribute range; // Range of the weapon
	protected Attribute skillFat; // Additional/reduced fatigue on use of skills
	protected Attribute hitChance; // Additional/reduced hit chance on skills
	protected Attribute headShot; // Additional/reduced chance to hit head
	protected Attribute shieldDam; // Amount of damage on a shield when using Split Shield Skill

	protected int numHands; // Number of hands required to use weapon (1 or 2)
	protected String weapType; // Type of weapon (sword/axe/spear/etc)

	/** Constructor: single style to encompass all weapon types */
	public Weapon(String name, double value, String desc, double dura, double fatRed, double minDam, double maxDam,
			double ignArm, double armDam, double range, double skillFat, double hitChance, double headShot,
			double shieldDam, int numHands, String weapType, ArrayList<ActivatedAbility> abilityList) {
		super(name, value, desc, dura, fatRed, abilityList);
		this.minDam = new Attribute(minDam);
		this.maxDam = new Attribute(maxDam);
		this.ignArm = new Attribute(ignArm);
		this.armDam = new Attribute(armDam);
		this.range = new Attribute(range);
		this.skillFat = new Attribute(skillFat);
		this.hitChance = new Attribute(hitChance);
		this.headShot = new Attribute(headShot);
		this.shieldDam = new Attribute(shieldDam);
		this.numHands = numHands;
		this.weapType = weapType;
		this.setUpWeaponListeners();
	}

	protected void setUpWeaponListeners() {
		this.minDam.addAttributeListener(this);
		this.maxDam.addAttributeListener(this);
		this.ignArm.addAttributeListener(this);
		this.armDam.addAttributeListener(this);
		this.range.addAttributeListener(this);
		this.skillFat.addAttributeListener(this);
		this.hitChance.addAttributeListener(this);
		this.headShot.addAttributeListener(this);
		this.shieldDam.addAttributeListener(this);
	}

	@Override
	protected void setIcon() {
		this.image = new ImageIcon("res/images/Items/Weapons/" + this.name + ".png");
		if (this.image == null) {
			System.out.println("Error Finding: " + this.name);
		}
	}

	public ArrayList<Modifier> onEquipSituation() {
		ArrayList<Modifier> temp = new ArrayList<Modifier>();

		temp.add(new Modifier("fatigue_Final", this.fatigueRed.getAlteredValue()));
		temp.add(new Modifier("initiative_Final", this.fatigueRed.getAlteredValue()));
		temp.add(new Modifier("damage_Min", this.minDam.getAlteredValue()));
		temp.add(new Modifier("damage_Max", this.maxDam.getAlteredValue()));
		temp.add(new Modifier("ignoreArmor", this.ignArm.getAlteredValue()));
		temp.add(new Modifier("armorDamage", this.armDam.getAlteredValue()));
		temp.add(new Modifier("headshotChance", this.headShot.getAlteredValue()));

		return temp;
	}

	/* Relevant Getters for each element */
	public String getDamage() {
		return this.getMinDam().toString() + " - " + this.getMaxDam().toString();
	}

	public String getArmorDamage() {
		return this.armDam.toString() + "%";
	}

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

	public Attribute getSkillFat() {
		return this.skillFat;
	}

	public Attribute getHitChance() {
		return this.hitChance;
	}

	public Attribute getHeadShot() {
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

		if (this.abilityList != null) {
			System.out.println("Grants use of: ");
			for (ActivatedAbility a : this.abilityList) {
				System.out.print(" -");
				a.display();
			}
		}

		System.out.println();
	}

	/** isTwoHanded: Checks if the weapon requires both hands to use */
	public boolean isTwoHanded() {
		return (this.numHands == 2);
	}

	public String toString() {
		String temp = "<html>" + this.name + "<br>" + this.desc + "<br>" + this.weapType + ", " + this.numHands
				+ "-handed" + "<br>Worth " + this.value.toString() + " crowns";
		if (this.durability.getAlteredCurrentValue() > 1) {
			temp += "<br>" + this.durability.toString();
		}

		if (this.maxDam.getAlteredValue() > 0) {
			temp += "<br>" + this.minDam.toString() + " - " + this.maxDam.toString() + "<br>" + this.ignArm.toString()
					+ "% of damage ignores armor<br>" + this.armDam + "% effective against armor";
		}

		if (this.shieldDam.getAlteredValue() > 0) {
			temp += "<br>Shield damage of " + this.shieldDam.getAlteredValue();
		}

		if (this.hitChance.getAlteredValue() > 0) {
			temp += "<br>Has an additional " + this.hitChance + "% chance to hit";
		}

		if (this.headShot.getAlteredValue() > 0) {
			temp += "<br>Chance to hit head " + this.headShot + "%";
		}

		if (this.range.getAlteredValue() > 1) {
			temp += "<br>Range of " + this.range.getAlteredValue() + " tiles";
		}

		if (this.fatigueRed.getAlteredValue() < 0) {
			temp += "<br>Reduces Maximum Fatigue by " + this.fatigueRed.toString();
		}

		if (this.skillFat.getAlteredValue() > 0) {
			temp += "<br>Weapon skills build up " + this.skillFat + " more fatigue";
		} else if (this.skillFat.getAlteredValue() < 0) {
			temp += "<br>Weapon skills build up " + this.skillFat + " less fatigue";
		}

		return temp += "</html>";
	}
}
