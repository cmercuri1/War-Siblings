package global_generators;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common_classes.Ability;
import common_classes.Effect;

/**
 * A class that takes the Base Stats and attributes and changes them based on a
 * chosen Background
 */
public class BackgroundGenerator extends BaseWarriorGenerator {
	protected String background;
	protected Ability bgAbility;
	
	protected ArrayList<String> excludedTalents;
	protected ArrayList<String> excludedTraits;

	/** Constructor applying background traits */
	public BackgroundGenerator(JSONObject o) {
		super();
		this.excludedTalents = new ArrayList<String>();
		this.excludedTraits = new ArrayList<String>();
		this.updateAttributes(o);
	}

	/**
	 * UpdateAttributes method that updates the base warrior stats with any changes
	 * due to background
	 */
	private void updateAttributes(JSONObject o) {
		ArrayList<Effect> mod = new ArrayList<Effect>();
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
		try {
			temp = (JSONArray) o.get("Background Ability");
			for (Object ob : temp) {
				JSONObject ob2 = (JSONObject) ob;
				Effect newMod = new Effect((String) ob2.get("Effect Name"), (Long) ob2.get("Value"));
				mod.add(newMod);
			}
			this.bgAbility = new Ability("Background Ability", mod);
		} catch (ClassCastException ce) {
			this.bgAbility = null;
		}
		
		try {
			temp = (JSONArray) o.get("Excluded Talents");
			for (Object ob : temp) {
				this.excludedTalents.add((String) ob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			temp = (JSONArray) o.get("Excluded Traits");
			for (Object ob : temp) {
				this.excludedTraits.add((String) ob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBackground() {
		return this.background;
	}

	public Ability getBgAbility() {
		return this.bgAbility;
	}

	public ArrayList<String> getExcludedTalents() {
		return this.excludedTalents;
	}

	public ArrayList<String> getExcludedTraits() {
		return this.excludedTraits;
	}

	public void display() {
		System.out.println(this.background);
	}
}
