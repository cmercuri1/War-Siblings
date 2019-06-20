package generators;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common_classes.Ability;
import items.Shield;

/** A class for generating all the Shields */
public class ShieldGenerator {
	public ArrayList<Shield> shieldList;

	public ShieldGenerator() {
		this.shieldList = new ArrayList<Shield>();
		this.fillList();
	}

	private void fillList() {
		JSONParser parser = new JSONParser();

		JSONArray list;

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("RegularGearData.json"));
			jsonObject = (JSONObject) jsonObject.get("Shield");
			list = (JSONArray) jsonObject.get("Shields List");

			for (Object o : list) {
				JSONObject tem = (JSONObject) o;
				this.addShield(tem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addShield(JSONObject o) {
		JSONArray temp = (JSONArray) o.get("Ability List");
		ArrayList<Ability> temp2 = new ArrayList<Ability>();
		for (Object ob : temp) {
			temp2.add(new Ability((String) ob, null));
		}
		this.shieldList.add(new Shield((String) o.get("Name"), (Long) o.get("Value"), (String) o.get("Description"),
				(Long) o.get("Durability"), (Long) o.get("Maximum Fatigue"), (Long) o.get("Melee Defense"),
				(Long) o.get("Ranged Defense"), temp2));
	}

	public Shield getBodyArmor(String name) {
		for (Shield s : shieldList) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

}