/** War Siblings
 * BaseGlobalManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Generic Manager class that all the other managers build from to suit their
 * needs
 */
public abstract class BaseGlobalManager {

	/**
	 * Constructor: instucts class to do the correct tasks to retrieve all the
	 * information it will need from the right file/s
	 */
	public BaseGlobalManager(String fileName, String objectName, String arrayName) {
		this.instantiate();
		this.fillList(fileName, objectName, arrayName);
	}

	/**
	 * fillList: finds the file required for the Manager object and parses through
	 * it as per the addItem method
	 */
	private void fillList(String fileName, String objectName, String arrayName) {
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
				this.addItem(tem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * addItem: each instantiated Manager defines how this works, but will take in
	 * JSON object and parse it to get the right object to place into the used
	 * ArrayList
	 */
	protected abstract void addItem(JSONObject o);

	/** Method used to instantiate the ArrayList during creation */
	protected abstract void instantiate();
}
