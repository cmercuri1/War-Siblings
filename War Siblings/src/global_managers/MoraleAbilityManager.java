package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common_classes.Ability;
import common_classes.Effect;

public class MoraleAbilityManager extends BaseGlobalManager {
	private ArrayList<Ability> abilityList;

	public MoraleAbilityManager() {
		super("MoraleAbilityData", null, "Morale States");
	}

	public ArrayList<Ability> getAbilityList() {
		return this.abilityList;
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

		this.abilityList.add(new Ability((String) o.get("Name"), temp3));
	}

	@Override
	protected void instantiate() {
		if (this.abilityList == null) {
			this.abilityList = new ArrayList<Ability>();
		}
	}

}
