/** War Siblings
 * TemporaryInjuryManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import storage_classes.Effect;
import storage_classes.TemporaryInjury;

/** A class for Globally Storing and Managing all the Temporary Injuries */
public class TemporyInjuryManager extends BaseGlobalManager {
	private ArrayList<TemporaryInjury> injuryList;

	public TemporyInjuryManager() {
		super("InjuryData.json", "Injuries", "Temporary Injuries");
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

		this.injuryList.add(new TemporaryInjury((String) o.get("Name"), (String) o.get("Description"),
				(Boolean) o.get("isHead"), (String) o.get("Damage Type"), (Long) o.get("Damage Threshold"), temp3,
				(Long) o.get("Min Days"), (Long) o.get("Max Days")));
	}

	@Override
	protected void instantiate() {
		if (this.injuryList == null) {
			this.injuryList = new ArrayList<TemporaryInjury>();
		}
	}

	/* Getters */

	public ArrayList<TemporaryInjury> getInjuryList() {
		return this.injuryList;
	}

	/**
	 * getSpecificInjuryList: curates and returns an altered list that is missing
	 * items that don't match the required parameters
	 */
	public ArrayList<TemporaryInjury> getSpecificInjuryList(boolean isHead, String damageType) {
		ArrayList<TemporaryInjury> temp = this.injuryList;

		temp.removeIf(t -> !(t.isHead() && isHead));
		temp.removeIf(t -> !(t.getDamageType().equals(damageType)));

		return temp;
	}

}
