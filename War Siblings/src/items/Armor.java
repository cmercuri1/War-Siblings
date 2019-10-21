/** War Siblings
 * Armor Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import javax.swing.ImageIcon;

/**
 * A class for storing and assisting in running items that can be used as
 * protective Armor
 */
public class Armor extends ComboItem {
	String type; // Type of armor (cloth, chain, plate)

	/** Constructor */
	public Armor(String name, double value, String desc, double dura, double fatRed, String type) {
		super(name, value, desc, dura, fatRed);
		this.type = type;
	}
	
	@Override
	protected void setIcon() {
		this.image = new ImageIcon("res/images/Items/Armor/" + this.name + ".png");
		if (this.image == null) {
			System.out.println("Error Finding: " + this.name);
		}
		this.invImage = new ImageIcon("res/images/Items/Armor/" + this.name + "-inv.png");
		if (this.invImage == null) {
			System.out.println("Error Finding: " + this.name);
		}
	}

	/* Getters */

	public String getType() {
		return this.type;
	}

	public void display() {
		System.out.println(this.name);
		System.out.println(this.durability.toString());
		System.out.println(this.desc);
		System.out.println("Reduces Max Fatigue by " + this.fatigueRed.getAlteredValue());
		System.out.println("");
	}

}
