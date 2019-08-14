/** War Siblings
 * Character class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import javax.swing.ImageIcon;

import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;

/**
 * Class that uses generators to generate a player usable character as well as
 * observes and maintains all the managers that help run the character
 */
public class Character{
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
	public Character(String background) {
		this.generalSetUp(GlobalManager.backgrounds.getBackground(background));
	}

	/** New Character with random background */
	public Character() {
		this.generalSetUp(GlobalManager.backgrounds.getRandomBackground());
	}

	private void generalSetUp(BackgroundGenerator bg) {
		this.backgroundName = bg.getBackground();
		this.bgIcon = new ImageIcon("res/images/Backgrounds/" + this.backgroundName + ".png");

		this.im = new InventoryManager();
		this.am = new AttributeManager();
		this.mm = new MoraleManager();
		this.abm = new AbilityManager();
		this.bm = new BattleManager();

		this.am.setUpAttributes(bg);
		this.abm.setUpAbilities(bg);
		this.im.setUpInventory(bg);
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
