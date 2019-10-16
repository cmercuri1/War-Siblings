/** War Siblings
 * AbilityManager
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import abilities.Ability;

public class AllAbilitiesManager {
	protected ActiveAbilityManager activatedAbility;
	protected AbilityManager ability;
	
	public AllAbilitiesManager() {
		this.activatedAbility = new ActiveAbilityManager();
		this.ability = new AbilityManager();
	}

	public ActiveAbilityManager getActivatedAbility() {
		return this.activatedAbility;
	}
	
	public AbilityManager getAbility() {
		return this.ability;
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
