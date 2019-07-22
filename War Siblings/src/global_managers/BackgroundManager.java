/** War Siblings
 * BackgroundManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import global_generators.BackgroundGenerator;

/** A class for Globally Storing and Managing all the Backgrounds */
public class BackgroundManager extends BaseGlobalManager {
	private ArrayList<BackgroundGenerator> bgList;

	public BackgroundManager() {
		super("BackgroundAttributesData.json", null, "Background Changes");
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
		return this.bgList;
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
