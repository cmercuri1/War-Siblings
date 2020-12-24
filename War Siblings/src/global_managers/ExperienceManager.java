/** War Siblings
 * ExperienceManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONObject;

import storage_classes.XPLevel;

/**
 * A class for Globally Storing and Managing all the XPLevel objects used in
 * calculating Experience requirements
 */
public class ExperienceManager extends BaseGlobalManager {
	protected ArrayList<XPLevel> xpList;

	public ExperienceManager() {
		super("res/game_data/XPData.json", null, "XP Req");
	}

	@Override
	protected void addItem(JSONObject o) {
		this.xpList.add(
				new XPLevel(((Long) o.get("Level")).intValue(), (Long) o.get("XP Required"), (Long) o.get("Total XP")));
	}

	@Override
	protected void instantiate() {
		this.xpList = new ArrayList<XPLevel>();
	}

	/* Getters */

	public ArrayList<XPLevel> getXpList() {
		ArrayList<XPLevel> temp = new ArrayList<>(this.xpList);
		return temp;
	}

	/** getCurrLevel: gets the correct XPLevel object for the current level */
	public XPLevel getCurrLevel(int currLevel) {
		for (XPLevel x : this.xpList) {
			if (x.getLevel() == currLevel) {
				return x;
			}
		}
		return null;
	}

	/** getNextLevel: gets the correct XPLevel object for the next level */
	public XPLevel getNextLevel(int currLevel) {
		for (XPLevel x : this.xpList) {
			if (x.getLevel() == currLevel + 1) {
				return x;
			}
		}
		return null;
	}

}
