package global_generators;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import common_classes.DualValue;

/**
 * A class that generates the base attributes and statistics of a player
 * character on top of the basics used for other creatures
 */
public class BaseWarriorGenerator extends BaseCreatureGenerator {
	// Uses dualValues to allow for both minimum and maximum for these values
	protected DualValue lev;

	protected int baseWage;
	protected int xpRate;

	public BaseWarriorGenerator() {
		super("WarriorAttributesData.json");
		this.getWarriorAttributes();
	}

	private void getWarriorAttributes() {
		JSONParser parser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("WarriorAttributesData.json"));

			JSONObject baseStats = (JSONObject) jsonObject.get("Base Stats");

			JSONArray temp = (JSONArray) baseStats.get("Starting Level");
			this.lev = new DualValue(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());

			this.baseWage = ((Long) baseStats.get("Base Wage")).intValue();
			this.xpRate = ((Long) baseStats.get("Experience Rate")).intValue();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public DualValue getLev() {
		return this.lev;
	}

	public int getBaseWage() {
		return this.baseWage;
	}

	public int getXpRate() {
		return this.xpRate;
	}

}
