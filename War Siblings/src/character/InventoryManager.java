package character;

import java.util.ArrayList;

import common_classes.Observer;
import common_classes.Observeree;
import items.AbilityItem;
import items.Armor;
import items.Headgear;
import items.Weapon;

/**
 * Manager specifically for keeping track of and managing the inventory/equiped
 * items of a character
 */
public class InventoryManager extends Observeree {
	private Armor body;
	private Headgear head;
	private AbilityItem right;
	private AbilityItem left;
	private ArrayList<AbilityItem> bag;

	public InventoryManager(Observer o) {
		this.setUpObservers();
		this.registerObserver(o);
		this.body = null;
		this.head = null;
		this.right = null;
		this.left = null;
		this.bag = new ArrayList<AbilityItem>(2);
	}

	/** Replaces current Body Armor with new one, returns old Body Armor */
	public Armor swapBody(Armor next) {
		Armor temp = this.body;
		this.body = next;
		return temp;
	}

	/** Replaces current Helm with new one, returns old Helm */
	public Headgear swapHead(Headgear next) {
		Headgear temp = this.head;
		this.head = next;
		return temp;
	}

	/**
	 * Replaces current equipped right item (shield, weapon, etc) with new one,
	 * returns old right item
	 */
	public AbilityItem swapRight(AbilityItem next) {
		AbilityItem temp = this.right;
		this.right = next;
		return temp;
	}

	/**
	 * Replaces current equipped left item (shield, weapon, etc) with new one,
	 * returns old left item
	 */
	public AbilityItem swapLeft(AbilityItem next) {
		AbilityItem temp = this.left;
		this.left = next;
		return temp;
	}

	/**
	 * Replaces current equipped item/s in both hands with a single two-handed item,
	 * returns both previously equipped items
	 */
	public ArrayList<AbilityItem> swap2Hander(AbilityItem next) {
		ArrayList<AbilityItem> returnedItems = new ArrayList<AbilityItem>();
		if (this.right != null) {
			returnedItems.add(swapRight(null));
		}
		if (this.left != null) {
			returnedItems.add(swapLeft(null));
		}
		this.right = next;

		return returnedItems;
	}

	/**
	 * Checker function that determines if the character is Dual Gripping a one
	 * handed melee weapon
	 */
	public boolean isDualGripping() {
		if (((this.right instanceof Weapon) && (!((Weapon) this.right).isTwoHanded()) && (this.left == null))
				|| ((this.left instanceof Weapon) && (!((Weapon) this.left).isTwoHanded()) && (this.right == null))) {
			return true;
		}
		return false;
	}

	/** Replaces item in bag, returns replaced item */
	public AbilityItem swapBagItem(AbilityItem next, int index) {
		AbilityItem temp = this.bag.remove(index);
		this.bag.add(index, next);
		return temp;
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
			this.head.display();
		}
		if (this.body != null) {
			this.body.display();
		}
		if (this.right != null) {
			this.right.display();
		}
		if (this.left != null) {
			this.left.display();
		}
		if (!this.bag.isEmpty()) {
			for (AbilityItem a : bag) {
				a.display();
			}
		}
	}
}
