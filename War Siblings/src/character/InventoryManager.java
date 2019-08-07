/** War Siblings
 * InventoryManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import storage_classes.ArrayList;
import storage_classes.BackgroundItem;
import storage_classes.Effect;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;
import items.Armor;
import items.EquipItem;
import items.Headgear;
import items.Shield;
import items.Weapon;
import old_event_classes.EventObject;
import old_event_classes.GenericObservee;
import old_event_classes.Observer;
import old_event_classes.Target;
import old_event_classes.Type;

/**
 * Manager specifically for keeping track of and managing the inventory/equiped
 * items of a character
 */
public class InventoryManager extends GenericObservee implements Observer {
	private Armor body;
	private Headgear head;
	private EquipItem right;
	private EquipItem left;
	private ArrayList<EquipItem> bag;

	private boolean rangedPref = false;

	private enum ARM {
		LEFT, RIGHT
	};

	public InventoryManager(Observer o) {
		this.setUpObservers();
		this.registerObserver(o);
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
					if (i.getItem().getName().contains("Bow") || i.getItem().getName().contains("Crossbow")) {
						// TODO GIVE QUIVER
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
		this.notifyObservers(new EventObject(Target.CHARACTER, Type.RETURN_INVENTORY, temp, null));
	}

	/** Replaces current Body Armor with new one, returns old Body Armor */
	public void swapBody(Armor next) {
		Armor temp = this.body;
		this.body = next;
		this.weighedDown(temp, next);
		this.notifyObservers(new EventObject(Target.CHARACTER, Type.RETURN_INVENTORY, temp, null));
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
		this.notifyObservers(new EventObject(Target.CHARACTER, Type.RETURN_INVENTORY, temp, null));
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
		this.notifyObservers(new EventObject(Target.CHARACTER, Type.RETURN_INVENTORY, temp, null));
	}

	protected void weighedDown(EquipItem old, EquipItem next) {
		Effect fatiguePen;
		Effect initiativePen;
		try {
			fatiguePen = new Effect("Fatigue_Final", old.getFatigueRed().getAlteredValue());
			initiativePen = new Effect("Initiative_Final", old.getFatigueRed().getAlteredValue());
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.REMOVE, fatiguePen, null));
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.REMOVE, initiativePen, null));
		} catch (NullPointerException nu) {

		}
		try {
			fatiguePen = new Effect("Fatigue_Final", next.getFatigueRed().getAlteredValue());
			initiativePen = new Effect("Initiative_Final", next.getFatigueRed().getAlteredValue());
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.ADD, fatiguePen, null));
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.ADD, initiativePen, null));
		} catch (NullPointerException nu) {

		}
	}

	protected void impedeVision(Headgear old, Headgear next) {
		Effect visionPen;
		try {
			visionPen = new Effect("Vision_Final", old.getVisRed());
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.REMOVE, visionPen, null));
		} catch (NullPointerException nu) {

		}
		try {
			visionPen = new Effect("Vision_Final", next.getVisRed());
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.ADD, visionPen, null));
		} catch (NullPointerException nu) {

		}
	}

	protected void removeShieldDefense(Shield old) {
		try {
			Effect meleeDefense = new Effect("MeleeDefense_Final", old.getMeleeDef().getAlteredValue());
			Effect rangedDefense = new Effect("RangedDefense_Final", old.getRangedDef().getAlteredValue());
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.REMOVE, meleeDefense, null));
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.REMOVE, rangedDefense, null));
		} catch (NullPointerException nu) {

		}
	}

	protected void grantShieldDefense(Shield next) {
		try {
			Effect meleeDefense = new Effect("MeleeDefense_Final", next.getMeleeDef().getAlteredValue());
			Effect rangedDefense = new Effect("RangedDefense_Final", next.getRangedDef().getAlteredValue());
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.ADD, meleeDefense, null));
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, Type.ADD, rangedDefense, null));
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
			this.notifyObservers(new EventObject(Target.ABILITY, Type.ADD,
					GlobalManager.traits.getSpecialTrait("Double Grip"), null));
		} else {
			this.notifyObservers(new EventObject(Target.ABILITY, Type.REMOVE,
					GlobalManager.traits.getSpecialTrait("Double Grip"), null));
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
	public void onEventHappening(EventObject event) {
		switch (event.getTarget()) {
		case INVENTORY:
			switch (event.getTask()) {
			case CHANGE_BODY:
				this.swapBody((Armor) event.getInformation());
				break;
			case REMOVE_BODY:
				this.swapBody(GlobalManager.equipment.DEFAULTBODY);
				break;
			case CHANGE_HEAD:
				this.swapHead((Headgear) event.getInformation());
				break;
			case REMOVE_HEAD:
				this.swapHead(GlobalManager.equipment.DEFAULTHEAD);
				break;
			case CHANGE_LEFT:
				this.swapItem(ARM.LEFT, (EquipItem) event.getInformation());
				break;
			case REMOVE_LEFT:
				this.swapItem(ARM.LEFT, GlobalManager.equipment.DEFAULTLEFT);
				break;
			case CHANGE_RIGHT:
				this.swapItem(ARM.RIGHT, (EquipItem) event.getInformation());
				break;
			case REMOVE_RIGHT:
				this.swapItem(ARM.RIGHT, GlobalManager.equipment.DEFAULTRIGHT);
				break;
			case RANGED_PREF:
				this.rangedPref = true;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

}
