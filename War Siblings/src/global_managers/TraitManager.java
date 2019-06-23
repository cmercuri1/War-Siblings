package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common_classes.Effect;
import common_classes.Trait;

public class TraitManager extends BaseGlobalManager {
	private ArrayList<Trait> traitList;

	public TraitManager() {
		super("Traits.json", null, "Traits");
	}

	public ArrayList<Trait> getTraitList() {
		return this.traitList;
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
		ArrayList<String> temp5 = new ArrayList<String>();
		ArrayList<String> temp6 = new ArrayList<String>();

		temp = (JSONArray) o.get("Effects");
		for (Object ob : temp) {
			JSONObject temp2 = (JSONObject) ob;
			try {
				temp3.add(new Effect((String) temp2.get("Effect Name"), (Long) temp2.get("Value")));
			} catch (ClassCastException ce) {
				temp3.add(new Effect((String) temp2.get("Effect Name"), 0));
			}
		}

		temp = (JSONArray) o.get("Specific Backgrounds");
		for (Object ob : temp) {
			temp4.add((String) ob);
		}

		temp = (JSONArray) o.get("Invalid Backgrounds");
		for (Object ob : temp) {
			temp5.add((String) ob);
		}

		temp = (JSONArray) o.get("Mutually Exclusive");
		for (Object ob : temp) {
			temp6.add((String) ob);
		}

		this.traitList.add(new Trait((String) o.get("Name"), temp3, temp4, temp5, temp6));
	}

}
