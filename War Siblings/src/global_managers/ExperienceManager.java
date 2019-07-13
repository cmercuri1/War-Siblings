package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import common_classes.XPLevel;

public class ExperienceManager extends BaseGlobalManager {
	private ArrayList<XPLevel> xpList;

	public ExperienceManager() {
		super("XPData.json", null, "XP Req");
	}
	
	public XPLevel getCurrLevel(int currLevel) {
		for (XPLevel x : this.xpList) {
			if (x.getLevel() == currLevel) {
				return x;
			}
		}
		return null;
	}

	public XPLevel getNextLevel(int currLevel) {
		for (XPLevel x : this.xpList) {
			if (x.getLevel() == currLevel + 1) {
				return x;
			}
		}
		return null;
	}

	@Override
	protected void addItem(JSONObject o) {
		this.xpList.add(new XPLevel(((Long) o.get("Level")).intValue(), (Long) o.get("XP Required"), (Long) o.get("Total XP")));
	}

	@Override
	protected void instantiate() {
		this.xpList = new ArrayList<XPLevel>();
	}

}
