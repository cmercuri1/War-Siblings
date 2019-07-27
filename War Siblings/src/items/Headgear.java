/** War Siblings
 * Headgear Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */

package items;

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
}
