/** War Siblings
 * EquipItem Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import storage_classes.Attribute;
import storage_classes.DurAttribute;

/**
 * Equip Item class that allows for an item to be equipped and thus may reduce
 * max fatigue as well as has durability that can be reduced or else damaged
 */
public class EquipItem extends Item {
	protected DurAttribute durability; // Durability of the item, if 0 item can break
	protected Attribute fatigueRed; // Reduction to fatigue while using the item

	/** Constuctor */
	public EquipItem(String name, double value, String desc, double dura, double fatRed) {
		super(name, value, desc);
		this.durability = new DurAttribute(dura);
		this.fatigueRed = new Attribute(fatRed);
	}

	/* Getters */

	public DurAttribute getDurability() {
		return this.durability;
	}

	public Attribute getFatigueRed() {
		return this.fatigueRed;
	}

	/** damageRepair: method for damaging/repairing an item */
	public void damageRepair(double value) {
		this.durability.alterItem(value);
	}
}
