package character;

import java.util.ArrayList;

import common_classes.Ability;
import common_classes.Trait;

import global_generators.BackgroundGenerator;

import global_managers.GlobalManager;
import global_managers.TraitManager;

/**
 * A manager class that handles all the abilities a character may have, either
 * from items, traits or from level abilities
 */
public class AbilityManager {

	private ArrayList<Ability> characterAbilities;

	public AbilityManager(BackgroundGenerator background) {
		this.characterAbilities = new ArrayList<Ability>();
		if (background.getBgAbility() != null) {
			this.characterAbilities.add(background.getBgAbility());
		}
		this.traitDetermining(background.getBackground());
	}

	/**
	 * Gets Traits and picks up to two for the character, then ensures effects from
	 * traits are applied
	 */
	private void traitDetermining(String background) {
		ArrayList<Trait> temp = GlobalManager.traits.getTraitList();

		// Temp list culling of unattainable traits
		for (Trait t : temp) {
			if (t.getInvalBg().contains(background) || !(t.getSpecBg().contains(background))) {
				temp.remove(t);
				break;
			}
		}

		// Roll for up to two different traits, with 50% chance each time? If a trait is
		// gained, then we remove any mutually exclusive traits. e.g. if "Huge" is gained,
		// "Tiny" should not be able to be rolled
		for (int i = 0; i < 2; i++) {
			if (GlobalManager.d100Roll() < 51) {
				int pos = GlobalManager.rng.nextInt(temp.size());
				Trait sel = temp.get(pos);
				this.characterAbilities.add(sel);
				temp.remove(sel);

				if (!sel.getMutalExcl().isEmpty()) {
					for (String s : sel.getMutalExcl()) {
						for (Trait t : temp) {
							if (t.getName().equals(s)) {
								temp.remove(t);
								break;
							}
						}
					}
				}
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
