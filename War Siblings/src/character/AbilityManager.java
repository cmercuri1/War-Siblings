/** War Siblings
 * AbilityManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import event_classes.EventObject;
import event_classes.Type;
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
	private ArrayList<Trait> characterTraits;
	private ArrayList<Ability> characterAbilities;

	private ArrayList<PerminentInjury> permaInjuries;
	private ArrayList<TemporaryInjury> tempInjuries;

	public AbilityManager(Observer o) {
		this.characterAbilities = new ArrayList<Ability>();
		this.characterTraits = new ArrayList<Trait>();
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
				Trait sel = temp.get(GlobalManager.rng.nextInt(temp.size()));
				this.addTrait(sel);
				temp.remove(sel);
				if (!sel.getMutalExcl().isEmpty()) {
					for (String s : sel.getMutalExcl()) {
						temp.removeIf(t -> t.getName().equals(s));
					}
				}
			}
		}
	}

	public Ability getAbility(String abilityName) {
		for (Ability a : this.characterAbilities) {
			if (a.getName().equals(abilityName)) {
				return a;
			}
		}
		return null;
	}

	public ArrayList<Ability> getAbilities() {
		return this.characterAbilities;
	}

	public ArrayList<Trait> getTraits() {
		return this.characterTraits;
	}

	public void sufferTemporaryInjury(TemporaryInjury injury) {
		this.tempInjuries.add(injury);
		this.tempInjuries.get(injury).registerObserver(this);
		this.notifyOtherManagers(Type.ADD, injury);
	}

	public void healTemporaryInjuries() {
		this.tempInjuries.forEach(t -> t.healInjury());
	}

	public void sufferPerminentInjury(PerminentInjury injury) {
		this.permaInjuries.add(injury);

		this.notifyOtherManagers(Type.ADD, injury);
	}

	public void healPerminentInjuries() {
		this.permaInjuries.forEach(i -> this.notifyOtherManagers(Type.REMOVE, i));
		this.permaInjuries.clear();
	}

	public void addAbility(Ability ability) {
		this.characterAbilities.add(ability);
		this.notifyOtherManagers(Type.ADD, ability);
	}

	public void addTrait(Trait trait) {
		this.characterTraits.add(trait);
		this.notifyOtherManagers(Type.ADD, trait);
	}

	public void removeAbility(Ability ability) {
		if (this.characterAbilities.remove(ability))
			this.notifyOtherManagers(Type.REMOVE, ability);
	}

	public void removeTrait(Trait trait) {
		if (this.characterTraits.remove(trait))
			this.notifyOtherManagers(Type.REMOVE, trait);
	}

	public void removeAbility(String abilityName) {
		for (Ability a : this.characterAbilities) {
			if (a.getName().equals(abilityName)) {
				this.removeAbility(a);
				return;
			}
		}
	}

	private void notifyOtherManagers(Type type, Ability ability) {
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
		System.out.println("Character Traits:");
		this.characterTraits.forEach(a -> a.display());
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
				} else if (event.getInformation() instanceof Trait) {
					this.addTrait((Trait) event.getInformation());
				} else {
					this.addAbility((Ability) event.getInformation());
				}
				break;
			case REMOVE:
				if (event.getInformation() instanceof Trait) {
					this.removeTrait((Trait) event.getInformation());
				} else {
					this.removeAbility((Ability) event.getInformation());
				}
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
