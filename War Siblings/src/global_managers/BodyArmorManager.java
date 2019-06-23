package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import items.Armor;

/** A class for Globally Storing and Managing all the Body Armors */
public class BodyArmorManager extends BaseGlobalManager {
	public ArrayList<Armor> armorList = new ArrayList<Armor>();

	public BodyArmorManager() {
		super("RegularGearData.json", "Armor", "Body Armor");
	}

	protected void addItem(JSONObject o) {
		this.armorList.add(new Armor((String) o.get("Name"), (Long) o.get("Value"), (String) o.get("Description"),
				(Long) o.get("Durability"), (Long) o.get("Maximum Fatigue"), (String) o.get("Armor Type")));
	}

	public Armor getBodyArmor(String name) {
		for (Armor a : armorList) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}
	
	public void display(String name) {
		this.getBodyArmor(name).display();
	}

}