package character;

import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;

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
		this.generalSetUp(GlobalManager.characters.getBackground(background));
	}

	/** New Character with random background */
	public Character() {
		this.generalSetUp(GlobalManager.characters.getRandomBackground());
	}

	private void generalSetUp(BackgroundGenerator bg) {
		this.backgroundName = bg.getBackground();

		this.am = new AttributeManager(bg);
		this.im = new InventoryManager();
		this.abm = new AbilityManager(bg);
		this.mm = new MoraleManager();
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
		System.out.println();
	}
}
