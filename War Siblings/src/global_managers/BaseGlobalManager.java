package global_managers;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public abstract class BaseGlobalManager {

	public BaseGlobalManager(String fileName, String objectName, String arrayName) {
		this.fillList(fileName, objectName, arrayName);
	}

	private void fillList(String fileName, String objectName, String arrayName) {
		JSONParser parser = new JSONParser();

		JSONArray list;

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));
			if (objectName.equals(null)) {
				list = (JSONArray) jsonObject.get(arrayName);
			} else {
				jsonObject = (JSONObject) jsonObject.get(objectName);
				list = (JSONArray) jsonObject.get(arrayName);
			}

			for (Object o : list) {
				JSONObject tem = (JSONObject) o;
				this.addItem(tem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void addItem(JSONObject o) {
	}
}
