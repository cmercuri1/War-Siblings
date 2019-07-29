/** War Siblings
 * WeaponManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import items.Weapon;
import storage_classes.Ability;

/** A class for Globally Storing and Managing all the Weapons */
public class WeaponManager extends BaseGlobalManager {
	public final Weapon DEFAULT = new Weapon(null, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null);
	private ArrayList<Weapon> weaponList;

	/** Constructor */
	public WeaponManager() {
		super("res/game_data/RegularGearData.json", "Weapon", "Weapons List");
	}

	protected void addItem(JSONObject o) {
		JSONArray temp = (JSONArray) o.get("Weapon Type");
		String temp2 = "";
		for (Object ob : temp) {
			temp2 += ob.toString();
			if (temp.indexOf(ob) != (temp.size() - 1)) {
				temp2 += "/";
			}
		}
		temp = (JSONArray) o.get("Ability List");
		ArrayList<Ability> temp3 = new ArrayList<Ability>();
		for (Object ob : temp) {
			temp3.add(new Ability((String) ob));
		}

		this.weaponList.add(new Weapon((String) o.get("Name"), (Long) o.get("Value"), (String) o.get("Description"),
				(Long) o.get("Durability"), (Long) o.get("Maximum Fatigue"), (Long) o.get("Damage Minimum"),
				(Long) o.get("Damage Maximum"), (Long) o.get("Ignore Armor"), (Long) o.get("Armor Damage"),
				(Long) o.get("Range"), (Long) o.get("Bonus Skill Fatigue"), (Long) o.get("Bonus Hit Chance"),
				(Long) o.get("Bonus Headshot"), (Long) o.get("Shield Damage"),
				((Long) o.get("Number of Hands required")).intValue(), temp2, temp3));
	}

	@Override
	protected void instantiate() {
		if (this.weaponList == null) {
			this.weaponList = new ArrayList<Weapon>();
		}
	}

	/* Getters */

	public ArrayList<Weapon> getWeaponList() {
		return this.weaponList;
	}

	/** getWeapon: Gets a particular weapons from the list */
	public Weapon getWeapon(String name) {
		for (Weapon w : weaponList) {
			if (w.getName().equals(name)) {
				return w;
			}
		}
		return null;
	}

	/** display: Displays the statistics of a particular weapon */
	public void display(String name) {
		this.getWeapon(name).display();
	}

}
