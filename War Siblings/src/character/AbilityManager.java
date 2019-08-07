/** War Siblings
 * AbilityManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import event_classes.AbilityEvent;
import event_classes.EffectEvent;
import event_classes.PermanentInjuryEvent;
import event_classes.TemporaryInjuryEvent;
import global_generators.BackgroundGenerator;

import global_managers.GlobalManager;
import listener_interfaces.AbilityListener;
import listener_interfaces.EffectListener;
import listener_interfaces.MultiListener;
import listener_interfaces.PermanentInjuryListener;
import listener_interfaces.TemporaryInjuryListener;
import notifier_interfaces.EffectNotifier;
import storage_classes.Ability;
import storage_classes.ArrayList;
import storage_classes.Effect;
import storage_classes.PermanentInjury;
import storage_classes.TemporaryInjury;
import storage_classes.Trait;

/**
 * A manager class that handles all the abilities a character may have, either
 * from items, traits or from level abilities
 */
public class AbilityManager
		implements MultiListener, AbilityListener, PermanentInjuryListener, TemporaryInjuryListener, EffectNotifier {
	protected ArrayList<Trait> characterTraits;
	protected ArrayList<Ability> characterAbilities;
	protected ArrayList<PermanentInjury> permaInjuries;
	protected ArrayList<TemporaryInjury> tempInjuries;

	protected ArrayList<EffectListener> effectListeners;
	
	public AbilityManager() {
		this.characterAbilities = new ArrayList<Ability>();
		this.characterTraits = new ArrayList<Trait>();
		this.permaInjuries = new ArrayList<PermanentInjury>();
		this.tempInjuries = new ArrayList<TemporaryInjury>();

		this.setUpListeners();
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
		injury.addTemporaryInjuryListener(this);
		this.tempInjuries.add(injury);
		this.notifyOtherManagers(EffectEvent.Task.ADD, injury);
	}
	
	public void healTemporaryInjuries() {
		this.tempInjuries.forEach(t -> t.healInjury());
	}

	public void sufferPermanentInjury(PermanentInjury injury) {
		injury.addPermanentInjuryListener(this);
		this.permaInjuries.add(injury);
		this.notifyOtherManagers(EffectEvent.Task.ADD, injury);
	}
	
	public void healPermanentInjury(PermanentInjury injury) {
		injury.removePermanentInjuryListener(this);
		this.permaInjuries.remove(injury);
		this.notifyOtherManagers(EffectEvent.Task.ADD, injury);
	}

	public void healPermanentInjuries() {
		this.permaInjuries.forEach(i -> this.healPermanentInjury(i));
	}

	public void addAbility(Ability ability) {
		this.characterAbilities.add(ability);
		this.notifyOtherManagers(EffectEvent.Task.ADD, ability);
	}

	public void addTrait(Trait trait) {
		this.characterTraits.add(trait);
		this.notifyOtherManagers(EffectEvent.Task.ADD, trait);
	}

	public void removeAbility(Ability ability) {
		if (this.characterAbilities.remove(ability))
			this.notifyOtherManagers(EffectEvent.Task.REMOVE, ability);
	}

	public void removeTrait(Trait trait) {
		if (this.characterTraits.remove(trait))
			this.notifyOtherManagers(EffectEvent.Task.REMOVE, trait);
	}

	public void removeAbility(String abilityName) {
		for (Ability a : this.characterAbilities) {
			if (a.getName().equals(abilityName)) {
				this.removeAbility(a);
				return;
			}
		}
	}

	private void notifyOtherManagers(EffectEvent.Task task, Ability ability) {
		for (Effect t : ability.getEffects()) {
			this.notifyEffectListeners(new EffectEvent(task, t, this));
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
	public void onTemporaryInjuryEvent(TemporaryInjuryEvent t) {
		switch (t.getTask()) {
		case ADD:
			this.sufferTemporaryInjury(t.getInformation());
			break;
		case HEALED:
			t.getInformation().removeTemporaryInjuryListener(this);
			this.tempInjuries.remove(t.getInformation());
			break;
		case HEAL_ALL:
			this.healTemporaryInjuries();
			break;		
		}
	}

	@Override
	public void onPermanentInjuryEvent(PermanentInjuryEvent p) {
		switch (p.getTask()) {
		case ADD:
			this.sufferPermanentInjury(p.getInformation());
			break;
		case HEALED:
			this.healPermanentInjury(p.getInformation());
			break;
		case HEAL_ALL:
			this.healPermanentInjuries();
			break;
		}
	}

	@Override
	public void onAbilityEvent(AbilityEvent a) {
		switch (a.getTask()) {
		case ADD:
			this.addAbility(a.getInformation());
			break;
		case REMOVE:
			this.removeAbility(a.getInformation());
			break;
		case REMOVE_ALL:
			this.characterAbilities.forEach(c -> this.removeAbility(c));
			break;
		}
	}

	@Override
	public void setUpListeners() {
		this.effectListeners = new ArrayList<EffectListener>();
	}

	@Override
	public void addEffectListener(EffectListener e) {
		this.effectListeners.add(e);		
	}

	@Override
	public void removeEffectListener(EffectListener e) {
		this.effectListeners.remove(e);
	}

	@Override
	public void notifyEffectListeners(EffectEvent e) {
		this.effectListeners.forEach(l -> l.onEffectEvent(e));
	}
}
