package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import common_classes.Mood;

public class MoodManager extends BaseGlobalManager {
	private ArrayList<Mood> moodList;

	public MoodManager() {
		super("MoodData.json", null, "Mood States");
	}
	
	public ArrayList<Mood> getMoodList() {
		return this.moodList;
	}
	
	public Mood getAMood(double currentMood) {
		for (Mood m: this.moodList) {
			if (m.checkIfMood(currentMood)) {
				return m;
			}
		}
		return null;
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

}
