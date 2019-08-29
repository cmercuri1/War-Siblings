/** War Siblings
 * BodyArmorManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONObject;

import items.Armor;

/** A class for Globally Storing and Managing all the Body Armors */
public class BodyArmorManager extends BaseGlobalManager {
	protected ArrayList<Armor> armorList;

	public BodyArmorManager() {
		super("res/game_data/RegularGearData.json", "Armor", "Body Armor");
	}

	protected void addItem(JSONObject o) {
		this.armorList.add(new Armor((String) o.get("Name"), (Long) o.get("Value"), (String) o.get("Description"),
				(Long) o.get("Durability"), (Long) o.get("Maximum Fatigue"), (String) o.get("Armor Type")));
	}

	@Override
	protected void instantiate() {
		if (this.armorList == null) {
			this.armorList = new ArrayList<Armor>();
		}
	}

	/* Getters */

	public ArrayList<Armor> getArmorList() {
		ArrayList<Armor> temp = new ArrayList<>(this.armorList);
		return temp;
	}

	/** getBodyArmor: retrieves a particular body armor by name */
	public Armor getBodyArmor(String name) {
		for (Armor a : armorList) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}

	/** display: displays statistics for a particular body armor */
	public void display(String name) {
		this.getBodyArmor(name).display();
	}

}