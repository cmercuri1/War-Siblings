/** War Siblings
 * InventoryManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import storage_classes.ArrayList;
import storage_classes.BackgroundItem;
import event_classes.TraitEvent;
import event_classes.ModifierEvent;
import abilities.Ability;
import effect_classes.Modifier;
import event_classes.AbilityEvent;
import event_classes.CharacterInventoryEvent;
import event_classes.InventoryEvent;
import event_classes.InventorySituationEvent;
import event_classes.SkillPreferenceEvent;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;
import items.Abilities;
import items.AbilityItem;
import items.Accessory;
import items.Ammunition;
import items.Armor;
import items.ComboItem;
import items.Equipable;
import items.Headgear;
import items.Item;
import items.Weapon;
import listener_interfaces.TraitListener;
import listener_interfaces.ModifierListener;
import listener_interfaces.AbilityListener;
import listener_interfaces.CharacterInventoryListener;
import listener_interfaces.InventoryListener;
import listener_interfaces.InventorySituationListener;
import listener_interfaces.SkillPreferenceListener;
import notifier_interfaces.TraitNotifier;
import notifier_interfaces.ModifierNotifier;
import notifier_interfaces.AbilityNotifier;
import notifier_interfaces.InventoryNotifier;
import notifier_interfaces.InventorySituationNotifier;
import notifier_interfaces.MultiNotifier;

/**
 * Manager specifically for keeping track of and managing the inventory/equiped
 * items of a character
 */
public class InventoryManager implements CharacterInventoryListener, SkillPreferenceListener, MultiNotifier,
		InventoryNotifier, ModifierNotifier, AbilityNotifier, TraitNotifier, InventorySituationNotifier {
	protected Armor body;
	protected Headgear head;
	protected AbilityItem right;
	protected AbilityItem left;

	protected Accessory accessory;
	protected Ammunition ammunition;

	protected ArrayList<ComboItem> bag;

	protected ArrayList<TraitListener> traitListeners;
	protected ArrayList<ModifierListener> effectListeners;
	protected ArrayList<InventoryListener> inventoryListeners;
	protected ArrayList<InventorySituationListener> inventorySituationListeners;
	protected ArrayList<AbilityListener> abilityListeners;

	protected double rangedPref;

	protected enum Target {
		LEFT, RIGHT, HEAD, BODY, ACCESSORY, AMMO
	};

	public InventoryManager() {
		this.defaultInventory();
		this.setUpListeners();
	}

	@Override
	public void setUpListeners() {
		this.traitListeners = new ArrayList<TraitListener>();
		this.effectListeners = new ArrayList<ModifierListener>();
		this.inventoryListeners = new ArrayList<InventoryListener>();
		this.inventorySituationListeners = new ArrayList<InventorySituationListener>();
		this.abilityListeners = new ArrayList<AbilityListener>();
	}

	protected void defaultInventory() {
		this.body = GlobalManager.equipment.DEFAULTBODY;
		this.head = GlobalManager.equipment.DEFAULTHEAD;
		this.right = GlobalManager.equipment.DEFAULTRIGHT;
		this.left = GlobalManager.equipment.DEFAULTLEFT;

		this.accessory = GlobalManager.equipment.DEFAULTACCESSORY;
		this.ammunition = GlobalManager.equipment.DEFAULTAMMO;

		this.bag = new ArrayList<ComboItem>();
		this.bag.add(GlobalManager.equipment.DEFAULTBAG);
		this.bag.add(GlobalManager.equipment.DEFAULTBAG);
	}

	public void setUpInventory(BackgroundGenerator bg) {
		int roll = GlobalManager.d100Roll();
		for (BackgroundItem i : bg.getHeadOptions()) {
			if (roll <= i.getChanceToGet()) {
				if (i.getItem() != null)
					this.swapItem(Target.HEAD, (ComboItem) i.getItem());
				break;
			} else {
				roll -= i.getChanceToGet();
			}
		}

		roll = GlobalManager.d100Roll();
		for (BackgroundItem i : bg.getBodyOptions()) {
			if (roll <= i.getChanceToGet()) {
				if (i.getItem() != null)
					this.swapItem(Target.BODY, (ComboItem) i.getItem());
				break;
			} else {
				roll -= i.getChanceToGet();
			}
		}

		// Uses System for determining whether starting equipment is a ranged weapon on
		// Deserter/Sellsword Backgrounds
		roll = GlobalManager.d100Roll();
		if ((rangedPref > 0) && (bg.getBackground().equals("Deserter") || bg.getBackground().equals("Sellsword")))
			roll += 100;
		for (BackgroundItem i : bg.getRightOptions()) {
			if (roll <= i.getChanceToGet()) {
				if (i.getItem() != null) {
					this.swapItem(Target.RIGHT, (ComboItem) i.getItem());
					if (i.getItem().getName().contains("Bow")) {
						this.swapItem(Target.AMMO, new Ammunition("Quiver of Arrows", 50, "", 10));
					} else if (i.getItem().getName().contains("Crossbow")) {
						this.swapItem(Target.AMMO, new Ammunition("Quiver of Bolts", 50, "", 10));
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
						this.swapItem(Target.LEFT, (ComboItem) i.getItem());
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
					this.swapBagItem((ComboItem) i.getItem(), 0);
				break;
			} else {
				roll -= i.getChanceToGet();
			}
		}
	}

	public void swapItem(Target target, Equipable next) {
		Equipable temp = null;
		switch (target) {
		case BODY:
			temp = this.body;
			this.body = (Armor) next;
			break;
		case HEAD:
			temp = this.head;
			this.head = (Headgear) next;
			break;
		case LEFT:
			if (next instanceof Weapon && ((Weapon) next).isTwoHanded())
				temp = this.swap2Hander(target, (AbilityItem) next);
			else
				temp = this.swap1Hander(target, (AbilityItem) next);
			break;
		case RIGHT:
			if (next instanceof Weapon && ((Weapon) next).isTwoHanded())
				temp = this.swap2Hander(target, (AbilityItem) next);
			else
				temp = this.swap1Hander(target, (AbilityItem) next);
			break;
		case ACCESSORY:
			temp = this.accessory;
			this.accessory = (Accessory) next;
			break;
		case AMMO:
			temp = this.ammunition;
			this.ammunition = (Ammunition) next;
			break;
		}
		this.removeModifiers(temp.onEquipSituation());
		this.addModifiers(next.onEquipSituation());

		if (temp instanceof Abilities) {
			for (Ability a : ((Abilities) temp).getAbilityList()) {
				this.notifyAbilityListeners(new AbilityEvent(AbilityEvent.Task.REMOVE, a, this));
			}
		}

		if (next instanceof Abilities) {
			for (Ability a : ((Abilities) next).getAbilityList()) {
				this.notifyAbilityListeners(new AbilityEvent(AbilityEvent.Task.ADD, a, this));
			}
		}

		this.notifyInventoryListeners(new InventoryEvent(InventoryEvent.Task.RETURN_INVENTORY, (Item) temp, this));
	}

	/**
	 * Replaces current equipped right item (shield, weapon, etc) with new one,
	 * returns old right item
	 * 
	 * @return
	 */
	public AbilityItem swap1Hander(Target target, AbilityItem next) {
		AbilityItem temp = null;
		switch (target) {
		case LEFT:
			temp = this.left;
			this.left = next;
			break;
		case RIGHT:
			temp = this.right;
			this.right = next;

			if (next == GlobalManager.equipment.DEFAULTRIGHT) {
				this.notifyInventorySituationListeners(
						new InventorySituationEvent(InventorySituationEvent.Task.UNARMED, null, this));
			} else if (((Weapon) next).getRange().getAlteredValue() <= 2.0) {
				this.notifyInventorySituationListeners(
						new InventorySituationEvent(InventorySituationEvent.Task.MELEE, null, this));
			} else {
				this.notifyInventorySituationListeners(
						new InventorySituationEvent(InventorySituationEvent.Task.RANGED, null, this));
			}

			break;
		default:
			break;
		}

		this.isDualGripping();
		return temp;
	}

	/**
	 * Replaces current equipped item/s in both hands with a single two-handed item,
	 * returns both previously equipped items
	 * 
	 * @return
	 */
	public AbilityItem swap2Hander(Target target, AbilityItem next) {
		AbilityItem temp = null;
		AbilityItem temp2 = null;

		switch (target) {
		case LEFT:
			if (this.right != GlobalManager.equipment.DEFAULTRIGHT) {
				temp2 = this.swap1Hander(Target.RIGHT, GlobalManager.equipment.DEFAULTRIGHT);
			}
			temp = this.swap1Hander(Target.LEFT, next);
			break;
		case RIGHT:
			if (this.left != GlobalManager.equipment.DEFAULTLEFT) {
				temp2 = this.swap1Hander(Target.LEFT, GlobalManager.equipment.DEFAULTLEFT);
			}
			temp = this.swap1Hander(Target.RIGHT, next);
			break;
		default:
			break;
		}

		if (temp2 != null) {
			this.notifyInventoryListeners(new InventoryEvent(InventoryEvent.Task.RETURN_INVENTORY, temp2, this));
		}

		return temp;
	}

	/** Replaces item in bag, returns replaced item */
	public void swapBagItem(ComboItem next, int index) {
		ComboItem temp = this.bag.remove(index);
		this.bag.add(index, next);

		this.removeModifiers(temp.onBagSituation());
		this.addModifiers(next.onBagSituation());
		this.notifyInventoryListeners(new InventoryEvent(InventoryEvent.Task.RETURN_INVENTORY, temp, this));
	}

	/**
	 * @param onEquipSituation
	 */
	protected void addModifiers(ArrayList<Modifier> onEquipSituation) {
		for (Modifier m : onEquipSituation) {
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.ADD, m, this));
		}
	}

	/**
	 * @param onEquipSituation
	 */
	protected void removeModifiers(ArrayList<Modifier> onDeequipSituation) {
		for (Modifier m : onDeequipSituation) {
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.REMOVE, m, this));
		}
	}

	/**
	 * Method that determines if the character is Dual Gripping a one handed melee
	 * weapon and informs the
	 */
	public void isDualGripping() {
		if (((this.right instanceof Weapon) && (this.right != GlobalManager.equipment.DEFAULTRIGHT)
				&& !(((Weapon) this.right).isTwoHanded()) && (this.left == GlobalManager.equipment.DEFAULTLEFT))
				|| ((this.left instanceof Weapon) && (this.left != GlobalManager.equipment.DEFAULTLEFT)
						&& !(((Weapon) this.left).isTwoHanded())
						&& (this.right == GlobalManager.equipment.DEFAULTRIGHT))) {
			this.notifyTraitListeners(
					new TraitEvent(TraitEvent.Task.ADD, GlobalManager.traits.getSpecialTrait("Double Grip"), this));
		} else {
			this.notifyTraitListeners(
					new TraitEvent(TraitEvent.Task.REMOVE, GlobalManager.traits.getSpecialTrait("Double Grip"), this));
		}
	}

	public Armor getBody() {
		return this.body;
	}

	public Headgear getHead() {
		return this.head;
	}

	public ComboItem getRight() {
		return this.right;
	}

	public ComboItem getLeft() {
		return this.left;
	}

	public ArrayList<ComboItem> getBag() {
		return this.bag;
	}

	public Accessory getAccessory() {
		return this.accessory;
	}

	public Ammunition getAmmunition() {
		return this.ammunition;
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
		for (ComboItem a : this.bag) {
			if (!a.equals(GlobalManager.equipment.DEFAULTRIGHT))
				a.display();

		}
	}

	@Override
	public void onCharacterInventoryEvent(CharacterInventoryEvent c) {
		switch (c.getTask()) {
		case CHANGE_BODY:
			this.swapItem(Target.BODY, (Armor) c.getInformation());
			break;
		case CHANGE_HEAD:
			this.swapItem(Target.HEAD, (Headgear) c.getInformation());
			break;
		case CHANGE_LEFT:
			this.swapItem(Target.LEFT, c.getInformation());
			break;
		case CHANGE_RIGHT:
			this.swapItem(Target.RIGHT, c.getInformation());
			break;
		case REMOVE_BODY:
			this.swapItem(Target.BODY, GlobalManager.equipment.DEFAULTBODY);
			break;
		case REMOVE_HEAD:
			this.swapItem(Target.HEAD, GlobalManager.equipment.DEFAULTHEAD);
			break;
		case REMOVE_LEFT:
			this.swapItem(Target.LEFT, GlobalManager.equipment.DEFAULTLEFT);
			break;
		case REMOVE_RIGHT:
			this.swapItem(Target.RIGHT, GlobalManager.equipment.DEFAULTRIGHT);
			break;
		case REMOVE_ALL:
			this.swapItem(Target.BODY, GlobalManager.equipment.DEFAULTBODY);
			this.swapItem(Target.HEAD, GlobalManager.equipment.DEFAULTHEAD);
			this.swapItem(Target.LEFT, GlobalManager.equipment.DEFAULTLEFT);
			this.swapItem(Target.RIGHT, GlobalManager.equipment.DEFAULTRIGHT);
			break;
		}
	}

	@Override
	public void onSkillPreferenceEvent(SkillPreferenceEvent s) {
		switch (s.getTask()) {
		case MELEE_PREF:
			this.rangedPref -= s.getInformation();
			break;
		case RANGED_PREF:
			this.rangedPref += s.getInformation();
			break;
		}
	}

	@Override
	public void addTraitListener(TraitListener a) {
		this.traitListeners.add(a);
	}

	@Override
	public void removeTraitListener(TraitListener a) {
		this.traitListeners.remove(a);
	}

	@Override
	public void notifyTraitListeners(TraitEvent a) {
		this.traitListeners.forEach(l -> l.onTraitEvent(a));
	}

	@Override
	public void notifyTraitListener(TraitListener a, TraitEvent e) {
		this.traitListeners.get(a).onTraitEvent(e);
	}

	@Override
	public void addModifierListener(ModifierListener a) {
		this.effectListeners.add(a);
	}

	@Override
	public void removeModifierListener(ModifierListener a) {
		this.effectListeners.remove(a);
	}

	@Override
	public void notifyModifierListeners(ModifierEvent a) {
		this.effectListeners.forEach(l -> l.onModifierEvent(a));
	}

	@Override
	public void notifyModifierListener(ModifierListener a, ModifierEvent e) {
		this.effectListeners.get(a).onModifierEvent(e);
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
	public void addInventorySituationListener(InventorySituationListener i) {
		this.inventorySituationListeners.add(i);
	}

	@Override
	public void removeInventorySituationListener(InventorySituationListener i) {
		this.inventorySituationListeners.remove(i);
	}

	@Override
	public void notifyInventorySituationListeners(InventorySituationEvent i) {
		this.inventorySituationListeners.forEach(l -> l.onInventorySituationEvent(i));
	}

	@Override
	public void notifyInventorySituationListener(InventorySituationListener i, InventorySituationEvent e) {
		this.inventorySituationListeners.get(i).onInventorySituationEvent(e);
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

}
