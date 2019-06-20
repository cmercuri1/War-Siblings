package generators;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import items.Armor;

public class BodyArmorGenerator {
	public ArrayList<Armor> armorList;

	public BodyArmorGenerator() {
		this.armorList = new ArrayList<Armor>();
		this.fillList();
	}

	private void fillList() {
		JSONParser parser = new JSONParser();

		JSONArray list;

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("Weapon&ArmorData.json"));
			jsonObject = (JSONObject) jsonObject.get("Armor");
			list = (JSONArray) jsonObject.get("Body Armor");

			for (Object o : list) {
				JSONObject tem = (JSONObject) o;
				this.addArmor(tem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addArmor(JSONObject o) {
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

}