/** War Siblings
 * AbilityManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import abilities.Ability;
import abilities.PassiveAbility;
import abilities.PermanentInjury;
import abilities.TemporaryInjury;
import abilities.Trait;
import effect_classes.Effect;
import effect_classes.Effect_Modifier;
import effect_classes.Effect_Triggered;
import effect_classes.Modifier;
import event_classes.AbilityEvent;
import event_classes.BattleControlEvent;
import event_classes.CharacterEvent;
import event_classes.InventorySituationEvent;
import event_classes.ModifierEvent;
import event_classes.MoraleChangeEvent;
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
import listener_interfaces.CharacterListener;
import listener_interfaces.InventorySituationListener;
import listener_interfaces.ModifierListener;
import listener_interfaces.MoraleChangeListener;
import listener_interfaces.PermanentInjuryListener;
import listener_interfaces.RoundControlListener;
import listener_interfaces.TemporaryInjuryListener;
import listener_interfaces.TraitListener;
import listener_interfaces.TriggeredEffectListener;
import listener_interfaces.TurnControlListener;
import notifier_interfaces.BattleControlNotifier;
import notifier_interfaces.CharacterNotifier;
import notifier_interfaces.InventorySituationNotifier;
import notifier_interfaces.ModifierNotifier;
import notifier_interfaces.MoraleChangeNotifier;
import notifier_interfaces.MultiNotifier;
import notifier_interfaces.RoundControlNotifier;
import notifier_interfaces.TurnControlNotifier;
import storage_classes.ArrayList;
import storage_classes.MoraleState;

/**
 * A manager class that handles all the abilities a character may have, either
 * from items, traits or from level abilities
 */
public class AbilitiesManager implements AbilityListener, TraitListener, PermanentInjuryListener, TemporaryInjuryListener,
		TriggeredEffectListener, BattleControlListener, TurnControlListener, RoundControlListener, MoraleChangeListener,
		InventorySituationListener, MultiNotifier, ModifierNotifier, BattleControlNotifier, TurnControlNotifier,
		RoundControlNotifier, MoraleChangeNotifier, InventorySituationNotifier, CharacterNotifier {

	protected ArrayList<Ability> characterAbilities;
	protected ArrayList<Trait> characterTraits;
	protected ArrayList<PermanentInjury> permaInjuries;
	protected ArrayList<TemporaryInjury> tempInjuries;

	protected ArrayList<ModifierListener> modifierListeners;

	protected ArrayList<BattleControlListener> battleControlListeners;
	protected ArrayList<RoundControlListener> roundControlListeners;
	protected ArrayList<TurnControlListener> turnControlListeners;
	protected ArrayList<MoraleChangeListener> moraleChangeListeners;
	protected ArrayList<InventorySituationListener> inventorySituationListeners;
	protected ArrayList<CharacterListener> characterListeners;

	public AbilitiesManager() {
		this.characterAbilities = new ArrayList<Ability>();
		this.characterTraits = new ArrayList<Trait>();
		this.permaInjuries = new ArrayList<PermanentInjury>();
		this.tempInjuries = new ArrayList<TemporaryInjury>();

		this.setUpListeners();
	}

	@Override
	public void setUpListeners() {
		this.modifierListeners = new ArrayList<ModifierListener>();
		this.battleControlListeners = new ArrayList<BattleControlListener>();
		this.roundControlListeners = new ArrayList<RoundControlListener>();
		this.turnControlListeners = new ArrayList<TurnControlListener>();
		this.moraleChangeListeners = new ArrayList<MoraleChangeListener>();
		this.inventorySituationListeners = new ArrayList<InventorySituationListener>();
		this.characterListeners = new ArrayList<CharacterListener>();
	}

	public void setUpAbilities(BackgroundGenerator background) {
		if (background.getBgAbility() != null) {
			this.addPassiveAbility(background.getBgAbility());

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
		this.addEffectsToListeners(injury);
		this.tempInjuries.add(injury);
		this.notifyOtherManagers(ModifierEvent.Task.ADD, injury);
	}

	public void healTemporaryInjuries() {
		this.tempInjuries.forEach(t -> t.healInjury());
	}

	public void sufferPermanentInjury(PermanentInjury injury) {
		injury.addPermanentInjuryListener(this);
		this.addEffectsToListeners(injury);
		this.permaInjuries.add(injury);
		this.notifyOtherManagers(ModifierEvent.Task.ADD, injury);
	}

	public void healPermanentInjury(PermanentInjury injury) {
		this.removeEffectsFromListeners(injury);
		injury.removePermanentInjuryListener(this);
		this.permaInjuries.remove(injury);
		this.notifyOtherManagers(ModifierEvent.Task.ADD, injury);
	}

	public void healPermanentInjuries() {
		this.permaInjuries.forEach(i -> this.healPermanentInjury(i));
	}

	public void addAbility(Ability ability) {
		this.characterAbilities.add(ability);
		if (ability instanceof PassiveAbility) {
			this.addPassiveAbility((PassiveAbility) ability);
		}
	}

	public void addPassiveAbility(PassiveAbility ability) {
		this.addEffectsToListeners(ability);
		this.notifyOtherManagers(ModifierEvent.Task.ADD, ability);
	}

	public void addTrait(Trait trait) {
		if (!this.characterTraits.contains(trait)) {
			this.characterTraits.add(trait);
			this.addEffectsToListeners(trait);
			this.notifyOtherManagers(ModifierEvent.Task.ADD, trait);
		}
	}

	public void removeAbility(Ability ability) {
		if (this.characterAbilities.remove(ability)) {
			if (ability instanceof PassiveAbility) {
				this.removePassiveAbility((PassiveAbility) ability);
			}
		}
	}

	public void removePassiveAbility(PassiveAbility ability) {
		this.removeEffectsFromListeners(ability);
		this.notifyOtherManagers(ModifierEvent.Task.REMOVE, ability);
	}

	public void removeTrait(Trait trait) {
		if (this.characterTraits.remove(trait)) {
			this.removeEffectsFromListeners(trait);
			this.notifyOtherManagers(ModifierEvent.Task.REMOVE, trait);
		}
	}

	protected void addEffectsToListeners(PassiveAbility a) {
		for (Effect e : a.getEffects()) {
			if (e instanceof BattleControlListener) {
				this.addBattleControlListener((BattleControlListener) e);
			}
			if (e instanceof RoundControlListener) {
				this.addRoundControlListener((RoundControlListener) e);
			}
			if (e instanceof TurnControlListener) {
				this.addTurnControlListener((TurnControlListener) e);
			}
			if (e instanceof MoraleChangeListener) {
				this.addMoraleChangeListener((MoraleChangeListener) e);
			}
			if (e instanceof InventorySituationListener) {
				this.addInventorySituationListener((InventorySituationListener) e);
			}
			if (e instanceof Effect_Triggered) {
				((Effect_Triggered) e).addTriggeredEffectListener(this);
			}
		}
	}

	protected void removeEffectsFromListeners(PassiveAbility a) {
		for (Effect e : a.getEffects()) {
			if (e instanceof BattleControlListener) {
				this.removeBattleControlListener((BattleControlListener) e);
			}
			if (e instanceof RoundControlListener) {
				this.removeRoundControlListener((RoundControlListener) e);
			}
			if (e instanceof TurnControlListener) {
				this.removeTurnControlListener((TurnControlListener) e);
			}
			if (e instanceof MoraleChangeListener) {
				this.removeMoraleChangeListener((MoraleChangeListener) e);
			}
			if (e instanceof InventorySituationListener) {
				this.removeInventorySituationListener((InventorySituationListener) e);
			}
			if (e instanceof Effect_Triggered) {
				((Effect_Triggered) e).removeTriggeredEffectListener(this);
			}
		}
	}

	protected void notifyOtherManagers(ModifierEvent.Task task, PassiveAbility ability) {
		for (Effect e : ability.getEffects()) {
			if (e instanceof Effect_Modifier) {
				this.notifyModifierListeners(new ModifierEvent(task, ((Effect_Modifier) e).getMod(), this));
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
		this.notifyCharacterListeners(new CharacterEvent(CharacterEvent.Task.UPDATED_CHARACTER, null, this));
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
		this.notifyCharacterListeners(new CharacterEvent(CharacterEvent.Task.UPDATED_CHARACTER, null, this));
	}

	@Override
	public void onAbilityEvent(AbilityEvent a) {
		switch (a.getTask()) {
		case ADD:
			this.addAbility((Ability) a.getInformation());
			break;
		case REMOVE:
			this.removeAbility((Ability) a.getInformation());
			break;
		case REMOVE_ALL:
			while (!this.characterAbilities.isEmpty()) {
				this.removeAbility(this.characterAbilities.get(this.characterAbilities.size() - 1));
			}
			break;
		}
		this.notifyCharacterListeners(new CharacterEvent(CharacterEvent.Task.UPDATED_CHARACTER, null, this));
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
		this.notifyCharacterListeners(new CharacterEvent(CharacterEvent.Task.UPDATED_CHARACTER, null, this));
	}

	@Override
	public void onTriggeredEffectEvent(TriggeredEffectEvent t) {
		switch (t.getTask()) {
		case APPLY:
			this.notifyModifierListeners(
					new ModifierEvent(ModifierEvent.Task.ADD, (Modifier) t.getInformation(), this));
			break;
		case DAMAGE:
			// DAMAGE LISTENERs
			break;
		case IMPEDE:
			break;
		case REMOVE:
			this.notifyModifierListeners(
					new ModifierEvent(ModifierEvent.Task.REMOVE, (Modifier) t.getInformation(), this));
			break;
		case ADD_ABILITY:
			this.addAbility((Ability) t.getInformation());
			break;
		case REMOVE_ABILITY:
			this.removeAbility((Ability) t.getInformation());
			break;
		case MORALE_REPLACE:
			this.notifyMoraleChangeListeners(
					new MoraleChangeEvent(MoraleChangeEvent.Task.OVERRIDE, (MoraleState) t.getInformation(), this));
			break;
		case IGNORE_MORALE:
			//
			break;
		}
	}

	@Override
	public void onRoundControlEvent(RoundControlEvent r) {
		this.notifyRoundControlListeners(r);
	}

	@Override
	public void onTurnControlEvent(TurnControlEvent t) {
		this.notifyTurnControlListeners(t);
	}

	@Override
	public void onBattleControlEvent(BattleControlEvent b) {
		this.notifyBattleControlListeners(b);
	}

	@Override
	public void onMoraleChangeEvent(MoraleChangeEvent m) {
		this.notifyMoraleChangeListeners(m);
	}

	@Override
	public void onInventorySituationEvent(InventorySituationEvent i) {
		this.notifyInventorySituationListeners(i);
	}

	@Override
	public void addModifierListener(ModifierListener e) {
		this.modifierListeners.add(e);
	}

	@Override
	public void removeModifierListener(ModifierListener e) {
		this.modifierListeners.remove(e);
	}

	@Override
	public void notifyModifierListeners(ModifierEvent e) {
		this.modifierListeners.forEach(l -> l.onModifierEvent(e));
	}

	@Override
	public void notifyModifierListener(ModifierListener l, ModifierEvent e) {
		this.modifierListeners.get(l).onModifierEvent(e);
	}

	@Override
	public void addMoraleChangeListener(MoraleChangeListener m) {
		this.moraleChangeListeners.add(m);
	}

	@Override
	public void removeMoraleChangeListener(MoraleChangeListener m) {
		this.moraleChangeListeners.remove(m);
	}

	@Override
	public void notifyMoraleChangeListeners(MoraleChangeEvent m) {
		this.moraleChangeListeners.forEach(l -> l.onMoraleChangeEvent(m));
	}

	@Override
	public void notifyMoraleChangeListener(MoraleChangeListener m, MoraleChangeEvent e) {
		this.moraleChangeListeners.get(m).onMoraleChangeEvent(e);
	}

	@Override
	public void addBattleControlListener(BattleControlListener b) {
		this.battleControlListeners.add(b);
	}

	@Override
	public void removeBattleControlListener(BattleControlListener b) {
		this.battleControlListeners.remove(b);
	}

	@Override
	public void notifyBattleControlListeners(BattleControlEvent b) {
		this.battleControlListeners.forEach(l -> l.onBattleControlEvent(b));
	}

	@Override
	public void notifyBattleControlListener(BattleControlListener b, BattleControlEvent e) {
		this.battleControlListeners.get(b).onBattleControlEvent(e);
	}

	@Override
	public void addRoundControlListener(RoundControlListener r) {
		this.roundControlListeners.add(r);
	}

	@Override
	public void removeRoundControlListener(RoundControlListener r) {
		this.roundControlListeners.remove(r);
	}

	@Override
	public void notifyRoundControlListeners(RoundControlEvent r) {
		this.roundControlListeners.forEach(l -> l.onRoundControlEvent(r));
	}

	@Override
	public void notifyRoundControlListener(RoundControlListener r, RoundControlEvent e) {
		this.roundControlListeners.get(r).onRoundControlEvent(e);
	}

	@Override
	public void addTurnControlListener(TurnControlListener t) {
		this.turnControlListeners.add(t);
	}

	@Override
	public void removeTurnControlListener(TurnControlListener t) {
		this.turnControlListeners.remove(t);
	}

	@Override
	public void notifyTurnControlListeners(TurnControlEvent t) {
		this.turnControlListeners.forEach(l -> l.onTurnControlEvent(t));
	}

	@Override
	public void notifyTurnControlListener(TurnControlListener t, TurnControlEvent e) {
		this.turnControlListeners.get(t).onTurnControlEvent(e);
	}

	@Override
	public void addInventorySituationListener(InventorySituationListener i) {
		this.inventorySituationListeners.add(i);
	}

	@Override
	public void removeInventorySituationListener(InventorySituationListener i) {
		this.inventorySituationListeners.remove(i);
	}

	@Override
	public void notifyInventorySituationListeners(InventorySituationEvent i) {
		this.inventorySituationListeners.forEach(l -> l.onInventorySituationEvent(i));
	}

	@Override
	public void notifyInventorySituationListener(InventorySituationListener i, InventorySituationEvent e) {
		this.inventorySituationListeners.get(i).onInventorySituationEvent(e);
	}

	@Override
	public void addCharacterListener(CharacterListener c) {
		this.characterListeners.add(c);
	}

	@Override
	public void removeCharacterListener(CharacterListener c) {
		this.characterListeners.remove(c);
	}

	@Override
	public void notifyCharacterListeners(CharacterEvent c) {
		this.characterListeners.forEach(l -> l.onCharacterEvent(c));
	}

	@Override
	public void notifyCharacterListener(CharacterListener c, CharacterEvent e) {
		this.characterListeners.get(c).onCharacterEvent(e);
	}
}
