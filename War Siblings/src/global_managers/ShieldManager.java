package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common_classes.Ability;
import items.Shield;

/** A class for Globally Storing and Managing all the Shields */
public class ShieldManager extends BaseGlobalManager {
	private ArrayList<Shield> shieldList;

	public ShieldManager() {
		super("RegularGearData.json", "Shield", "Shields List");
	}

	protected void addItem(JSONObject o) {
		JSONArray temp = (JSONArray) o.get("Ability List");
		ArrayList<Ability> temp2 = new ArrayList<Ability>();
		for (Object ob : temp) {
			temp2.add(new Ability((String) ob));
		}
		this.shieldList.add(new Shield((String) o.get("Name"), (Long) o.get("Value"), (String) o.get("Description"),
				(Long) o.get("Durability"), (Long) o.get("Maximum Fatigue"), (Long) o.get("Melee Defense"),
				(Long) o.get("Ranged Defense"), temp2));
	}

	public Shield getShield(String name) {
		for (Shield s : shieldList) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

	public void display(String name) {
		this.getShield(name).display();
	}

	@Override
	protected void instantiate() {
		if (this.shieldList == null) {
			this.shieldList = new ArrayList<Shield>();
		}
	}

}