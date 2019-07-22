/** War Siblings
 * BaseCreatureGenerator Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_generators;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import storage_classes.DualValue;

/**
 * A base generator class that others will use as a base for creating
 * characters/creatures
 */
public class BaseCreatureGenerator {
	// Uses dualValues to allow for both minimum and maximum for these values
	protected DualValue hp;
	protected DualValue fat;
	protected DualValue res;
	protected DualValue ini;
	protected DualValue mSk;
	protected DualValue rSk;
	protected DualValue mDef;
	protected DualValue rDef;

	protected int actionPoints;
	protected int headShot;
	protected int fatRegain;
	protected int vision;

	public BaseCreatureGenerator(String file) {
		this.getAttributes(file);
	}

	private void getAttributes(String file) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));

			JSONObject baseStats = (JSONObject) jsonObject.get("Base Stats");

			JSONArray temp = (JSONArray) baseStats.get("Hitpoints");
			this.hp = new DualValue(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
			temp = (JSONArray) baseStats.get("Fatigue");
			this.fat = new DualValue(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
			temp = (JSONArray) baseStats.get("Resolve");
			this.res = new DualValue(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
			temp = (JSONArray) baseStats.get("Initiative");
			this.ini = new DualValue(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
			temp = (JSONArray) baseStats.get("Melee Skill");
			this.mSk = new DualValue(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
			temp = (JSONArray) baseStats.get("Ranged Skill");
			this.rSk = new DualValue(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
			temp = (JSONArray) baseStats.get("Melee Defense");
			this.mDef = new DualValue(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
			temp = (JSONArray) baseStats.get("Ranged Defense");
			this.rDef = new DualValue(((Long) temp.get(0)).intValue(), ((Long) temp.get(1)).intValue());
			temp = (JSONArray) baseStats.get("Starting Level");

			this.actionPoints = ((Long) baseStats.get("Action Points")).intValue();
			this.headShot = ((Long) baseStats.get("Change to Hit Head")).intValue();
			this.fatRegain = ((Long) baseStats.get("Fatigue Regain")).intValue();
			this.vision = ((Long) baseStats.get("Vision")).intValue();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/* Getters */

	public DualValue getHp() {
		return this.hp;
	}

	public DualValue getFat() {
		return this.fat;
	}

	public DualValue getRes() {
		return this.res;
	}

	public DualValue getIni() {
		return this.ini;
	}

	public DualValue getmSk() {
		return this.mSk;
	}

	public DualValue getrSk() {
		return this.rSk;
	}

	public DualValue getmDef() {
		return this.mDef;
	}

	public DualValue getrDef() {
		return this.rDef;
	}

	public int getActionPoints() {
		return this.actionPoints;
	}

	public int getHeadShot() {
		return this.headShot;
	}

	public int getFatRegain() {
		return this.fatRegain;
	}

	public int getVision() {
		return this.vision;
	}
}
