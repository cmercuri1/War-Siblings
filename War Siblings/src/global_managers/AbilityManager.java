/** War Siblings
 * AbilityManager
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import org.json.simple.JSONObject;

import abilities.Ability;
import storage_classes.ArrayList;

public class AbilityManager extends BaseGlobalManager {

	protected ArrayList<Ability> abilityList;

	/**
	 */
	public AbilityManager() {
		super("res/game_data/AbilityData.json", null, "Abilities");
	}

	@Override
	protected void addItem(JSONObject o) {
		if ((String) o.get("Display Name") != null) {
			this.abilityList.add(new Ability((String) o.get("Ability Name"), null, (String) o.get("Display Name")));
		} else {
			this.abilityList.add(new Ability((String) o.get("Ability Name"), null));
		}
	}

	@Override
	protected void instantiate() {
		if (this.abilityList == null) {
			this.abilityList = new ArrayList<Ability>();
		}
	}

	/* Getters */

	public ArrayList<Ability> getAbilityList() {
		ArrayList<Ability> temp = new ArrayList<>(this.abilityList);
		return temp;
	}

	/**
	 * 
	 */
	public Ability getAbility(String abilityName) {
		for (Ability a : this.abilityList) {
			if (a.getName().equals(abilityName)) {
				return a;
			}
		}
		return null;
	}

}
