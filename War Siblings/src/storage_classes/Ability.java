/** War Siblings
 * Attribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import javax.swing.ImageIcon;

import effect_classes.Effect;
import storage_classes.ArrayList;

/**
 * Base level Ability class used for helping handle any effects that can change
 * elements of a character
 */
public class Ability {
	protected String name;
	protected String desc;
	protected ArrayList<Effect> effects;

	protected ImageIcon image;

	/** Constructor for when you have all the effects and description */
	public Ability(String name, String desc, ArrayList<Effect> effects) {
		this.name = name;
		this.desc = desc;
		this.effects = effects;
		this.setImage();
	}

	/** Constructor for when you have all the effects */
	public Ability(String name, ArrayList<Effect> effects) {
		this.name = name;
		this.effects = effects;
		this.setImage();
	}

	protected void setImage() {
		try {
			this.image = new ImageIcon(Ability.class.getResource("/images/Abilities/" + this.name + ".png"));
		} catch (NullPointerException n) {
			
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

	public ArrayList<Effect> getEffects() {
		return this.effects;
	}

	public ImageIcon getImage() {
		return this.image;
	}
	
	public String toString() {
		return "<html>" + this.name + ": "+ this.desc;
	}

	public void display() {
		System.out.print("	" + this.name + ": " + this.desc + "\n");
		try {
			this.effects.forEach(e -> e.display());
		} catch (NullPointerException n) {

		}
	}

}
