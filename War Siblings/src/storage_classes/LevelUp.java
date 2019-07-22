/** War Siblings
 * LevelUp class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

/** A level up storage class used for helping in levelling up a character */
public class LevelUp {
	private String name;
	private double value;

	public LevelUp(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	/* Getters */

	public String getName() {
		return this.name;
	}

	public double getValue() {
		return this.value;
	}

}
