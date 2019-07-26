package global_managers;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public abstract class TwoListGlobalManager extends BaseGlobalManager {

	public TwoListGlobalManager(String fileName, String objectName, String arrayName, String fileName2, String objectName2, String arrayName2) {
		super(fileName, objectName, arrayName);
		this.fillList2(fileName2, objectName2, arrayName2);
	}
	
	/**
	 * fillList: finds the file required for the Manager object and parses through
	 * it as per the addItem method
	 */
	protected void fillList2(String fileName, String objectName, String arrayName) {
		JSONParser parser = new JSONParser();
		JSONArray list;

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));
			if (objectName == null) {
				list = (JSONArray) jsonObject.get(arrayName);
			} else {
				jsonObject = (JSONObject) jsonObject.get(objectName);
				list = (JSONArray) jsonObject.get(arrayName);
			}

			for (Object o : list) {
				JSONObject tem = (JSONObject) o;
				this.addItem2(tem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected abstract void addItem(JSONObject o);
	
	protected abstract void addItem2(JSONObject o);

	@Override
	protected abstract void instantiate();

}
