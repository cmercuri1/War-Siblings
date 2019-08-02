/** War Siblings
 * EquipmentManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import items.EquipItem;

/**
 * EquipmentManager class that has control over the other managers that make up
 * the equipment side of things
 */
public class EquipmentManager {
	protected BodyArmorManager body;
	protected HeadgearManager head;
	protected ShieldManager shield;
	protected WeaponManager weapon;

	public final EquipItem DEFAULT = new EquipItem("Unarmed", 0, null, 0, 0);

	public EquipmentManager() {
		this.body = new BodyArmorManager();
		this.head = new HeadgearManager();
		this.shield = new ShieldManager();
		this.weapon = new WeaponManager();
	}

	public BodyArmorManager getBody() {
		return this.body;
	}

	public HeadgearManager getHead() {
		return this.head;
	}

	public ShieldManager getShield() {
		return this.shield;
	}

	public WeaponManager getWeapon() {
		return this.weapon;
	}
}
