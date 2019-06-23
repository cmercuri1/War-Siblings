package global_managers;

import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONObject;

import global_generators.BackgroundGenerator;

/** A class for Globally Storing and Managing all the Backgrounds */
public class BackgroundManager extends BaseGlobalManager {
	public ArrayList<BackgroundGenerator> bgList = new ArrayList<BackgroundGenerator>();

	public BackgroundManager() {
		super("BackgroundAttributesData.json", null, "Background Changes");
	}

	protected void addItem(JSONObject o) {
		this.bgList.add(new BackgroundGenerator(o));
	}

	public BackgroundGenerator getRandomBackground() {
		Random rand = new Random();

		return this.bgList.get(rand.nextInt(this.bgList.size()));
	}

	public BackgroundGenerator getBackground(String name) {
		for (BackgroundGenerator bg : bgList) {
			if (bg.getBackground().equals(name)) {
				return bg;
			}
		}
		return null;
	}
}
