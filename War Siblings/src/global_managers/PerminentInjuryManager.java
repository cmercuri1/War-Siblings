package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common_classes.Effect;
import common_classes.PerminentInjury;

public class PerminentInjuryManager extends BaseGlobalManager {
	private ArrayList<PerminentInjury> injuryList;

	public PerminentInjuryManager() {
		super("InjuryData.json", "Injuries", "Perminent Injuries");
	}

	public ArrayList<PerminentInjury> getInjuryList() {
		return this.injuryList;
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

		this.injuryList.add(new PerminentInjury((String) o.get("Name"), (String) o.get("Description"), temp3, (Boolean) o.get("Content In Reserve")));
	}

	@Override
	protected void instantiate() {
		if (this.injuryList == null) {
			this.injuryList = new ArrayList<PerminentInjury>();
		}
	}

}
