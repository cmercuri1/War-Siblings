package character;

import java.io.FileReader;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * A class that takes the Base Stats and attributes and changes them based on a
 * chosen Background
 */
public class BackgroundGenerator extends BaseWarriorGenerator {
	protected String background;
	protected String ability;

	/** Constructor for picking a specific background */
	public BackgroundGenerator(String bgName) {
		super();

		JSONArray bg;

		bg = this.open();
		if (bg != null) {
			for (Object o : bg) {
				JSONObject tem = (JSONObject) o;
				if (bgName.equals(((String) tem.get("Name")))) {
					this.updateAttributes(tem);
				}
			}
		}
	}

	/** Constructor for picking a random background */
	public BackgroundGenerator() {
		super();

		JSONArray bg;

		bg = this.open();
		if (bg != null) {
			JSONObject o;
			Random rand = new Random();
			o = (JSONObject) bg.get(rand.nextInt(bg.size()));
			this.updateAttributes(o);
		}
	}

	private JSONArray open() {
		JSONParser parser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("BackgroundAttributesData.json"));

			JSONArray backgrounds = (JSONArray) jsonObject.get("Background Changes");

			return backgrounds;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * UpdateAttributes method that updates the base warrior stats with any
	 * changes due to background
	 */
	private void updateAttributes(JSONObject o) {
		this.background = ((String) o.get("Name"));

		JSONArray temp = (JSONArray) o.get("Hitpoints");
		this.hp.update(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
		temp = (JSONArray) o.get("Fatigue");
		this.fat.update(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
		temp = (JSONArray) o.get("Resolve");
		this.res.update(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
		temp = (JSONArray) o.get("Initiative");
		this.ini.update(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
		temp = (JSONArray) o.get("Melee Skill");
		this.mSk.update(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
		temp = (JSONArray) o.get("Ranged Skill");
		this.rSk.update(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
		temp = (JSONArray) o.get("Melee Defense");
		this.mDef.update(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
		temp = (JSONArray) o.get("Ranged Defense");
		this.rDef.update(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
		temp = (JSONArray) o.get("Starting Level");
		this.lev.update(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());

		this.baseWage = baseWage + (((Long) o.get("Base Wage")).intValue());
		this.ability = (String) o.get("Background Ability");

	}
}
