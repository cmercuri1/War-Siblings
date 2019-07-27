/** War Siblings
 * EffectManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;

import org.json.simple.JSONObject;

import storage_classes.Effect;

/**
 * A class for Globally Storing and Managing all the Effect types and important
 * data about effects
 */
public class EffectManager extends BaseGlobalManager {
	private ArrayList<Effect> effectList;

	public EffectManager() {
		super("EffectData.json", null, "Effects");
	}

	@Override
	protected void addItem(JSONObject o) {
		this.effectList.add(new Effect((String) o.get("Effect Name"), (String) o.get("Relevant Manager"),
				(String) o.get("Sub Manager")));
	}

	@Override
	protected void instantiate() {
		if (this.effectList == null) {
			this.effectList = new ArrayList<Effect>();
		}
	}

	/* Getters */

	public ArrayList<Effect> getEffectList() {
		return this.effectList;
	}

	/** search: finds and returns the effect by its name */
	public Effect search(String toBeFound) {
		for (Effect e : this.effectList) {
			if (e.getName().equals(toBeFound)) {
				return e;
			}
		}
		return null;
	}

	/** display: displays all the effects */
	public void display() {
		this.effectList.forEach(e -> e.display());
	}

}
