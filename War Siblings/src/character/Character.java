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
		
		this.abm = new AbilityManager(bg, this);
		this.observerObjects.add(abm);
		
		this.bm = new BattleManager(this);
		this.observerObjects.add(bm);
		
		this.mm.setUp();
	}

	@Override
	public void onEventHappening(EventObject information) {
		switch (information.getTarget().value) {
		case 1: 
			this.notifyObserver(abm, information);
			break;
		case 2:
			this.notifyObserver(am, information);
			break;
		case 3:
			this.notifyObserver(mm, information);
			break;
		case 4:
			this.notifyObserver(bm, information);
			break;
		case 5:
			this.notifyObserver(im, information);
			break;
		case 6:
			switch (information.getTask().value) {
			case 4:
				this.notifyObserver(information.getRequester(), information);
			}
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
