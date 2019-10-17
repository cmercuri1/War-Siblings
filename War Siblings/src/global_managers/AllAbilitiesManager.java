/** War Siblings
 * AbilityManager
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import abilities.Ability;
import storage_classes.ArrayList;

public class AllAbilitiesManager {
	protected ActiveAbilityManager activatedAbility;
	protected AbilityManager ability;

	public AllAbilitiesManager() {
		this.activatedAbility = new ActiveAbilityManager();
		this.ability = new AbilityManager();
	}

	public ActiveAbilityManager getActivatedAbilityManager() {
		return this.activatedAbility;
	}

	public AbilityManager getAbilityManager() {
		return this.ability;
	}

	public ArrayList<Ability> getAbilities(String... abilityNames) {
		ArrayList<Ability> temp = new ArrayList<>();

		for (String abilityName : abilityNames) {
			try {
				temp.add(this.getAbility(abilityName));
			} catch (NullPointerException n) {

			}
		}

		return temp;
	}

	public Ability getAbility(String abilityName) {
		Ability a = this.activatedAbility.getAbility(abilityName);

		if (a != null) {
			return a;
		}
		a = this.ability.getAbility(abilityName);

		if (a != null) {
			return a;
		}

		return null;
	}
}
