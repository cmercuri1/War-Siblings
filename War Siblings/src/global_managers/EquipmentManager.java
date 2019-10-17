/** War Siblings
 * EquipmentManager Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import abilities.Ability;
import items.Accessory;
import items.Ammunition;
import items.Armor;
import items.ComboItem;
import items.Headgear;
import items.Shield;
import items.Weapon;
import storage_classes.ArrayList;

/**
 * EquipmentManager class that has control over the other managers that make up
 * the equipment side of things
 */
public class EquipmentManager {
	protected BodyArmorManager body;
	protected HeadgearManager head;
	protected ShieldManager shield;
	protected WeaponManager weapon;
	protected AccessoryManager accessory;

	public final Headgear DEFAULTHEAD = new Headgear("EmptyHead", 0, null, 0, 0, null, 0);
	public final Armor DEFAULTBODY = new Armor("EmptyBody", 0, null, 0, 0, null);
	public final Weapon DEFAULTRIGHT = new Weapon("EmptyRight", 0, null, 0, 0, 5, 10, 10, 50, 0, 0, 0, 0, 0, 0, null,
			GlobalManager.abilities.getAbilities("HandToHand"));
	public final Shield DEFAULTLEFT = new Shield("EmptyLeft", 0, null, 0, 0, 0, 0, new ArrayList<Ability>());

	public final Ammunition DEFAULTAMMO = new Ammunition("EmptyAmmo", 0, null, 0);
	public final Accessory DEFAULTACCESSORY = new Accessory("EmptyAccessory", 0, null);
	public final ComboItem DEFAULTBAG = new ComboItem("EmptyBag", 0, null, 0, 0);

	public EquipmentManager() {
		this.body = new BodyArmorManager();
		this.head = new HeadgearManager();
		this.shield = new ShieldManager();
		this.weapon = new WeaponManager();
		this.accessory = new AccessoryManager();
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

	public AccessoryManager getAccessory() {
		return this.accessory;
	}
}
