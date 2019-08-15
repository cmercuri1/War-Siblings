/** War Siblings
 * Character class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import javax.swing.ImageIcon;

import event_classes.AbilityEvent;
import event_classes.CharacterEvent;
import event_classes.CharacterInventoryEvent;
import event_classes.TraitEvent;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;
import listener_interfaces.AbilityListener;
import listener_interfaces.CharacterInventoryListener;
import listener_interfaces.CharacterListener;
import listener_interfaces.TraitListener;
import notifier_interfaces.AbilityNotifier;
import notifier_interfaces.CharacterInventoryNotifier;
import notifier_interfaces.CharacterNotifier;
import notifier_interfaces.TraitNotifier;
import storage_classes.ArrayList;

/**
 * Class that uses generators to generate a player usable character as well as
 * observes and maintains all the managers that help run the character
 */
public class Character
		implements CharacterNotifier, CharacterInventoryNotifier, AbilityNotifier, TraitNotifier, CharacterListener {
	protected String charName;
	protected String charTitle;
	protected String backgroundName;
	protected ImageIcon bgIcon;

	// Single Attribute manager handles attributes
	protected AttributeManager am;
	protected InventoryManager im;
	protected AbilityManager abm;
	protected MoraleManager mm;
	protected BattleManager bm;

	protected ArrayList<CharacterListener> characterListeners;
	protected ArrayList<CharacterInventoryListener> cInventoryListeners;
	protected ArrayList<AbilityListener> abilityListeners;
	protected ArrayList<TraitListener> traitListeners;

	/** New Character with random background */
	public Character() {
		this.generalSetUp();
	}

	protected void generalSetUp() {
		this.characterListeners = new ArrayList<CharacterListener>();
		this.cInventoryListeners = new ArrayList<CharacterInventoryListener>();
		this.abilityListeners = new ArrayList<AbilityListener>();
		this.traitListeners = new ArrayList<TraitListener>();

		this.im = new InventoryManager();
		this.am = new AttributeManager();
		this.mm = new MoraleManager();
		this.abm = new AbilityManager();
		this.bm = new BattleManager();

		this.assignListeners();

	}

	protected void assignListeners() {
		this.im.addTraitListener(this.abm);
		this.im.addEffectListener(this.am);

		this.am.addCharacterInventoryListener(this.im);
		this.am.addPostDataListener(this.bm);
		this.am.addPostDataListener(this.mm);

		this.mm.addAbilityListener(this.abm);
		this.mm.addMoraleListener(this.bm);
		this.mm.addRetrievalListener(this.am);

		this.abm.addEffectListener(this.am);
		this.abm.addEffectListener(this.bm);
		this.abm.addEffectListener(this.mm);

		this.bm.addBattleControlListener(this.mm);
		this.bm.addCombatListener(null);
		this.bm.addEffectListener(this.am);
		this.bm.addRetrievalListener(this.am);
		this.bm.addRoundControlListener(null);
		this.bm.addTurnControlListener(this.am);

		this.addAbilityListener(abm);
		this.addCharacterInventoryListener(im);
		this.addTraitListener(abm);
	}

	protected void makeCharacter(BackgroundGenerator bg) {
		this.backgroundName = bg.getBackground();
		this.bgIcon = new ImageIcon("res/images/Backgrounds/" + this.backgroundName + ".png");

		this.am.setUpAttributes(bg);
		this.abm.setUpAbilities(bg);
		this.im.setUpInventory(bg);

		this.notifyCharacterListeners(new CharacterEvent(CharacterEvent.Task.FINISHED_CHARACTER, this, this));
	}

	protected void resetCharacter() {
		this.notifyAbilityListeners(new AbilityEvent(AbilityEvent.Task.REMOVE_ALL, null, this));
		this.notifyCharacterInventoryListeners(
				new CharacterInventoryEvent(CharacterInventoryEvent.Task.REMOVE_ALL, null, this));
		this.notifyTraitListeners(new TraitEvent(TraitEvent.Task.REMOVE_ALL, null, this));
	}

	public String getCharName() {
		return this.charName;
	}

	public String getCharTitle() {
		return this.charTitle;
	}

	public String getBackgroundName() {
		return this.backgroundName;
	}

	public ImageIcon getBgIcon() {
		return this.bgIcon;
	}

	public AttributeManager getAm() {
		return this.am;
	}

	public InventoryManager getIm() {
		return this.im;
	}

	public AbilityManager getAbm() {
		return this.abm;
	}

	public MoraleManager getMm() {
		return this.mm;
	}

	public BattleManager getBm() {
		return this.bm;
	}

	public void display() {
		System.out.println("New Character!");
		System.out.println("Background is " + this.backgroundName);

		this.am.display();
		this.im.display();
		this.abm.display();
		this.mm.display();
		System.out.println();
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

	@Override
	public void onCharacterEvent(CharacterEvent c) {
		switch (c.getTask()) {
		case CHANGED_CHARACTER:
			this.resetCharacter();
			if (c.getInformation().equals("Random")) {
				this.makeCharacter(GlobalManager.backgrounds.getRandomBackground());
			} else {
				this.makeCharacter(GlobalManager.backgrounds.getBackground((String) c.getInformation()));
			}
			break;
		case FINISHED_CHARACTER:
			break;
		}
	}

	@Override
	public void addAbilityListener(AbilityListener a) {
		this.abilityListeners.add(a);
	}

	@Override
	public void removeAbilityListener(AbilityListener a) {
		this.abilityListeners.remove(a);
	}

	@Override
	public void notifyAbilityListeners(AbilityEvent a) {
		this.abilityListeners.forEach(l -> l.onAbilityEvent(a));
	}

	@Override
	public void notifyAbilityListener(AbilityListener a, AbilityEvent e) {
		this.abilityListeners.get(a).onAbilityEvent(e);
	}

	@Override
	public void addCharacterInventoryListener(CharacterInventoryListener c) {
		this.cInventoryListeners.add(c);
	}

	@Override
	public void removeCharacterInventoryListener(CharacterInventoryListener c) {
		this.cInventoryListeners.remove(c);
	}

	@Override
	public void notifyCharacterInventoryListeners(CharacterInventoryEvent c) {
		this.cInventoryListeners.forEach(l -> l.onCharacterInventoryEvent(c));
	}

	@Override
	public void notifyCharacterInventoryListener(CharacterInventoryListener c, CharacterInventoryEvent e) {
		this.cInventoryListeners.get(c).onCharacterInventoryEvent(e);
	}

	@Override
	public void addTraitListener(TraitListener t) {
		this.traitListeners.add(t);
	}

	@Override
	public void removeTraitListener(TraitListener t) {
		this.traitListeners.remove(t);
	}

	@Override
	public void notifyTraitListeners(TraitEvent t) {
		this.traitListeners.forEach(l -> l.onTraitEvent(t));
	}

	@Override
	public void notifyTraitListener(TraitListener t, TraitEvent e) {
		this.traitListeners.get(t).onTraitEvent(e);
	}
}
