/** War Siblings
 * BaseWarriorGenerator Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_generators;

import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import storage_classes.DualValue;

/**
 * A class that generates the base attributes and statistics of a player
 * character on top of the basics used for other creatures
 */
public class BaseWarriorGenerator extends BaseCreatureGenerator {
	// Uses dualValues to allow for both minimum and maximum for these values
	protected DualValue lev;

	protected int dailyFood;
	protected int baseWage;
	protected int xpRate;

	public BaseWarriorGenerator() {
		super("res/game_data/WarriorAttributesData.json");
		this.getWarriorAttributes("res/game_data/WarriorAttributesData.json");
	}

	private void getWarriorAttributes(String fileName) {
		JSONParser parser = new JSONParser();

		Path file = FileSystems.getDefault().getPath("", fileName);

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file.toFile()));

			JSONObject baseStats = (JSONObject) jsonObject.get("Base Stats");

			JSONArray temp = (JSONArray) baseStats.get("Starting Level");
			this.lev = new DualValue(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());

			this.dailyFood = ((Long) baseStats.get("Food Eaten per Day")).intValue();
			this.baseWage = ((Long) baseStats.get("Base Wage")).intValue();
			this.xpRate = ((Long) baseStats.get("Experience Rate")).intValue();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* Getters */

	public DualValue getLev() {
		return this.lev;
	}

	public int getDailyFood() {
		return this.dailyFood;
	}

	public int getBaseWage() {
		return this.baseWage;
	}

	public int getXpRate() {
		return this.xpRate;
	}

}
