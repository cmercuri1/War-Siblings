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
	public enum Location {
		HEAD, BODY, RIGHT, LEFT, BAG
	};

	protected Item item;
	protected double chanceToGet;

	public BackgroundItem(Location loc, String itemName, double chance) {
		this.chanceToGet = chance;
		this.item = this.setUpItem(loc, itemName);
	}

	protected Item setUpItem(Location loc, String itemName) {
		Item temp;

		switch (loc) {
		case BODY:
			temp = GlobalManager.equipment.getBody().getBodyArmor(itemName);
			if (temp != null) {
				return temp;
			}
			return GlobalManager.equipment.DEFAULTBODY;
		case HEAD:
			temp = GlobalManager.equipment.getHead().getHeadArmor(itemName);
			if (temp != null) {
				return temp;
			}
			return GlobalManager.equipment.DEFAULTHEAD;
		case LEFT:
			temp = GlobalManager.equipment.getShield().getShield(itemName);
			if (temp != null) {
				return temp;
			}
			return GlobalManager.equipment.DEFAULTLEFT;
		case RIGHT:
			temp = GlobalManager.equipment.getWeapon().getWeapon(itemName);
			if (temp != null) {
				return temp;
			}
			return GlobalManager.equipment.DEFAULTRIGHT;
		case BAG:
			temp = GlobalManager.equipment.getWeapon().getWeapon(itemName);
			if (temp != null) {
				return temp;
			}
			break;
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
