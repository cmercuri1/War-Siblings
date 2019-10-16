/** War Siblings
 * ActivatedAbility
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package abilities;

import javax.swing.ImageIcon;

import attributes.Attribute;

public class ActivatedAbility extends Ability {

	protected Attribute fatigueCost;
	protected Attribute apCost;

	/**
	 * @param name
	 * @param desc
	 */
	public ActivatedAbility(String name, String desc, String dName, String image, double fatCost, double apCost) {
		super(image, desc, dName);
		this.name = name;
		this.fatigueCost = new Attribute(fatCost);
		this.apCost = new Attribute(apCost);
	}

	/**
	 * @param name
	 */
	public ActivatedAbility(String name, String dName, String image, double fatCost, double apCost) {
		super(image);
		this.name = name;
		this.displayName = dName;
		this.fatigueCost = new Attribute(fatCost);
		this.apCost = new Attribute(apCost);
	}

	protected void setImage() {
		try { // THIS NEEDS TO BE CHANGED TODO
			this.image = new ImageIcon(Ability.class.getResource("/images/Abilities/Weapons/" + this.name + ".png"));
		} catch (NullPointerException n) {
			System.out.println("Cannot find image for: " + this.name);
		}
	}

	public Attribute getFatigueCost() {
		return fatigueCost;
	}

	public Attribute getApCost() {
		return apCost;
	}

	public String toString() {
		return "<html>" + this.displayName + ": " + this.desc + "<br>Builds up " + this.fatigueCost + "fatigue.<br>Costs "
				+ this.apCost + " Action Points to use" + "</html>";
	}
}
