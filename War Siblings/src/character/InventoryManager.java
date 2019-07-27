/** War Siblings
 * InventoryManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import storage_classes.ArrayList;
import storage_classes.BackgroundItem;
import storage_classes.Effect;
import event_classes.EventObject;
import event_classes.EventType;
import event_classes.GenericObservee;
import event_classes.Observer;
import event_classes.Target;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;
import items.AbilityItem;
import items.Armor;
import items.EquipItem;
import items.Headgear;
import items.Weapon;

/**
 * Manager specifically for keeping track of and managing the inventory/equiped
 * items of a character
 */
public class InventoryManager extends GenericObservee implements Observer {
	private Armor body;
	private Headgear head;
	private AbilityItem right;
	private AbilityItem left;
	private ArrayList<AbilityItem> bag;
	
	private enum arm {LEFT,RIGHT};

	public InventoryManager(Observer o) {
		this.setUpObservers();
		this.registerObserver(o);
		this.body = null;
		this.head = null;
		this.right = null;
		this.left = null;
		this.bag = new ArrayList<AbilityItem>(2);
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

		roll = GlobalManager.d100Roll();
		for (BackgroundItem i : bg.getRightOptions()) {
			if (roll <= i.getChanceToGet()) {
				if (i.getItem() != null) {
					this.swapItem(arm.RIGHT, (AbilityItem) i.getItem());
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
						this.swapItem(arm.LEFT, (AbilityItem) i.getItem());
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
					System.out.println("adding " + i.getItem().getName() + " to bag");
					//this.swapBagItem((AbilityItem) i.getItem(), 0);
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
		this.notifyObservers(new EventObject(Target.CHARACTER, EventType.RETURN_INVENTORY, temp, null));
	}

	/** Replaces current Body Armor with new one, returns old Body Armor */
	public void swapBody(Armor next) {
		Armor temp = this.body;
		this.body = next;
		this.weighedDown(temp, next);
		this.notifyObservers(new EventObject(Target.CHARACTER, EventType.RETURN_INVENTORY, temp, null));
	}

	public void swapItem(arm arm, AbilityItem next) {
		if ((next instanceof Weapon) && ((Weapon) next).isTwoHanded()) {
			this.swap2Hander(next);
			return;
		} else {
			this.swap1Hander(arm, next);
			return;
		}
	}

	/**
	 * Replaces current equipped right item (shield, weapon, etc) with new one,
	 * returns old right item
	 */
	public void swap1Hander(arm arm, AbilityItem next) {
		AbilityItem temp = null;
		switch (arm) {
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
		this.isDualGripping();
		this.notifyObservers(new EventObject(Target.CHARACTER, EventType.RETURN_INVENTORY, temp, null));
	}

	/**
	 * Replaces current equipped item/s in both hands with a single two-handed item,
	 * returns both previously equipped items
	 */
	public void swap2Hander(AbilityItem next) {
		if (this.left != null) {
			this.swap1Hander(arm.LEFT, null);
		}
		this.swap1Hander(arm.RIGHT, next);
	}

	/** Replaces item in bag, returns replaced item */
	public void swapBagItem(AbilityItem next, int index) {
		AbilityItem temp = this.bag.remove(index);
		this.bag.add(index, next);
		this.weighedDown(temp, next);
		this.notifyObservers(new EventObject(Target.CHARACTER, EventType.RETURN_INVENTORY, temp, null));
	}

	protected void weighedDown(EquipItem old, EquipItem next) {
		Effect fatiguePen;
		Effect initiativePen;
		try {
			fatiguePen = new Effect("Fatigue_Final", old.getFatigueRed().getAlteredValue());
			initiativePen = new Effect("Initiative_Final", old.getFatigueRed().getAlteredValue());
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, EventType.REMOVE, fatiguePen, null));
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, EventType.REMOVE, initiativePen, null));
		} catch (NullPointerException nu) {

		}
		try {
			fatiguePen = new Effect("Fatigue_Final", next.getFatigueRed().getAlteredValue());
			initiativePen = new Effect("Initiative_Final", next.getFatigueRed().getAlteredValue());
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, EventType.ADD, fatiguePen, null));
			this.notifyObservers(new EventObject(Target.ATTRIBUTE, EventType.ADD, initiativePen, null));
		} catch (NullPointerException nu) {

		}
	}

	/**
	 * Method that determines if the character is Dual Gripping a one handed melee
	 * weapon and informs the
	 */
	public void isDualGripping() {
		if (((this.right instanceof Weapon) && (!((Weapon) this.right).isTwoHanded()) && (this.left == null))
				|| ((this.left instanceof Weapon) && (!((Weapon) this.left).isTwoHanded()) && (this.right == null))) {
			this.notifyObservers(new EventObject(Target.ABILITY, EventType.ADD,
					GlobalManager.traits.getSpecialTrait("Dual Grip"), null));
		} else {
			this.notifyObservers(new EventObject(Target.ABILITY, EventType.REMOVE,
					GlobalManager.traits.getSpecialTrait("Dual Grip"), null));
		}
	}

	public Armor getBody() {
		return this.body;
	}

	public Headgear getHead() {
		return this.head;
	}

	public AbilityItem getRight() {
		return this.right;
	}

	public AbilityItem getLeft() {
		return this.left;
	}

	public ArrayList<AbilityItem> getBag() {
		return this.bag;
	}

	public void display() {
		System.out.println("Inventory:");

		if (this.head != null) {
			System.out.println("Head:");
			this.head.display();
		}
		if (this.body != null) {
			System.out.println("Body:");
			this.body.display();
		}
		if (this.right != null) {
			System.out.println("Right:");
			this.right.display();
		}
		if (this.left != null) {
			System.out.println("Left:");
			this.left.display();
		}
		if (!this.bag.isEmpty()) {
			System.out.println("Bag:");
			for (AbilityItem a : bag) {
				a.display();
			}
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
			case CHANGE_HEAD:
				this.swapHead((Headgear) event.getInformation());
				break;
			case CHANGE_LEFT:
				this.swapItem(arm.LEFT, (AbilityItem) event.getInformation());
				break;
			case CHANGE_RIGHT:
				this.swapItem(arm.RIGHT, (AbilityItem) event.getInformation());
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
