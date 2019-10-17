/** War Siblings
 * BackgroundGenerator Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_generators;

import storage_classes.ArrayList;
import storage_classes.BackgroundItem;

import javax.swing.ImageIcon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import abilities.PassiveAbility;
import effect_classes.Effect;
import global_managers.GlobalManager;

/**
 * A class that takes the Base Stats and attributes and changes them based on a
 * chosen Background
 */
public class BackgroundGenerator extends BaseWarriorGenerator {
	protected String background;
	protected PassiveAbility bgAbility;

	protected ArrayList<String> excludedTalents;
	protected ArrayList<String> excludedTraits;
	protected ArrayList<BackgroundItem> headOptions;
	protected ArrayList<BackgroundItem> bodyOptions;
	protected ArrayList<BackgroundItem> rightOptions;
	protected ArrayList<BackgroundItem> leftOptions;
	protected ArrayList<BackgroundItem> backPackOptions;

	protected ImageIcon bgIcon;

	/** Constructor applying background traits */
	public BackgroundGenerator(JSONObject o) {
		super();

		this.excludedTalents = new ArrayList<String>();
		this.excludedTraits = new ArrayList<String>();
		this.headOptions = new ArrayList<BackgroundItem>();
		this.bodyOptions = new ArrayList<BackgroundItem>();
		this.rightOptions = new ArrayList<BackgroundItem>();
		this.leftOptions = new ArrayList<BackgroundItem>();
		this.backPackOptions = new ArrayList<BackgroundItem>();

		this.bgIcon = new ImageIcon("res/images/Backgrounds" + this.background + ".png");

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
				Effect newMod = GlobalManager.effectFactory.getEffect((String) ob2.get("Effect Name"),
						(Long) ob2.get("Value"));
				mod.add(newMod);
			}
			this.bgAbility = new PassiveAbility("Background Ability", mod);
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

		try {
			temp = (JSONArray) o.get("Starting Headgear");
			for (Object ob : temp) {
				JSONObject ob2 = (JSONObject) ob;
				this.headOptions.add(new BackgroundItem(BackgroundItem.Location.HEAD, (String) ob2.get("Item Name"),
						(Long) ob2.get("Chance to Start")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			temp = (JSONArray) o.get("Starting Body");
			for (Object ob : temp) {
				JSONObject ob2 = (JSONObject) ob;
				this.bodyOptions.add(new BackgroundItem(BackgroundItem.Location.BODY, (String) ob2.get("Item Name"),
						(Long) ob2.get("Chance to Start")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			temp = (JSONArray) o.get("Starting Right");
			for (Object ob : temp) {
				JSONObject ob2 = (JSONObject) ob;
				this.rightOptions.add(new BackgroundItem(BackgroundItem.Location.RIGHT, (String) ob2.get("Item Name"),
						(Long) ob2.get("Chance to Start")));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			temp = (JSONArray) o.get("Starting Left");
			for (Object ob : temp) {
				JSONObject ob2 = (JSONObject) ob;
				this.leftOptions.add(new BackgroundItem(BackgroundItem.Location.LEFT, (String) ob2.get("Item Name"),
						(Long) ob2.get("Chance to Start")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			temp = (JSONArray) o.get("Starting Bag");
			for (Object ob : temp) {
				JSONObject ob2 = (JSONObject) ob;
				this.backPackOptions.add(new BackgroundItem(BackgroundItem.Location.BAG, (String) ob2.get("Item Name"),
						(Long) ob2.get("Chance to Start")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Getters */

	public String getBackground() {
		return this.background;
	}

	public PassiveAbility getBgAbility() {
		return this.bgAbility;
	}

	public ArrayList<String> getExcludedTalents() {
		return this.excludedTalents;
	}

	public ArrayList<String> getExcludedTraits() {
		return this.excludedTraits;
	}

	public ArrayList<BackgroundItem> getHeadOptions() {
		return this.headOptions;
	}

	public ArrayList<BackgroundItem> getBodyOptions() {
		return this.bodyOptions;
	}

	public ArrayList<BackgroundItem> getRightOptions() {
		return this.rightOptions;
	}

	public ArrayList<BackgroundItem> getLeftOptions() {
		return this.leftOptions;
	}

	public ArrayList<BackgroundItem> getBackPackOptions() {
		return this.backPackOptions;
	}

	public ImageIcon getBgIcon() {
		return this.bgIcon;
	}

	/** display: displays background name */
	public void display() {
		System.out.println(this.background);
	}
}
