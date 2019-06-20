package character;

import java.util.ArrayList;

import common_classes.Ability;

/**
 * A manager class that handles all the abilities a character may have, either
 * from items, traits or from level abilities
 */
public class AbilityManager {

	private ArrayList<Ability> characterAbilities;

	public AbilityManager() {
		this.characterAbilities = new ArrayList<Ability>();
		this.traitSetUp();
	}

	/**
	 * Gets Traits and picks up to two for the character, then ensures effects from
	 * traits are applied
	 */
	private void traitSetUp() {

	}

	public ArrayList<Ability> getAbilities() {
		return this.characterAbilities;
	}

	public void addAbility(Ability ability) {
		this.characterAbilities.add(ability);
	}

	public void removeAbility(String abilityName) {
		for (Ability a : this.characterAbilities) {
			if (a.getName().equals(abilityName)) {
				this.characterAbilities.remove(a);
				return;
			}
		}
	}

	public void display() {
		for (Ability a : this.characterAbilities) {
			a.display();
		}

	}

}
