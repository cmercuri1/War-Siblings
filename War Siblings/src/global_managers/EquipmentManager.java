/** War Siblings
 * EquipmentManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import items.Armor;
import items.EquipItem;
import items.Headgear;
import items.Shield;
import items.Weapon;

/**
 * EquipmentManager class that has control over the other managers that make up
 * the equipment side of things
 */
public class EquipmentManager {
	protected BodyArmorManager body;
	protected HeadgearManager head;
	protected ShieldManager shield;
	protected WeaponManager weapon;

	public final Headgear DEFAULTHEAD = new Headgear("EmptyHead", 0, null, 0, 0, null, 0);
	public final Armor DEFAULTBODY = new Armor("EmptyBody", 0, null, 0, 0, null);
	public final Weapon DEFAULTRIGHT = new Weapon("EmptyRight", 0, null, 0, 0, 5, 10, 0, 50, 0, 0, 0, 0, 0, 0, null, null);
	public final Shield DEFAULTLEFT = new Shield("EmptyLeft", 0, null, 0, 0, 0, 0, null);

	public final EquipItem DEFAULTAMMO = new EquipItem("EmptyAmmo", 0, null, 0, 0);
	public final EquipItem DEFAULTACCESSORY = new EquipItem("EmptyAccessory", 0, null, 0, 0);
	public final EquipItem DEFAULTBAG = new EquipItem("EmptyBag", 0, null, 0, 0);

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
