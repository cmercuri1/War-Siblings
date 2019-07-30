/** War Siblings
 * BackgroundItem Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import global_managers.GlobalManager;
import items.Item;

/**
 * Special storage item class used in setting up the equipment of a character
 * during generation
 */
public class BackgroundItem {
	protected Item item;
	protected double chanceToGet;

	public BackgroundItem(String itemName, double chance) {
		this.chanceToGet = chance;
		this.item = this.setUpItem(itemName);
	}

	private Item setUpItem(String itemName) {
		Item temp;

		temp = GlobalManager.equipment.getBody().getBodyArmor(itemName);
		if (temp != null) {
			return temp;
		}
		temp = GlobalManager.equipment.getHead().getHeadArmor(itemName);
		if (temp != null) {
			return temp;
		}
		temp = GlobalManager.equipment.getShield().getShield(itemName);
		if (temp != null) {
			return temp;
		}
		temp = GlobalManager.equipment.getWeapon().getWeapon(itemName);
		if (temp != null) {
			return temp;
		}

		return null;
	}

	public Item getItem() {
		return this.item;
	}

	public double getChanceToGet() {
		return this.chanceToGet;
	}

}
