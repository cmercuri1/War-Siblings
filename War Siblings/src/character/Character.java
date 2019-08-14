/** War Siblings
 * Character class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import javax.swing.ImageIcon;

import event_classes.CharacterEvent;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;
import listener_interfaces.CharacterListener;
import notifier_interfaces.CharacterNotifier;
import storage_classes.ArrayList;

/**
 * Class that uses generators to generate a player usable character as well as
 * observes and maintains all the managers that help run the character
 */
public class Character implements CharacterNotifier, CharacterListener {
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

	/** New Character with random background */
	public Character() {
		this.generalSetUp();
	}

	protected void generalSetUp() {
		this.characterListeners = new ArrayList<CharacterListener>();

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
	}

	protected void makeCharacter(BackgroundGenerator bg) {
		this.backgroundName = bg.getBackground();
		this.bgIcon = new ImageIcon("res/images/Backgrounds/" + this.backgroundName + ".png");

		this.am.setUpAttributes(bg);
		this.abm.setUpAbilities(bg);
		this.im.setUpInventory(bg);

		this.notifyCharacterListeners(new CharacterEvent(CharacterEvent.Task.FINISHED_CHARACTER, this, this));
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
}
