/** War Siblings
 * Attribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package abilities;

import javax.swing.ImageIcon;

/**
 * Base level Ability class used for helping handle any effects that can change
 * elements of a character
 */
public class Ability {
	protected String name;
	protected String desc;

	protected String displayName;
	
	protected ImageIcon image;

	/** Constructor for when you have all the effects and description */
	public Ability(String name, String desc) {
		this.name = name;
		this.desc = desc;
		this.displayName = name;
		this.setImage();
	}

	/** Constructor for when you have all the effects */
	public Ability(String name) {
		this.name = name;
		this.displayName = name;
		this.setImage();
	}
	
	public Ability(String name, String desc, String displayName) {
		this.name = name;
		this.desc = desc;
		this.displayName = displayName;
		this.setImage();
	}

	protected void setImage() {
		try {
			this.image = new ImageIcon(Ability.class.getResource("/images/Abilities/" + this.name + ".png"));
		} catch (NullPointerException n) {
			System.out.println("Cannot find image for: " + this.name + "->" + this.displayName);
		}
	}

	public void setImage(ImageIcon img) {
		this.image = img;
	}

	public String getName() {
		return this.name;
	}

	public String getDesc() {
		return this.desc;
	}

	public ImageIcon getImage() {
		return this.image;
	}

	public String toString() {
		return "<html>" + this.displayName + ": " + this.desc + "</html>";
	}

	public void display() {
		System.out.print("	" + this.displayName + ": " + this.desc + "\n");
	}

}
