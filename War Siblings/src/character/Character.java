package character;

import common_classes.Ability;
import common_classes.Effect;
import common_classes.Observer;
import event_classes.EventAbility;
import event_classes.EventAbilityType;
import event_classes.EventMorale;
import event_classes.EventObject;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;

/**
 * Class that uses generators to generate a player usable character as well as
 * observes and maintains all the managers that help run the character
 */
public class Character implements Observer {
	private String charName;
	private String charTitle;
	private String backgroundName;

	// Single Attribute manager handles attributes
	private AttributeManager am;
	private InventoryManager im;
	private AbilityManager abm;
	private MoraleManager mm;

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

		this.im = new InventoryManager(this);
		this.am = new AttributeManager(bg, this);
		this.abm = new AbilityManager(bg, this);
		this.mm = new MoraleManager(this);
	}

	public void onEventHappening(EventObject information) {
		if (information instanceof EventMorale) {
			this.onEventMoraleHappening((EventMorale) information);
		} else if (information instanceof EventAbility) {
			this.onEventAbilityHappening((EventAbility) information);
		}
	}

	public void onEventMoraleHappening(EventMorale morale) {
		try {
			this.abm.removeAbility(GlobalManager.morale.getMoraleAbility(morale.getInformation()[0]));
		} catch (NullPointerException nu) {
		}
		this.abm.addAbility(GlobalManager.morale.getMoraleAbility(morale.getInformation()[1]));
	}

	public void onEventAbilityHappening(EventAbility event) {
		if (event.getTask().equals(EventAbilityType.ADD)) {
			Ability temp = (Ability) event.getInformation();

			for (Effect t : temp.getEffects()) {
				if (t.getAffectedManager().equals("Attribute")) {
					this.am.addModifier(t);
				}
			}
		} else if (event.getTask().equals(EventAbilityType.REMOVE)) {
			Ability temp = (Ability) event.getInformation();

			for (Effect t : temp.getEffects()) {
				if (t.getAffectedManager().equals("Attribute")) {
					this.am.removeModifier(t);
				}
			}
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
