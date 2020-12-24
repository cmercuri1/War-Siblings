/** War Siblings
 * AbilityFactory
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import org.json.simple.JSONObject;

import abilities.ActivatedAbility;
import storage_classes.ArrayList;

public class ActiveAbilityManager extends BaseGlobalManager {
	protected ArrayList<ActivatedAbility> abilityList;

	public ActiveAbilityManager() {
		super("res/game_data/ActiveAbilityData.json", null, "Active Abilities");
	}

	@Override
	protected void addItem(JSONObject o) {
		this.abilityList.add(new ActivatedAbility((String) o.get("Ability Name"), null, (String) o.get("Display Name"),
				(String) o.get("Image Name"), (long) o.get("Fatigue"), (long) o.get("AP Cost")));
	}

	@Override
	protected void instantiate() {
		if (this.abilityList == null) {
			this.abilityList = new ArrayList<ActivatedAbility>();
		}
	}

	/* Getters */

	public ArrayList<ActivatedAbility> getActivatedAbilityList() {
		ArrayList<ActivatedAbility> temp = new ArrayList<>(this.abilityList);
		return temp;
	}

	/**
	 * 
	 */
	public ActivatedAbility getAbility(String abilityName) {
		for (ActivatedAbility a : this.abilityList) {
			if (a.getName().equals(abilityName)) {
				return a;
			}
		}
		return null;
	}
}
