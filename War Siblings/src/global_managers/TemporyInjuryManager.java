package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common_classes.Effect;
import common_classes.TemporaryInjury;

public class TemporyInjuryManager extends BaseGlobalManager {
	private ArrayList<TemporaryInjury> injuryList;

	public TemporyInjuryManager() {
		super("InjuryData.json", "Injuries", "Temporary Injuries");
	}

	public ArrayList<TemporaryInjury> getInjuryList() {
		return this.injuryList;
	}
	
	public ArrayList<TemporaryInjury> getSpecificInjuryList(boolean isHead, String damageType) {
		ArrayList<TemporaryInjury> temp = this.injuryList;
		
		temp.removeIf(t -> !(t.isHead() && isHead));
		temp.removeIf(t -> !(t.getDamageType().equals(damageType)));
		
		return temp;
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

		this.injuryList.add(new TemporaryInjury((String) o.get("Name"), (String) o.get("Description"), (Boolean) o.get("isHead"),
				(String) o.get("Damage Type"), (Long) o.get("Damage Threshold"), temp3, (Long) o.get("Min Days"),
				(Long) o.get("Max Days")));
	}

	@Override
	protected void instantiate() {
		if (this.injuryList == null) {
			this.injuryList = new ArrayList<TemporaryInjury>();
		}
	}

}
