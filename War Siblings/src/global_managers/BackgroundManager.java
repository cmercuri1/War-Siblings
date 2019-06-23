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

	public BackgroundGenerator getRandomBackground() {
		return this.bgList.get(GlobalManager.rng.nextInt(this.bgList.size()));
	}

	public BackgroundGenerator getBackground(String name) {
		for (BackgroundGenerator bg : bgList) {
			if (bg.getBackground().equals(name)) {
				return bg;
			}
		}
		return null;
	}

	public void display(String name) {
		this.getBackground(name).display();
	}

	@Override
	protected void instantiate() {
		if (this.bgList == null) {
			this.bgList = new ArrayList<BackgroundGenerator>();
		}
	}
}
