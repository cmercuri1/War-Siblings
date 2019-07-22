package items;

import java.util.ArrayList;

import common_classes.Ability;

/**
 * Ability Item class that allows for an item to have attached abilities that it
 * grants to the wielder
 */
public class AbilityItem extends EquipItem {
	protected ArrayList<Ability> abilityList;

	public AbilityItem(String name, double value, String desc, double dura, double fatRed,
			ArrayList<Ability> abilityList) {
		super(name, value, desc, dura, fatRed);
		this.abilityList = abilityList;
	}

	public ArrayList<Ability> getAbilityList() {
		return this.abilityList;
	}

}
