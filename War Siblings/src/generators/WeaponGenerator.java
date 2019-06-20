package generators;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common_classes.Ability;
import items.Weapon;

/** A class for generating all the weapons */
public class WeaponGenerator {
	public ArrayList<Weapon> weaponList;

	public WeaponGenerator() {
		this.weaponList = new ArrayList<Weapon>();
		this.fillList();
	}

	private void fillList() {
		JSONParser parser = new JSONParser();

		JSONArray list;

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("RegularGearData.json"));
			jsonObject = (JSONObject) jsonObject.get("Weapon");
			list = (JSONArray) jsonObject.get("Weapons List");

			for (Object o : list) {
				JSONObject tem = (JSONObject) o;
				this.addWeapon(tem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addWeapon(JSONObject o) {
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
			temp3.add(new Ability((String) ob, null));
		}

		this.weaponList.add(new Weapon((String) o.get("Name"), (Long) o.get("Value"), (String) o.get("Description"),
				(Long) o.get("Durability"), (Long) o.get("Maximum Fatigue"), (Long) o.get("Damage Minimum"),
				(Long) o.get("Damage Maximum"), (Long) o.get("Ignore Armor"), (Long) o.get("Armor Damage"),
				(Long) o.get("Range"), (Long) o.get("Bonus Skill Fatigue"), (Long) o.get("Bonus Hit Chance"),
				(Long) o.get("Bonus Headshot"), (Long) o.get("Shield Damage"),
				((Long) o.get("Number of Hands required")).intValue(), temp2, temp3));
	}

	public Weapon getWeapon(String name) {
		for (Weapon w : weaponList) {
			if (w.getName().equals(name)) {
				return w;
			}
		}
		return null;
	}

}
