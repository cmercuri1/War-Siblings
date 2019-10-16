/** War Siblings
 * MoraleAbilityManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import effect_classes.Effect;
import storage_classes.MoraleState;
import storage_classes.PassiveAbility;

/**
 * A class for Globally Storing and Managing all the Abilties associated with
 * Morale States
 */
public class MoraleAbilityManager extends BaseGlobalManager {
	protected ArrayList<PassiveAbility> moraleAbilityList;

	public MoraleAbilityManager() {
		super("res/game_data/MoraleAbilityData.json", null, "Morale States");
	}

	@Override
	protected void addItem(JSONObject o) {
		JSONArray temp;
		ArrayList<Effect> temp3 = new ArrayList<Effect>();

		temp = (JSONArray) o.get("Effects");
		for (Object ob : temp) {
			JSONObject temp2 = (JSONObject) ob;
			try {
				temp3.add(GlobalManager.effectFactory.getEffect((String) temp2.get("Effect Name"),
						(Long) temp2.get("Value")));
			} catch (NullPointerException nul) {
			}
		}

		this.moraleAbilityList.add(new PassiveAbility((String) o.get("Name"), temp3));
	}

	@Override
	protected void instantiate() {
		if (this.moraleAbilityList == null) {
			this.moraleAbilityList = new ArrayList<PassiveAbility>();
		}
	}

	/* Getters */

	public ArrayList<PassiveAbility> getMoraleAbilityList() {
		ArrayList<PassiveAbility> temp = new ArrayList<>(this.moraleAbilityList);
		return temp;
	}

	/**
	 * getMoraleAbility: gets the abilities associated with a particular MoraleState
	 */
	public PassiveAbility getMoraleAbility(MoraleState toFind) {
		for (PassiveAbility a : this.moraleAbilityList) {
			if (a.getName().toLowerCase().equals(toFind.toString().toLowerCase())) {
				return a;
			}
		}
		return null;
	}

}
