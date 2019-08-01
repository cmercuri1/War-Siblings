/** War Siblings
 * Character class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import javax.swing.ImageIcon;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.GenericObservee;
import event_classes.Observer;
import event_classes.Target;
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
	private ImageIcon bgIcon;

	// Single Attribute manager handles attributes
	private AttributeManager am;
	private InventoryManager im;
	private AbilityManager abm;
	private MoraleManager mm;
	private BattleManager bm;

	/** New Character with specific background */
	public Character(String background, Observer o) {
		this.generalSetUp(GlobalManager.backgrounds.getBackground(background), o);
	}

	/** New Character with random background */
	public Character(Observer o) {
		this.generalSetUp(GlobalManager.backgrounds.getRandomBackground(), o);
	}

	private void generalSetUp(BackgroundGenerator bg, Observer o) {
		this.backgroundName = bg.getBackground();
		this.bgIcon = new ImageIcon("res/images/Backgrounds/" + this.backgroundName + ".png");
		this.setUpObservers();
		this.observerObjects.add(o);

		this.im = new InventoryManager(this);
		this.observerObjects.add(this.im);

		this.am = new AttributeManager(bg, this);
		this.observerObjects.add(this.am);

		this.mm = new MoraleManager(this);
		this.observerObjects.add(this.mm);

		this.abm = new AbilityManager(this);
		this.observerObjects.add(this.abm);

		this.bm = new BattleManager(this);
		this.observerObjects.add(this.bm);

		this.im.setUpInventory(bg);
		this.abm.setUpAbilities(bg);
		this.notifyObservers(new EventObject(Target.UI, EventType.FINISHED_GENERATING, this, null));
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
}
