package character;

import java.util.ArrayList;

import common_classes.Ability;
import common_classes.Trait;

import global_managers.GlobalManager;
import global_managers.TraitManager;

/**
 * A manager class that handles all the abilities a character may have, either
 * from items, traits or from level abilities
 */
public class AbilityManager {

	private ArrayList<Ability> characterAbilities;

	public AbilityManager(String background) {
		this.characterAbilities = new ArrayList<Ability>();
		this.traitDetermining(background);
	}

	/**
	 * Gets Traits and picks up to two for the character, then ensures effects from
	 * traits are applied
	 */
	private void traitDetermining(String background) {
		TraitManager temp = GlobalManager.traits;

		// Temp list culling of unattainable traits
		for (Trait t : temp.getTraitList()) {
			if (t.getInvalBg().contains(background) || !(t.getSpecBg().contains(background))) {
				temp.getTraitList().remove(t);
				break;
			}
		}
		
		
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
		System.out.println("Character Abilities:");
		for (Ability a : this.characterAbilities) {
			a.display();
		}

	}

}
