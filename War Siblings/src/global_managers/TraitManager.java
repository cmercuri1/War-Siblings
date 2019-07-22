/** War Siblings
 * TraitManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import storage_classes.Effect;
import storage_classes.Trait;

/** A class for Globally Storing and Managing all the Temporary Injuries */
public class TraitManager extends BaseGlobalManager {
	private ArrayList<Trait> traitList;

	public TraitManager() {
		super("TraitsData.json", null, "Traits");
	}

	@Override
	protected void instantiate() {
		if (this.traitList == null) {
			this.traitList = new ArrayList<Trait>();
		}
	}

	@Override
	protected void addItem(JSONObject o) {
		JSONArray temp;
		ArrayList<Effect> temp3 = new ArrayList<Effect>();
		ArrayList<String> temp4 = new ArrayList<String>();

		temp = (JSONArray) o.get("Effects");
		for (Object ob : temp) {
			JSONObject temp2 = (JSONObject) ob;
			try {
				temp3.add(new Effect((String) temp2.get("Effect Name"), (Long) temp2.get("Value")));
			} catch (NullPointerException nul) {
				temp3.add(new Effect((String) temp2.get("Effect Name")));
			}
		}
		try {
			temp = (JSONArray) o.get("Mutually Exclusive");
			for (Object ob : temp) {
				temp4.add((String) ob);
			}
		} catch (NullPointerException n) {

		}

		this.traitList.add(new Trait((String) o.get("Name"), temp3, temp4));
	}

	/* Getters */

	public ArrayList<Trait> getTraitList() {
		return this.traitList;
	}

	/**
	 * getSpecificTraitList: curates and returns an altered list that is missing
	 * items that don't match the required parameters
	 */
	public ArrayList<Trait> getSpecificTraitList(ArrayList<String> excludedTraits) {
		ArrayList<Trait> temp = this.traitList;

		for (String s : excludedTraits) {
			temp.removeIf(t -> (t.getName().contains(s)));
		}

		return temp;
	}

}
