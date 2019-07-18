package character;

import java.util.ArrayList;

import common_classes.Ability;
import common_classes.Effect;
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

	public AbilityManager(BackgroundGenerator background, Observer o) {
		this.characterAbilities = new ArrayList<Ability>();
		this.setUpObservers();
		this.registerObserver(o);
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

	public void addAbility(Ability ability) {
		this.characterAbilities.add(ability);

		for (Effect t : ability.getEffects()) {
			if (t.getAffectedManager().equals("Attribute")) {
				this.notifyObservers(new EventObject(Target.ATTRIBUTE, EventType.ADD, t, null));
			} else if (t.getAffectedManager().equals("Morale")) {
				this.notifyObservers(new EventObject(Target.MORALE, EventType.ADD, t, null));
			}
		}
	}
	
	public void removeAbility(Ability ability) {
		this.characterAbilities.remove(ability);
		
		for (Effect t : ability.getEffects()) {
			if (t.getAffectedManager().equals("Attribute")) {
				this.notifyObservers(new EventObject(Target.ATTRIBUTE, EventType.REMOVE, t, null));
			} else if (t.getAffectedManager().equals("Morale")) {
				this.notifyObservers(new EventObject(Target.MORALE, EventType.REMOVE, t, null));
			}
		}
	}

	public void removeAbility(String abilityName) {
		for (Ability a : this.characterAbilities) {
			if (a.getName().equals(abilityName)) {
				this.removeAbility(a);
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

	@Override
	public void onEventHappening(EventObject information) {
		switch (information.getTask().value) {
		case 1:
			this.addAbility((Ability) information.getInformation());
			break;
		case 2:
			this.removeAbility((Ability) information.getInformation());
			break;
		} 
	}
}
