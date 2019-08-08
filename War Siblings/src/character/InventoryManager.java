/** War Siblings
 * InventoryManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import storage_classes.ArrayList;
import storage_classes.BackgroundItem;
import storage_classes.Effect;
import event_classes.AbilityEvent;
import event_classes.EffectEvent;
import event_classes.CharacterInventoryEvent;
import event_classes.InventoryEvent;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;
import items.Armor;
import items.EquipItem;
import items.Headgear;
import items.Shield;
import items.Weapon;
import listener_interfaces.AbilityListener;
import listener_interfaces.EffectListener;
import listener_interfaces.CharacterInventoryListener;
import listener_interfaces.InventoryListener;
import notifier_interfaces.AbilityNotifier;
import notifier_interfaces.EffectNotifier;
import notifier_interfaces.InventoryNotifier;
import notifier_interfaces.MultiNotifier;

/**
 * Manager specifically for keeping track of and managing the inventory/equiped
 * items of a character
 */
public class InventoryManager
		implements CharacterInventoryListener, InventoryNotifier, EffectNotifier, AbilityNotifier, MultiNotifier {
	protected Armor body;
	protected Headgear head;
	protected EquipItem right;
	protected EquipItem left;

	protected ArrayList<EquipItem> bag;

	protected ArrayList<AbilityListener> abilityListeners;
	protected ArrayList<EffectListener> effectListeners;
	protected ArrayList<InventoryListener> inventoryListeners;

	protected boolean rangedPref = false;

	protected enum ARM {
		LEFT, RIGHT
	};

	public InventoryManager() {
		this.defaultInventory();
		this.setUpListeners();
	}

	protected void defaultInventory() {
		this.body = GlobalManager.equipment.DEFAULTBODY;
		this.head = GlobalManager.equipment.DEFAULTHEAD;
		this.right = GlobalManager.equipment.DEFAULTRIGHT;
		this.left = GlobalManager.equipment.DEFAULTLEFT;

		this.bag = new ArrayList<EquipItem>();
		this.bag.add(GlobalManager.equipment.DEFAULTBAG);
		this.bag.add(GlobalManager.equipment.DEFAULTBAG);
	}

	public void setUpInventory(BackgroundGenerator bg) {
		int roll = GlobalManager.d100Roll();
		for (BackgroundItem i : bg.getHeadOptions()) {
			if (roll <= i.getChanceToGet()) {
				if (i.getItem() != null)
					this.swapHead((Headgear) i.getItem());
				break;
			} else {
				roll -= i.getChanceToGet();
			}
		}

		roll = GlobalManager.d100Roll();
		for (BackgroundItem i : bg.getBodyOptions()) {
			if (roll <= i.getChanceToGet()) {
				if (i.getItem() != null)
					this.swapBody((Armor) i.getItem());
				break;
			} else {
				roll -= i.getChanceToGet();
			}
		}

		// Uses System for determining whether starting equipment is a ranged weapon on
		// Deserter/Sellsword Backgrounds
		roll = GlobalManager.d100Roll();
		if (rangedPref)
			roll += 100;
		for (BackgroundItem i : bg.getRightOptions()) {
			if (roll <= i.getChanceToGet()) {
				if (i.getItem() != null) {
					this.swapItem(ARM.RIGHT, (EquipItem) i.getItem());
					if (i.getItem().getName().contains("Bow")) {
						// TODO GIVE QUIVER
					} else if (i.getItem().getName().contains("Crossbow")) {
						// TODO GIVE CROSSBOW QUIVER
					}
				}
				break;
			} else {
				roll -= i.getChanceToGet();
			}
		}

		roll = GlobalManager.d100Roll();
		if (!((this.right instanceof Weapon) && (((Weapon) this.right).isTwoHanded()))) {
			for (BackgroundItem i : bg.getLeftOptions()) {
				if (roll <= i.getChanceToGet()) {
					if (i.getItem() != null)
						this.swapItem(ARM.LEFT, (EquipItem) i.getItem());
					break;
				} else {
					roll -= i.getChanceToGet();
				}
			}
		}

		roll = GlobalManager.d100Roll();
		for (BackgroundItem i : bg.getBackPackOptions()) {
			if (roll <= i.getChanceToGet()) {
				if (i.getItem() != null)
					this.swapBagItem((EquipItem) i.getItem(), 0);
				break;
			} else {
				roll -= i.getChanceToGet();
			}
		}
	}

	/** Replaces current Helm with new one, returns old Helm */
	public void swapHead(Headgear next) {
		Headgear temp = this.head;
		this.head = next;
		this.weighedDown(temp, next);
		this.impedeVision(temp, next);
		this.notifyInventoryListeners(new InventoryEvent(InventoryEvent.Task.RETURN_INVENTORY, temp, this));
	}

	/** Replaces current Body Armor with new one, returns old Body Armor */
	public void swapBody(Armor next) {
		Armor temp = this.body;
		this.body = next;
		this.weighedDown(temp, next);
		this.notifyInventoryListeners(new InventoryEvent(InventoryEvent.Task.RETURN_INVENTORY, temp, this));
	}

	public void swapItem(ARM target, EquipItem next) {
		if ((next instanceof Weapon) && ((Weapon) next).isTwoHanded()) {
			this.swap2Hander(next);
			return;
		} else {
			this.swap1Hander(target, next);
			return;
		}
	}

	/**
	 * Replaces current equipped right item (shield, weapon, etc) with new one,
	 * returns old right item
	 */
	public void swap1Hander(ARM target, EquipItem next) {
		EquipItem temp = GlobalManager.equipment.DEFAULTRIGHT;
		switch (target) {
		case LEFT:
			temp = this.left;
			this.left = next;
			break;
		case RIGHT:
			temp = this.right;
			this.right = next;
			break;
		}
		this.weighedDown(temp, next);
		if (temp instanceof Shield) {
			this.removeShieldDefense((Shield) temp);
		}

		if (next instanceof Shield) {
			this.grantShieldDefense((Shield) next);
		}

		this.isDualGripping();
		this.notifyInventoryListeners(new InventoryEvent(InventoryEvent.Task.RETURN_INVENTORY, temp, this));
	}

	/**
	 * Replaces current equipped item/s in both hands with a single two-handed item,
	 * returns both previously equipped items
	 */
	public void swap2Hander(EquipItem next) {
		if (this.left != null) {
			this.swap1Hander(ARM.LEFT, GlobalManager.equipment.DEFAULTLEFT);
		}
		this.swap1Hander(ARM.RIGHT, next);
	}

	/** Replaces item in bag, returns replaced item */
	public void swapBagItem(EquipItem next, int index) {
		EquipItem temp = this.bag.remove(index);
		this.bag.add(index, next);

		this.weighedDown(temp, next);
		this.notifyInventoryListeners(new InventoryEvent(InventoryEvent.Task.RETURN_INVENTORY, temp, this));
	}

	protected void weighedDown(EquipItem old, EquipItem next) {
		Effect fatiguePen;
		Effect initiativePen;
		try {
			fatiguePen = new Effect("Fatigue_Final", old.getFatigueRed().getAlteredValue());
			initiativePen = new Effect("Initiative_Final", old.getFatigueRed().getAlteredValue());
			this.notifyEffectListeners(new EffectEvent(EffectEvent.Task.REMOVE, fatiguePen, this));
			this.notifyEffectListeners(new EffectEvent(EffectEvent.Task.REMOVE, initiativePen, this));
		} catch (NullPointerException nu) {

		}
		try {
			fatiguePen = new Effect("Fatigue_Final", next.getFatigueRed().getAlteredValue());
			initiativePen = new Effect("Initiative_Final", next.getFatigueRed().getAlteredValue());
			this.notifyEffectListeners(new EffectEvent(EffectEvent.Task.ADD, fatiguePen, this));
			this.notifyEffectListeners(new EffectEvent(EffectEvent.Task.ADD, initiativePen, this));
		} catch (NullPointerException nu) {

		}
	}

	protected void impedeVision(Headgear old, Headgear next) {
		Effect visionPen;
		try {
			visionPen = new Effect("Vision_Final", old.getVisRed());
			this.notifyEffectListeners(new EffectEvent(EffectEvent.Task.REMOVE, visionPen, this));
		} catch (NullPointerException nu) {

		}
		try {
			visionPen = new Effect("Vision_Final", next.getVisRed());
			this.notifyEffectListeners(new EffectEvent(EffectEvent.Task.REMOVE, visionPen, this));
		} catch (NullPointerException nu) {

		}
	}

	protected void removeShieldDefense(Shield old) {
		try {
			Effect meleeDefense = new Effect("MeleeDefense_Final", old.getMeleeDef().getAlteredValue());
			Effect rangedDefense = new Effect("RangedDefense_Final", old.getRangedDef().getAlteredValue());

			this.notifyEffectListeners(new EffectEvent(EffectEvent.Task.REMOVE, meleeDefense, this));
			this.notifyEffectListeners(new EffectEvent(EffectEvent.Task.REMOVE, rangedDefense, this));
		} catch (NullPointerException nu) {

		}
	}

	protected void grantShieldDefense(Shield next) {
		try {
			Effect meleeDefense = new Effect("MeleeDefense_Final", next.getMeleeDef().getAlteredValue());
			Effect rangedDefense = new Effect("RangedDefense_Final", next.getRangedDef().getAlteredValue());

			this.notifyEffectListeners(new EffectEvent(EffectEvent.Task.ADD, meleeDefense, this));
			this.notifyEffectListeners(new EffectEvent(EffectEvent.Task.ADD, rangedDefense, this));
		} catch (NullPointerException nu) {

		}
	}

	/**
	 * Method that determines if the character is Dual Gripping a one handed melee
	 * weapon and informs the
	 */
	public void isDualGripping() {
		if (((this.right instanceof Weapon) && !(((Weapon) this.right).isTwoHanded())
				&& (this.left == GlobalManager.equipment.DEFAULTLEFT))
				|| ((this.left instanceof Weapon) && !(((Weapon) this.left).isTwoHanded())
						&& (this.right == GlobalManager.equipment.DEFAULTRIGHT))) {
			this.notifyAbilityListeners(
					new AbilityEvent(AbilityEvent.Task.ADD, GlobalManager.traits.getSpecialTrait("Double Grip"), this));
		} else {
			this.notifyAbilityListeners(new AbilityEvent(AbilityEvent.Task.REMOVE,
					GlobalManager.traits.getSpecialTrait("Double Grip"), this));
		}
	}

	public Armor getBody() {
		return this.body;
	}

	public Headgear getHead() {
		return this.head;
	}

	public EquipItem getRight() {
		return this.right;
	}

	public EquipItem getLeft() {
		return this.left;
	}

	public ArrayList<EquipItem> getBag() {
		return this.bag;
	}

	public void display() {
		System.out.println("Inventory:");

		System.out.println("Head:");
		this.head.display();

		System.out.println("Body:");
		this.body.display();

		System.out.println("Right:");
		this.right.display();

		System.out.println("Left:");
		this.left.display();

		System.out.println("Bag:");
		for (EquipItem a : this.bag) {
			if (!a.equals(GlobalManager.equipment.DEFAULTRIGHT))
				a.display();

		}
	}

	@Override
	public void addAbilityListener(AbilityListener a) {
		this.abilityListeners.add(a);
	}

	@Override
	public void removeAbilityListener(AbilityListener a) {
		this.abilityListeners.remove(a);
	}

	@Override
	public void notifyAbilityListeners(AbilityEvent a) {
		this.abilityListeners.forEach(l -> l.onAbilityEvent(a));
	}

	@Override
	public void notifyAbilityListener(AbilityListener a, AbilityEvent e) {
		this.abilityListeners.get(a).onAbilityEvent(e);
	}

	@Override
	public void addEffectListener(EffectListener a) {
		this.effectListeners.add(a);
	}

	@Override
	public void removeEffectListener(EffectListener a) {
		this.effectListeners.remove(a);
	}

	@Override
	public void notifyEffectListeners(EffectEvent a) {
		this.effectListeners.forEach(l -> l.onEffectEvent(a));
	}

	@Override
	public void notifyEffectListener(EffectListener a, EffectEvent e) {
		this.effectListeners.get(a).onEffectEvent(e);
	}

	@Override
	public void addInventoryListener(InventoryListener i) {
		this.inventoryListeners.add(i);
	}

	@Override
	public void removeInventoryListener(InventoryListener i) {
		this.inventoryListeners.remove(i);
	}

	@Override
	public void notifyInventoryListeners(InventoryEvent i) {
		this.inventoryListeners.forEach(l -> l.onInventoryEvent(i));
	}

	@Override
	public void notifyInventoryListener(InventoryListener i, InventoryEvent e) {
		this.inventoryListeners.get(i).onInventoryEvent(e);
	}

	@Override
	public void onCharacterInventoryEvent(CharacterInventoryEvent c) {
		switch (c.getTask()) {
		case CHANGE_BODY:
			break;
		case CHANGE_HEAD:
			break;
		case CHANGE_LEFT:
			break;
		case CHANGE_RIGHT:
			break;
		case REMOVE_BODY:
			break;
		case REMOVE_HEAD:
			break;
		case REMOVE_LEFT:
			break;
		case REMOVE_RIGHT:
			break;
		}
	}

	@Override
	public void setUpListeners() {
		this.abilityListeners = new ArrayList<AbilityListener>();
		this.effectListeners = new ArrayList<EffectListener>();
		this.inventoryListeners = new ArrayList<InventoryListener>();
	}

}
