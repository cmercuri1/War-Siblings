/** War Siblings
 * TraitManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import storage_classes.Trait;

/** A class for Globally Storing and Managing all the Temporary Injuries */
public class TraitManager extends TwoListGlobalManager {
	private ArrayList<Trait> traitList;
	private ArrayList<Trait> specialTraitList;

	public TraitManager() {
		super("res/game_data/TraitsData.json", null, "Traits", "res/game_data/SpecialTraitsData.json", null, "Traits");
		this.fillList2("res/game_data/SpecialTraitsData.json", null, "Traits");
	}

	@Override
	protected void instantiate() {
		if (this.traitList == null) {
			this.traitList = new ArrayList<Trait>();
		}
		if (this.specialTraitList == null) {
			this.specialTraitList = new ArrayList<Trait>();
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

	@Override
	protected void addItem2(JSONObject o) {
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

		this.specialTraitList.add(new Trait((String) o.get("Name"), temp3, (String) o.get("Specifc Backgrounds")));
	}

	/* Getters */

	public ArrayList<Trait> getTraitList() {
		ArrayList<Trait> temp = new ArrayList<>(this.traitList);
		return temp;
	}

	public Trait getTrait(String traitName) {
		for (Trait t : this.traitList) {
			if (t.getName().equals(traitName)) {
				return t;
			}
		}
		return null;
	}

	public ArrayList<Trait> getSpecialTraitList() {
		ArrayList<Trait> temp = new ArrayList<>(this.specialTraitList);
		return temp;
	}

	public Trait getSpecialTrait(String traitName) {
		for (Trait t : this.specialTraitList) {
			if (t.getName().equals(traitName)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * getSpecificTraitList: curates and returns an altered list that is missing
	 * items that don't match the required parameters
	 */
	public ArrayList<Trait> getSpecificTraitList(ArrayList<String> excludedTraits) {
		ArrayList<Trait> temp = new ArrayList<>(this.traitList);

		excludedTraits.forEach(s -> temp.removeIf(t -> (t.getName().equals(s))));

		return temp;
	}

	public ArrayList<Trait> getSpecifcSpecialTraitList(String background) {
		ArrayList<Trait> temp = new ArrayList<>(this.traitList);

		temp.removeIf(t -> !t.getSpecificBackground().equals(background));

		return temp;
	}
}
