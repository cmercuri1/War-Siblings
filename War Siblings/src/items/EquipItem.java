package items;

import common_classes.Attribute;

/**
 * Equip Item class that allows for an item to be equipped and thus may reduce
 * max fatigue
 */
public class EquipItem extends Item {
	protected DurAttribute durability;
	protected Attribute fatigueRed;

	public EquipItem(String name, double value, String desc, double dura, double fatRed) {
		super(name, value, desc);
		this.durability = new DurAttribute(dura);
		this.fatigueRed = new Attribute(fatRed);
	}

	public DurAttribute getDurability() {
		return this.durability;
	}

	public Attribute getFatigueRed() {
		return this.fatigueRed;
	}

}
