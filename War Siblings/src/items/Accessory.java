/** War Siblings
 * EquipItem
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import javax.swing.ImageIcon;

import effect_classes.Modifier;
import storage_classes.ArrayList;

public class Accessory extends Item implements Equipable {

	public Accessory(String name, double value, String desc) {
		super(name, value, desc);
	}

	protected void setIcon() {
		this.image = new ImageIcon("res/Images/Items/Accessories/" + this.name + ".png");
		this.invImage = new ImageIcon("res/Images/Items/Accessories/" + this.name + ".png");
	}

	@Override
	public ArrayList<Modifier> onEquipSituation() {
		ArrayList<Modifier> temp = new ArrayList<Modifier>();

		return temp;
	}

	@Override
	public ArrayList<Modifier> onBagSituation() {
		ArrayList<Modifier> temp = new ArrayList<Modifier>();

		return temp;
	}

}
