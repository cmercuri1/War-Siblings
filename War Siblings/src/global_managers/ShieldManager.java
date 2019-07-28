/** War Siblings
 * ShieldManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import items.Shield;
import storage_classes.Ability;

/** A class for Globally Storing and Managing all the Shields */
public class ShieldManager extends BaseGlobalManager {
	private ArrayList<Shield> shieldList;

	public ShieldManager() {
		super("res/game_data/RegularGearData.json", "Shield", "Shields List");
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

	@Override
	protected void instantiate() {
		if (this.shieldList == null) {
			this.shieldList = new ArrayList<Shield>();
		}
	}

	/* Getters */

	public ArrayList<Shield> getShieldList() {
		return this.shieldList;
	}

	/** getShield: gets a particular shield */
	public Shield getShield(String name) {
		for (Shield s : shieldList) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

	/** display: displays the statistics of a particular shield */
	public void display(String name) {
		this.getShield(name).display();
	}

}