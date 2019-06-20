package character;

import common_classes.Ability;

/**
 * Class that uses generators to generate a player usable character as well as
 * has maintains all the managers that help run the character
 */
public class Character {
	private String backgroundName;

	// Single Attribute manager handles attributes
	private AttributeManager am;
	private InventoryManager im;
	private AbilityManager abm;
	private MoraleManager mm;

	/** New Character with specific background */
	public Character(String background) {
		BackgroundGenerator bg = new BackgroundGenerator(background);

		this.generalSetUp(bg);
	}

	/** New Character with random background */
	public Character() {
		BackgroundGenerator bg = new BackgroundGenerator();

		this.generalSetUp(bg);
	}

	private void generalSetUp(BackgroundGenerator bg) {
		this.backgroundName = bg.background;

		this.am = new AttributeManager(bg);
		this.im = new InventoryManager();
		this.abm = new AbilityManager();
		this.mm = new MoraleManager();

		this.abm.addAbility(new Ability(bg.ability, null));
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
	}
}
