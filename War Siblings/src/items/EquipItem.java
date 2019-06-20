package items;

import common_classes.Attribute;

public class EquipItem extends Item {
	protected DurAttribute durability;
	protected Attribute fatigueRed;

	public EquipItem(String name, double value, String desc, double dura, double fatRed) {
		super(name, value, desc);
		this.durability = new DurAttribute(dura);
		this.fatigueRed = new Attribute(fatRed);
	}

}
