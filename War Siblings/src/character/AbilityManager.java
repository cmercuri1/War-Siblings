package character;

import java.util.ArrayList;

import common_classes.Ability;
import common_classes.Effect;
import common_classes.PerminentInjury;
import common_classes.TemporaryInjury;
import common_classes.Trait;
import event_classes.EventObject;
import event_classes.EventType;
import event_classes.GenericObservee;
import event_classes.Observer;
import event_classes.Target;
import global_generators.BackgroundGenerator;

import global_managers.GlobalManager;

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

		this.notifyOtherManagers(EventType.ADD, injury);
	}
	
	public void healTemporaryInjuries() {
		for (TemporaryInjury temp : this.tempInjuries) {
			temp.checkForHealed();
			if (temp.isHealed()) {
				this.notifyOtherManagers(EventType.REMOVE, temp);
			}
		}
		this.tempInjuries.removeIf(i -> i.isHealed());
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
		this.characterAbilities.remove(ability);

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
	public void onEventHappening(EventObject information) {
		switch (information.getTask()) {
		case ADD:
			if (information.getInformation() instanceof TemporaryInjury) {
				this.sufferTemporaryInjury((TemporaryInjury) information.getInformation());
			} else if (information.getInformation() instanceof PerminentInjury) {
				this.sufferPerminentInjury((PerminentInjury) information.getInformation());
			} else {
				this.addAbility((Ability) information.getInformation());
			}
			break;
		case REMOVE:
			this.removeAbility((Ability) information.getInformation());
			break;
		default:
			break;
		}
	}
}
