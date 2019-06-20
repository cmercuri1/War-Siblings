package items;

import java.util.ArrayList;

import common_classes.Ability;

public class AbilityItem extends EquipItem {
	protected ArrayList<Ability> abilityList;

	public AbilityItem(String name, double value, String desc, double dura, double fatRed, ArrayList<Ability> abilityList) {
		super(name, value, desc, dura, fatRed);
		this.abilityList = abilityList;
	}

}
