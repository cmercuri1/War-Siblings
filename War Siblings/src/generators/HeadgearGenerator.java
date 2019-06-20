package generators;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import items.Headgear;

public class HeadgearGenerator {
	public ArrayList<Headgear> helmList;

	public HeadgearGenerator() {
		this.helmList = new ArrayList<Headgear>();
		this.fillList();
	}

	private void fillList() {
		JSONParser parser = new JSONParser();

		JSONArray list;

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("Weapon&ArmorData.json"));
			jsonObject = (JSONObject) jsonObject.get("Armor");
			list = (JSONArray) jsonObject.get("Headgear");

			for (Object o : list) {
				JSONObject tem = (JSONObject) o;
				this.addHelm(tem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addHelm(JSONObject o) {
		this.helmList.add(new Headgear((String) o.get("Name"), (Long) o.get("Value"), (String) o.get("Description"),
				(Long) o.get("Durability"), (Long) o.get("Maximum Fatigue"), (String) o.get("Armor Type"), (Long) o.get("Vision")));
	}

	public Headgear getHeadArmor(String name) {
		for (Headgear h : helmList) {
			if (h.getName().equals(name)) {
				return h;
			}
		}
		return null;
	}

}