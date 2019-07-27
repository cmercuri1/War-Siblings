/** War Siblings
 * AbilityManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.GenericObservee;
import event_classes.Observer;
import event_classes.Target;
import global_generators.BackgroundGenerator;

import global_managers.GlobalManager;
import storage_classes.Ability;
import storage_classes.ArrayList;
import storage_classes.Effect;
import storage_classes.PerminentInjury;
import storage_classes.TemporaryInjury;
import storage_classes.Trait;

/**
 * A manager class that handles all the abilities a character may have, either
 * from items, traits or from level abilities
 */
public class AbilityManager extends GenericObservee implements Observer {
	private ArrayList<Ability> characterAbilities;
	private ArrayList<PerminentInjury> permaInjuries;
	private ArrayList<TemporaryInjury> tempInjuries;

	public AbilityManager(Observer o) {
		this.characterAbilities = new ArrayList<Ability>();
		this.permaInjuries = new ArrayList<PerminentInjury>();
		this.tempInjuries = new ArrayList<TemporaryInjury>();

		this.setUpObservers();
		this.registerObserver(o);
	}

	public void setUpAbilities(BackgroundGenerator background) {
		if (background.getBgAbility() != null) {
			this.addAbility(background.getBgAbility());
		}
		this.traitDetermining(background.getExcludedTraits());
	}

	/**
	 * Gets Traits and picks up to two for the character, then ensures effects from
	 * traits are applied
	 */
	private void traitDetermining(ArrayList<String> excludedTraits) {
		ArrayList<Trait> temp = GlobalManager.traits.getSpecificTraitList(excludedTraits);

		// Roll for up to two different traits, with 50% chance each time? If a trait is
		// gained, then we remove any mutually exclusive traits. e.g. if "Huge" is
		// gained, "Tiny" should not be able to be rolled
		for (int i = 0; i < 2; i++) {
			if (GlobalManager.d100Roll() < 51) {
				int pos = GlobalManager.rng.nextInt(temp.size());
				Trait sel = temp.get(pos);
				this.addAbility(sel);
				temp.remove(sel);
				if (!sel.getMutalExcl().isEmpty()) {
					for (String s : sel.getMutalExcl()) {
						temp.removeIf(t -> t.getName().equals(s));
					}
				}
			}
		}
	}

	public ArrayList<Ability> getAbilities() {
		return this.characterAbilities;
	}

	public void sufferTemporaryInjury(TemporaryInjury injury) {
		this.tempInjuries.add(injury);
		this.tempInjuries.get(injury).registerObserver(this);
		this.notifyOtherManagers(EventType.ADD, injury);
	}

	public void healTemporaryInjuries() {
		this.tempInjuries.forEach(t -> t.healInjury());
	}

	public void sufferPerminentInjury(PerminentInjury injury) {
		this.permaInjuries.add(injury);

		this.notifyOtherManagers(EventType.ADD, injury);
	}

	public void healPerminentInjuries() {
		this.permaInjuries.forEach(i -> this.notifyOtherManagers(EventType.REMOVE, i));
		this.permaInjuries.clear();
	}

	public void addAbility(Ability ability) {
		this.characterAbilities.add(ability);
		this.notifyOtherManagers(EventType.ADD, ability);
	}

	public void removeAbility(Ability ability) {
		if (this.characterAbilities.remove(ability))
			this.notifyOtherManagers(EventType.REMOVE, ability);
	}

	public void removeAbility(String abilityName) {
		for (Ability a : this.characterAbilities) {
			if (a.getName().equals(abilityName)) {
				this.removeAbility(a);
				return;
			}
		}
	}

	private void notifyOtherManagers(EventType type, Ability ability) {
		for (Effect t : ability.getEffects()) {
			if (t.getAffectedManager().equals("Attribute")) {
				this.notifyObservers(new EventObject(Target.ATTRIBUTE, type, t, null));
			} else if (t.getAffectedManager().equals("Morale")) {
				this.notifyObservers(new EventObject(Target.MORALE, type, t, null));
			} else if (t.getAffectedManager().equals("Battle")) {
				this.notifyObservers(new EventObject(Target.BATTLE, type, t, null));
			}
		}
	}

	public void display() {
		System.out.println("Character Abilities:");
		this.characterAbilities.forEach(a -> a.display());
		System.out.println("Temporary Injuries:");
		this.tempInjuries.forEach(a -> a.display());
		System.out.println("Perminent Injuries:");
		this.permaInjuries.forEach(a -> a.display());
	}

	@Override
	public void onEventHappening(EventObject event) {
		switch (event.getTarget()) {
		case ABILITY:
			switch (event.getTask()) {
			case ADD:
				if (event.getInformation() instanceof TemporaryInjury) {
					this.sufferTemporaryInjury((TemporaryInjury) event.getInformation());
				} else if (event.getInformation() instanceof PerminentInjury) {
					this.sufferPerminentInjury((PerminentInjury) event.getInformation());
				} else {
					this.addAbility((Ability) event.getInformation());
				}
				break;
			case REMOVE:
				this.removeAbility((Ability) event.getInformation());
				break;
			case HEALED:
				this.tempInjuries.remove(event.getRequester());
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
}
