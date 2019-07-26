/** War Siblings
 * Character class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import event_classes.EventObject;
import event_classes.GenericObservee;
import event_classes.Observer;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;

/**
 * Class that uses generators to generate a player usable character as well as
 * observes and maintains all the managers that help run the character
 */
public class Character extends GenericObservee implements Observer {
	private String charName;
	private String charTitle;
	private String backgroundName;

	// Single Attribute manager handles attributes
	private AttributeManager am;
	private InventoryManager im;
	private AbilityManager abm;
	private MoraleManager mm;
	private BattleManager bm;

	/** New Character with specific background */
	public Character(String background) {
		this.generalSetUp(GlobalManager.characters.getBackground(background));
	}

	/** New Character with random background */
	public Character() {
		this.generalSetUp(GlobalManager.characters.getRandomBackground());
	}

	private void generalSetUp(BackgroundGenerator bg) {
		this.backgroundName = bg.getBackground();
		this.setUpObservers();

		this.im = new InventoryManager(this);
		this.observerObjects.add(this.im);

		this.am = new AttributeManager(bg, this);
		this.observerObjects.add(am);

		this.mm = new MoraleManager(this);
		this.observerObjects.add(mm);

		this.abm = new AbilityManager(this);
		this.observerObjects.add(abm);

		this.bm = new BattleManager(this);
		this.observerObjects.add(bm);

		this.abm.setUpAbilities(bg);
	}

	@Override
	public void onEventHappening(EventObject event) {
		switch (event.getTarget()) {
		case ABILITY:
			this.notifyObserver(abm, event);
			break;
		case ATTRIBUTE:
			this.notifyObserver(am, event);
			break;
		case MORALE:
			this.notifyObserver(mm, event);
			break;
		case BATTLE:
			this.notifyObserver(bm, event);
			break;
		case INVENTORY:
			this.notifyObserver(im, event);
			break;
		case UNDEFINED:
			switch (event.getTask()) {
			case GOT:
				this.notifyObserver(event.getRequester(), event);
				break;
			case GOT_OTHER:
				this.notifyObserver(event.getRequester(), event);
				break;
			default:
				break;
			}
			break;
		case CHARACTER:
			switch (event.getTask()) {
			case LEVEL_UP:
				// TODO Notify UI about level up and new perk choice
				break;
			case APPLY_LEVEL_UP:
				this.notifyObserver(this.am, event);
				break;
			default:
				break;
			}
			break;
		case UI:
			break;
		default:
			break;
		}
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
}
