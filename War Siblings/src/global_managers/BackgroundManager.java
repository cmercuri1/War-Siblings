/** War Siblings
 * BackgroundManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONObject;

import global_generators.BackgroundGenerator;

/** A class for Globally Storing and Managing all the Backgrounds */
public class BackgroundManager extends BaseGlobalManager {
	protected ArrayList<BackgroundGenerator> bgList;

	public BackgroundManager() {
		super("res/game_data/BackgroundAttributesData.json", null, "Background Changes");
	}

	protected void addItem(JSONObject o) {
		this.bgList.add(new BackgroundGenerator(o));
	}

	@Override
	protected void instantiate() {
		if (this.bgList == null) {
			this.bgList = new ArrayList<BackgroundGenerator>();
		}
	}

	/* Getters */

	public ArrayList<BackgroundGenerator> getBgList() {
		ArrayList<BackgroundGenerator> temp = new ArrayList<>(this.bgList);
		return temp;
	}

	public String[] getBgNames() {
		ArrayList<String> bgNames = new ArrayList<String>();
		bgNames.add("Random");
		this.bgList.forEach(b -> bgNames.add(b.getBackground()));
		String[] temp = new String[this.bgList.size()];
		return bgNames.toArray(temp);
	}

	/** getRandomBackground: retrieves a random background from list */
	public BackgroundGenerator getRandomBackground() {
		return this.bgList.get(GlobalManager.rng.nextInt(this.bgList.size()));
	}

	/** getBackground: retrieves a background from list by name */
	public BackgroundGenerator getBackground(String name) {
		for (BackgroundGenerator bg : bgList) {
			if (bg.getBackground().equals(name)) {
				return bg;
			}
		}
		return null;
	}

	/** display: displays a selected background's information */
	public void display(String name) {
		this.getBackground(name).display();
	}
}
