/** War Siblings
 * EquipItem Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import javax.swing.ImageIcon;

import event_classes.ItemEvent;
import event_classes.MultiValueAttributeEvent;
import listener_interfaces.ItemListener;
import listener_interfaces.MultiValueAttributeListener;
import storage_classes.Attribute;
import storage_classes.DurAttribute;
import storage_classes.Modifier;

/**
 * Equip Item class that allows for an item to be equipped and thus may reduce
 * max fatigue as well as has durability that can be reduced or else damaged
 */
public class EquipItem extends Item implements MultiValueAttributeListener, ItemListener {
	protected DurAttribute durability; // Durability of the item, if 0 item can break
	protected Attribute fatigueRed; // Reduction to fatigue while using the item

	/** Constructor */
	public EquipItem(String name, double value, String desc, double dura, double fatRed) {
		super(name, value, desc);
		this.durability = new DurAttribute(dura);
		this.fatigueRed = new Attribute(fatRed);
		this.setUpEquipItemListeners();
	}
	
	protected void setUpEquipItemListeners() {
		this.durability.addAttributeListener(this);
		this.durability.addItemListener(this);
		this.durability.addMultiValueAttributeListener(this);
		
		this.fatigueRed.addAttributeListener(this);
	}

	protected void setIcon() {
		this.image = new ImageIcon("res/Images/Items/" + this.name + ".png");
	}

	/* Getters */
	public String getDamage() {
		return "0 - 0";
	}

	public String getArmorDamage() {
		return "0%";
	}

	public DurAttribute getDurability() {
		return this.durability;
	}

	public Attribute getFatigueRed() {
		return this.fatigueRed;
	}

	/** damageRepair: method for damaging/repairing an item */
	public void damageRepair(double value) {
		this.durability.alterCurrent(value);
	}

	public String toString() {
		String temp = super.toString() + "<html><br>" + this.durability.toString();

		if (this.fatigueRed.getAlteredValue() > 0) {
			temp += "<br>Reduces Maximum Fatigue by " + this.fatigueRed.toString();
		}

		return temp + "</html>";
	}

	@Override
	public void onMultiValueAttributeEvent(MultiValueAttributeEvent m) {
		switch (m.getTask()) {
		case UPDATE_CURRENT:
			this.value.addModifier(
					new Modifier("Durability Modifier", m.getInformation(), true, false, true));
			break;
		}
	}

	@Override
	public void onItemEvent(ItemEvent i) {
		switch (i.getTask()) {
		case BROKEN:
			break;
		case MODIFY_VALUE:
			break;
		case REPAIRED:
			break;
		}
	}
}
