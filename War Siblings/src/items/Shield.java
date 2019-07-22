/** War Siblings
 * Shield Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */

package items;

import java.util.ArrayList;

import storage_classes.Ability;
import storage_classes.Attribute;

/** A class used for storing and assisting in operating items usable as Shields */
public class Shield extends AbilityItem {
	protected Attribute meleeDef; // Bonus melee defense gained from shield
	protected Attribute rangedDef; // Bonus ranged defense gained from shield

	/** Constructor */
	public Shield(String name, double value, String desc, double dura, double fatRed, double mDef, double rDef,
			ArrayList<Ability> abilityList) {
		super(name, value, desc, dura, fatRed, abilityList);
		this.meleeDef = new Attribute(mDef);
		this.rangedDef = new Attribute(rDef);
	}

	/* Getters */
	
	public Attribute getMeleeDef() {
		return this.meleeDef;
	}

	public Attribute getRangedDef() {
		return this.rangedDef;
	}

	public void display() {
		System.out.println(this.name);
		System.out.println(this.durability.getAlteredCurrentValue() + "/" + this.durability.getAlteredValue());
		System.out.println(this.desc);
		System.out.println("Reduces Max Fatigue by " + this.fatigueRed.getAlteredValue());

		System.out.println("Grants use of: ");
		for (Ability a : this.abilityList) {
			System.out.print(" -");
			a.display();
		}
		System.out.println();
	}
}
