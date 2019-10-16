/** War Siblings
 * EffectManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import storage_classes.ArrayList;
import storage_classes.Effect_Storage;

import org.json.simple.JSONObject;

/**
 * A class for Globally Storing and Managing all the Effect types and important
 * data about effects
 */
public class EffectManager extends TwoListGlobalManager {
	protected ArrayList<Effect_Storage> effectList;

	public EffectManager() {
		super("res/game_data/EffectModifierData.json", null, "Effect Names", "res/game_data/EffectSituationalData.json",
				null, "Effects");
	}

	@Override
	protected void addItem(JSONObject o) {
		this.effectList.add(new Effect_Storage((String) o.get("Effect Name"), "Effect_Modifier"));
		this.effectList.add(new Effect_Storage((String) o.get("Effect Name") + "_Percent", "Effect_Modifier"));
		this.effectList.add(new Effect_Storage((String) o.get("Effect Name") + "_Percent_Unique", "Effect_Modifier"));
		this.effectList.add(new Effect_Storage((String) o.get("Effect Name") + "_Final", "Effect_Modifier"));
		this.effectList.add(new Effect_Storage((String) o.get("Effect Name") + "_Final_Unique", "Effect_Modifier"));
		this.effectList.add(new Effect_Storage((String) o.get("Effect Name") + "_Unique", "Effect_Modifier"));
	}

	@Override
	protected void addItem2(JSONObject o) {
		this.effectList.add(new Effect_Storage((String) o.get("Effect Name"), (String) o.get("Effect Type")));
	}

	@Override
	protected void instantiate() {
		if (this.effectList == null) {
			this.effectList = new ArrayList<Effect_Storage>();
		}
	}

	/* Getters */

	public ArrayList<Effect_Storage> getEffectList() {
		ArrayList<Effect_Storage> temp = new ArrayList<>(this.effectList);
		return temp;
	}

	/** search: finds and returns the effect by its name */
	public Effect_Storage search(String toBeFound) {
		for (Effect_Storage e : this.effectList) {
			if (e.getEffectName().equals(toBeFound)) {
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
