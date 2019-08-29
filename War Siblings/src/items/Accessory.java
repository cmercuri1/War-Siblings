/** War Siblings
 * EquipItem
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import javax.swing.ImageIcon;

import effect_classes.Modifier;
import storage_classes.ArrayList;

public class Accessory extends Item implements Equipable {
	protected ArrayList<Modifier> temp;

	public Accessory(String name, double value, String desc) {
		super(name, value, desc);
		this.temp = new ArrayList<Modifier>();
	}

	protected void setIcon() {
		this.image = new ImageIcon("res/Images/Items/Accessories/" + this.name + ".png");
	}

	@Override
	public ArrayList<Modifier> onEquipSituation() {
		return temp;
	}

}
