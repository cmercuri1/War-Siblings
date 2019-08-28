/** War Siblings
 * Headgear Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */

package items;

import javax.swing.ImageIcon;

/**
 * A class for storing and assisting in operating items that function as Helmets
 * and other protective headgear
 */
public class Headgear extends Armor {
	protected double visRed;

	/** Constructor */
	public Headgear(String name, double value, String desc, double dura, double fatRed, String type, double visRed) {
		super(name, value, desc, dura, fatRed, type);
		this.visRed = visRed;
	}

	@Override
	protected void setIcon() {
		this.image = new ImageIcon("res/images/Items/Headgear/" + this.name + ".png");
		if (this.image == null) {
			System.out.println("Error Finding: " + this.name);
		}
	}

	/* Getters */

	public double getVisRed() {
		return this.visRed;
	}

	public void display() {
		System.out.println(this.name);
		System.out.println(this.durability.getAlteredCurrentValue() + "/" + this.durability.getAlteredValue());
		System.out.println(this.desc);
		System.out.println("Reduces Max Fatigue by " + this.fatigueRed.getAlteredValue());
		System.out.print("Reduces vision by " + this.visRed);
		System.out.println("");
	}

	public String toString() {
		String temp = "<html>" + this.name + "<br>" + this.desc + "<br>Worth " + this.value.toString() + " crowns"
				+ "<br>" + this.durability.toString();

		if (this.fatigueRed.getAlteredValue() < 0) {
			temp += "<br>Reduces Maximum Fatigue by " + this.fatigueRed.toString();
		}
		if (this.visRed < 0) {
			temp += ("<br>Reduces vision by " + ((Double) this.visRed).intValue() + " tiles");
		}
		return temp + "</html>";
	}
}
