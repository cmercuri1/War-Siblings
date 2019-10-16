/** War Siblings
 * HeadgearManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONObject;

import items.Headgear;

/** A class for Globally Storing and Managing all the Headgears */
public class HeadgearManager extends BaseGlobalManager {
	protected ArrayList<Headgear> helmList;

	public HeadgearManager() {
		super("res/game_data/RegularGearData.json", "Armor", "Headgear");
	}

	protected void addItem(JSONObject o) {
		this.helmList.add(new Headgear((String) o.get("Name"), (Long) o.get("Value"), (String) o.get("Description"),
				(Long) o.get("Durability"), (Long) o.get("Maximum Fatigue"), (String) o.get("Armor Type"),
				(Long) o.get("Vision")));
	}

	@Override
	protected void instantiate() {
		if (this.helmList == null) {
			this.helmList = new ArrayList<Headgear>();
		}
	}

	/* Getters */

	public ArrayList<Headgear> getHelmList() {
		ArrayList<Headgear> temp = new ArrayList<>(this.helmList);
		return temp;
	}

	/** getHeadArmor: gets a particular Headgear by name; */
	public Headgear getHeadArmor(String name) {
		for (Headgear h : helmList) {
			if (h.getName().equals(name)) {
				return h;
			}
		}
		return null;
	}

	/** display: displays the statistics of a particular piece of headgear */
	public void display(String name) {
		this.getHeadArmor(name).display();
	}
}