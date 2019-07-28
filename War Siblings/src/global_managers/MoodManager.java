/** War Siblings
 * MoodManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONObject;

import storage_classes.Mood;

/**
 * A class for Globally Storing and Managing all the Moods
 */
public class MoodManager extends BaseGlobalManager {
	private ArrayList<Mood> moodList;

	public MoodManager() {
		super("res/game_data/MoodData.json", null, "Mood States");
	}

	@Override
	protected void addItem(JSONObject o) {
		this.moodList.add(new Mood((String) o.get("Name"), (String) o.get("Description"), (Long) o.get("Minimum Mood"),
				(Long) o.get("Maximum Mood"), (Long) o.get("Best Morale State"), (Long) o.get("Chance of Best State"),
				(Long) o.get("Desertion Chance")));
	}

	@Override
	protected void instantiate() {
		this.moodList = new ArrayList<Mood>();
	}

	/* Getters */

	public ArrayList<Mood> getMoodList() {
		return this.moodList;
	}

	/** getAMood: gets a particular mood from the list */
	public Mood getAMood(double currentMood) {
		for (Mood m : this.moodList) {
			if (m.checkIfMood(currentMood)) {
				return m;
			}
		}
		return null;
	}

}
