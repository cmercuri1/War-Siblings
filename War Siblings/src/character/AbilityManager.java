/** War Siblings
 * AbilityManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import event_classes.AbilityEvent;
import event_classes.BattleControlEvent;
import event_classes.EffectEvent;
import event_classes.ModifierEvent;
import event_classes.PermanentInjuryEvent;
import event_classes.RoundControlEvent;
import event_classes.TemporaryInjuryEvent;
import event_classes.TraitEvent;
import event_classes.TriggeredEffectEvent;
import event_classes.TurnControlEvent;
import global_generators.BackgroundGenerator;

import global_managers.GlobalManager;
import listener_interfaces.AbilityListener;
import listener_interfaces.BattleControlListener;
import listener_interfaces.ModifierListener;
import listener_interfaces.PermanentInjuryListener;
import listener_interfaces.RoundControlListener;
import listener_interfaces.TemporaryInjuryListener;
import listener_interfaces.TraitListener;
import listener_interfaces.TriggeredEffectListener;
import listener_interfaces.TurnControlListener;
import notifier_interfaces.ModifierNotifier;
import notifier_interfaces.MultiNotifier;
import storage_classes.Ability;
import storage_classes.ArrayList;
import storage_classes.PermanentInjury;
import storage_classes.TemporaryInjury;
import storage_classes.Trait;

/**
 * A manager class that handles all the abilities a character may have, either
 * from items, traits or from level abilities
 */
public class AbilityManager implements AbilityListener, TraitListener, PermanentInjuryListener, TemporaryInjuryListener,
		TriggeredEffectListener, BattleControlListener, TurnControlListener, RoundControlListener, MultiNotifier,
		ModifierNotifier {

	protected ArrayList<Ability> characterAbilities;
	protected ArrayList<Trait> characterTraits;
	protected ArrayList<PermanentInjury> permaInjuries;
	protected ArrayList<TemporaryInjury> tempInjuries;

	protected ArrayList<ModifierListener> effectListeners;

	public AbilityManager() {
		this.characterAbilities = new ArrayList<Ability>();
		this.characterTraits = new ArrayList<Trait>();
		this.permaInjuries = new ArrayList<PermanentInjury>();
		this.tempInjuries = new ArrayList<TemporaryInjury>();

		this.setUpListeners();
	}

	@Override
	public void setUpListeners() {
		this.effectListeners = new ArrayList<ModifierListener>();
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
	protected void traitDetermining(ArrayList<String> excludedTraits) {
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

	protected void notifyOtherManagers(EffectEvent.Task task, Ability ability) {
		//TODO
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
			while (!this.characterAbilities.isEmpty()) {
				this.removeAbility(this.characterAbilities.get(this.characterAbilities.size() - 1));
			}
			break;
		}
	}

	@Override
	public void onTraitEvent(TraitEvent t) {
		switch (t.getTask()) {
		case ADD:
			this.addTrait(t.getInformation());
			break;
		case REMOVE:
			this.removeTrait(t.getInformation());
			break;
		case REMOVE_ALL:
			while (!this.characterTraits.isEmpty()) {
				this.removeTrait(this.characterTraits.get(this.characterTraits.size() - 1));
			}
			break;
		}
	}

	@Override
	public void onTriggeredEffectEvent(TriggeredEffectEvent t) {
		switch (t.getTask()) {
		case APPLY:
			break;
		case DAMAGE:
			break;
		case IMPEDE:
			break;
		case REMOVE:
			break;
		}
	}

	@Override
	public void onRoundControlEvent(RoundControlEvent r) {
		switch (r.getTask()) {
		case END_ROUND:
			break;
		case START_ROUND:
			break;
		}
	}

	@Override
	public void onTurnControlEvent(TurnControlEvent t) {
		switch (t.getTask()) {
		case END_TURN:
			break;
		case START_TURN:
			break;
		}
	}

	@Override
	public void onBattleControlEvent(BattleControlEvent b) {
		switch (b.getTask()) {
		case END_BATTLE:
			break;
		case START_BATTLE:
			break;
		}
	}

	@Override
	public void addModifierListener(ModifierListener e) {
		this.effectListeners.add(e);
	}

	@Override
	public void removeModifierListener(ModifierListener e) {
		this.effectListeners.remove(e);
	}

	@Override
	public void notifyModifierListeners(ModifierEvent e) {
		this.effectListeners.forEach(l -> l.onModifierEvent(e));
	}

	@Override
	public void notifyModifierListener(ModifierListener l, ModifierEvent e) {
		this.effectListeners.get(l).onModifierEvent(e);
	}
}
