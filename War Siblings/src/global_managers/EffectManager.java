package global_managers;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import common_classes.Effect;

public class EffectManager extends BaseGlobalManager {
	private ArrayList<Effect> effectList;

	public EffectManager() {
		super("EffectData.json", null, "Effects");
	}

	public ArrayList<Effect> getEffectList() {
		return this.effectList;
	}
	
	public Effect search(String toBeFound) {
		for (Effect e : this.effectList) {
			if (e.getName().equals(toBeFound)) {
				return e;
			}
		} return null;
	}

	@Override
	protected void addItem(JSONObject o) {
		this.effectList.add(new Effect((String) o.get("Effect Name"), (String) o.get("Relevant Manager"),
				(String) o.get("Sub Manager"), (boolean) o.get("FinalAdd")));
	}

	@Override
	protected void instantiate() {
		if (this.effectList == null) {
			this.effectList = new ArrayList<Effect>();
		}
	}
	
	public void display() {
		for (Effect e : this.effectList) {
			e.display();
		}
	}

}
