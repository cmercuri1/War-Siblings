/** War Siblings
 * PerminentInjuryManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import storage_classes.Effect;
import storage_classes.PerminentInjury;

/** A class for Globally Storing and Managing all the Perminent Injuries */
public class PerminentInjuryManager extends BaseGlobalManager {
	private ArrayList<PerminentInjury> injuryList;

	public PerminentInjuryManager() {
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
				temp3.add(new Effect((String) temp2.get("Effect Name"), (Long) temp2.get("Value")));
			} catch (NullPointerException nul) {
				temp3.add(new Effect((String) temp2.get("Effect Name")));
			}
		}

		this.injuryList.add(new PerminentInjury((String) o.get("Name"), (String) o.get("Description"), temp3,
				(Boolean) o.get("Content In Reserve")));
	}

	@Override
	protected void instantiate() {
		if (this.injuryList == null) {
			this.injuryList = new ArrayList<PerminentInjury>();
		}
	}

	/* Getters */

	public ArrayList<PerminentInjury> getInjuryList() {
		ArrayList<PerminentInjury> temp = new ArrayList<>(this.injuryList);
		return temp;
	}

}
