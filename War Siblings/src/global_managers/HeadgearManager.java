package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import items.Headgear;

/** A class for Globally Storing and Managing all the Headgears */
public class HeadgearManager extends BaseGlobalManager {
	public ArrayList<Headgear> helmList = new ArrayList<Headgear>();

	public HeadgearManager() {
		super("RegularGearData.json", "Armor", "Headgear");
	}

	protected void addItem(JSONObject o) {
		this.helmList.add(new Headgear((String) o.get("Name"), (Long) o.get("Value"), (String) o.get("Description"),
				(Long) o.get("Durability"), (Long) o.get("Maximum Fatigue"), (String) o.get("Armor Type"),
				(Long) o.get("Vision")));
	}

	public Headgear getHeadArmor(String name) {
		for (Headgear h : helmList) {
			if (h.getName().equals(name)) {
				return h;
			}
		}
		return null;
	}
	
	public void display(String name) {
		this.getHeadArmor(name).display();
	}
}