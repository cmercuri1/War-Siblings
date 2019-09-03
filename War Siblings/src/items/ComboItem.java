/** War Siblings
 * EquipItem Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import attributes.Attribute;
import attributes.DurAttribute;
import effect_classes.Modifier;
import event_classes.ItemEvent;
import event_classes.MultiValueAttributeEvent;
import storage_classes.ArrayList;

/**
 * Equip Item class that allows for an item to be equipped and thus may reduce
 * max fatigue as well as has durability that can be reduced or else damaged
 */
public class ComboItem extends Item implements Equipable, Durable, Weighty {
	protected DurAttribute durability; // Durability of the item, if 0 item can break
	protected Attribute fatigueRed; // Reduction to fatigue while using the item

	/** Constructor */
	public ComboItem(String name, double value, String desc, double dura, double fatRed) {
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

	public ArrayList<Modifier> onEquipSituation() {
		ArrayList<Modifier> temp = new ArrayList<Modifier>();

		temp.add(new Modifier("fatigue", this.fatigueRed.getAlteredValue()));
		temp.add(new Modifier("initiative_Final", this.fatigueRed.getAlteredValue()));

		return temp;
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
		this.durability.alterCurrent(value);
	}

	public String toString() {
		String temp = "<html>" + this.name + "<br>" + this.desc + "<br>Worth " + this.value.toString() + " crowns"
				+ "<br>" + this.durability.toString();

		if (this.fatigueRed.getAlteredValue() < 0) {
			temp += "<br>Reduces Maximum Fatigue by " + this.fatigueRed.toString();
		}

		return temp + "</html>";
	}

	@Override
	public void onMultiValueAttributeEvent(MultiValueAttributeEvent m) {
		switch (m.getTask()) {
		case UPDATE_CURRENT:
			this.value.addModifier(new Modifier("Durability Modifier", m.getInformation(), true, false, true));
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
