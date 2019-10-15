/** War Siblings
 * PassiveAbility
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import abilities.Ability;
import effect_classes.Effect;

public class PassiveAbility extends Ability {

	protected ArrayList<Effect> effects;

	/**
	 * @param name
	 * @param desc
	 * @param effects
	 */
	public PassiveAbility(String name, String desc, ArrayList<Effect> effects) {
		super(name, desc);
		this.effects = effects;
	}

	/**
	 * @param name
	 * @param effects
	 */
	public PassiveAbility(String name, ArrayList<Effect> effects) {
		super(name);
		this.effects = effects;
	}

	public ArrayList<Effect> getEffects() {
		return this.effects;
	}

	public void display() {
		super.display();
		try {
			this.effects.forEach(e -> e.display());
		} catch (NullPointerException n) {

		}
	}

}
