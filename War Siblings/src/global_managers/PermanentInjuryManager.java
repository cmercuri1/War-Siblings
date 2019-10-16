/** War Siblings
 * PerminentInjuryManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import abilities.PermanentInjury;
import effect_classes.Effect;

/** A class for Globally Storing and Managing all the Perminent Injuries */
public class PermanentInjuryManager extends BaseGlobalManager {
	protected ArrayList<PermanentInjury> injuryList;

	public PermanentInjuryManager() {
		super("res/game_data/InjuryData.json", "Injuries", "Perminent Injuries");
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
				temp3.add(GlobalManager.effectFactory.getEffect((String) temp2.get("Effect Name")));
			}
		}

		this.injuryList.add(new PermanentInjury((String) o.get("Name"), (String) o.get("Description"), temp3,
				(Boolean) o.get("Content In Reserve")));
	}

	@Override
	protected void instantiate() {
		if (this.injuryList == null) {
			this.injuryList = new ArrayList<PermanentInjury>();
		}
	}

	/* Getters */

	public ArrayList<PermanentInjury> getInjuryList() {
		ArrayList<PermanentInjury> temp = new ArrayList<>(this.injuryList);
		return temp;
	}

}
