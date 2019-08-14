/** War Siblings
 * AbilityItem Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import storage_classes.ArrayList;

import storage_classes.Ability;

/**
 * Ability Item class that allows for an item to have attached abilities that it
 * grants to the wielder
 */
public class AbilityItem extends EquipItem {
	protected ArrayList<Ability> abilityList;

	/** Constructor */
	public AbilityItem(String name, double value, String desc, double dura, double fatRed,
			ArrayList<Ability> abilityList) {
		super(name, value, desc, dura, fatRed);
		this.abilityList = new ArrayList<Ability>();
		this.abilityList = abilityList;
	}

	/* Getters */
	public ArrayList<Ability> getAbilityList() {
		return this.abilityList;
	}

}
