/** War Siblings
 * InventoryManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import storage_classes.ArrayList;
import storage_classes.BackgroundItem;
import event_classes.TraitEvent;
import event_classes.ModifierEvent;
import effect_classes.Modifier;
import event_classes.CharacterInventoryEvent;
import event_classes.InventoryEvent;
import event_classes.InventorySituationEvent;
import event_classes.SkillPreferenceEvent;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;
import items.Armor;
import items.EquipItem;
import items.Headgear;
import items.Shield;
import items.Weapon;
import listener_interfaces.TraitListener;
import listener_interfaces.ModifierListener;
import listener_interfaces.CharacterInventoryListener;
import listener_interfaces.InventoryListener;
import listener_interfaces.InventorySituationListener;
import listener_interfaces.SkillPreferenceListener;
import notifier_interfaces.TraitNotifier;
import notifier_interfaces.ModifierNotifier;
import notifier_interfaces.InventoryNotifier;
import notifier_interfaces.InventorySituationNotifier;
import notifier_interfaces.MultiNotifier;

/**
 * Manager specifically for keeping track of and managing the inventory/equiped
 * items of a character
 */
public class InventoryManager implements CharacterInventoryListener, SkillPreferenceListener, MultiNotifier,
		InventoryNotifier, ModifierNotifier, TraitNotifier, InventorySituationNotifier {
	protected Armor body;
	protected Headgear head;
	protected EquipItem right;
	protected EquipItem left;

	protected ArrayList<EquipItem> bag;

	protected ArrayList<TraitListener> traitListeners;
	protected ArrayList<ModifierListener> effectListeners;
	protected ArrayList<InventoryListener> inventoryListeners;
	protected ArrayList<InventorySituationListener> inventorySituationListeners;

	protected double rangedPref;

	protected enum ARM {
		LEFT, RIGHT
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
		if ((rangedPref > 0) && (bg.getBackground().equals("Deserter") || bg.getBackground().equals("Sellsword")))
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
		} else if (temp instanceof Weapon) {
			this.removeWeaponStats((Weapon) temp);
		}

		if (next instanceof Shield) {
			this.grantShieldDefense((Shield) next);
		} else if (next instanceof Weapon) {
			this.grantWeaponStats((Weapon) next);
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
		Modifier fatiguePen;
		Modifier initiativePen;
		try {
			fatiguePen = new Modifier("fatigue_Final", old.getFatigueRed().getAlteredValue());
			initiativePen = new Modifier("initiative_Final", old.getFatigueRed().getAlteredValue());
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.REMOVE, fatiguePen, this));
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.REMOVE, initiativePen, this));
		} catch (NullPointerException nu) {

		}
		try {
			fatiguePen = new Modifier("fatigue_Final", next.getFatigueRed().getAlteredValue());
			initiativePen = new Modifier("initiative_Final", next.getFatigueRed().getAlteredValue());
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.ADD, fatiguePen, this));
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.ADD, initiativePen, this));
		} catch (NullPointerException nu) {

		}
	}

	protected void impedeVision(Headgear old, Headgear next) {
		Modifier visionPen;
		try {
			visionPen = new Modifier("vision_Final", old.getVisRed());
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.REMOVE, visionPen, this));
		} catch (NullPointerException nu) {

		}
		try {
			visionPen = new Modifier("vision_Final", next.getVisRed());
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.ADD, visionPen, this));
		} catch (NullPointerException nu) {

		}
	}

	protected void removeShieldDefense(Shield old) {
		try {
			Modifier meleeDefense = new Modifier("meleeDefense", old.getMeleeDef().getAlteredValue());
			Modifier rangedDefense = new Modifier("rangedDefense", old.getRangedDef().getAlteredValue());

			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.REMOVE, meleeDefense, this));
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.REMOVE, rangedDefense, this));
		} catch (NullPointerException nu) {

		}
	}

	protected void grantShieldDefense(Shield next) {
		try {
			Modifier meleeDefense = new Modifier("meleeDefense", next.getMeleeDef().getAlteredValue());
			Modifier rangedDefense = new Modifier("rangedDefense", next.getRangedDef().getAlteredValue());

			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.ADD, meleeDefense, this));
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.ADD, rangedDefense, this));
		} catch (NullPointerException nu) {

		}
	}

	protected void removeWeaponStats(Weapon old) {
		try {
			Modifier minDam = new Modifier("damage", old.getMinDam().getAlteredValue());
			Modifier ignArm = new Modifier("ignoreArmor", old.getIgnArm().getAlteredValue());
			Modifier armDam = new Modifier("armorDamage", old.getArmDam().getAlteredValue());
			Modifier headShot = new Modifier("headshotChance", old.getHeadShot().getAlteredValue());

			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.REMOVE, minDam, this));
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.REMOVE, ignArm, this));
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.REMOVE, armDam, this));
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.REMOVE, headShot, this));
		} catch (NullPointerException nu) {

		}
	}

	protected void grantWeaponStats(Weapon next) {
		try {
			Modifier minDam = new Modifier("damage", next.getMinDam().getAlteredValue());
			Modifier ignArm = new Modifier("ignoreArmor", next.getIgnArm().getAlteredValue());
			Modifier armDam = new Modifier("armorDamage", next.getArmDam().getAlteredValue());
			Modifier headShot = new Modifier("headshotChance", next.getHeadShot().getAlteredValue());

			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.ADD, minDam, this));
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.ADD, ignArm, this));
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.ADD, armDam, this));
			this.notifyModifierListeners(new ModifierEvent(ModifierEvent.Task.ADD, headShot, this));
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
	public void onCharacterInventoryEvent(CharacterInventoryEvent c) {
		switch (c.getTask()) {
		case CHANGE_BODY:
			this.swapBody((Armor) c.getInformation());
			break;
		case CHANGE_HEAD:
			this.swapHead((Headgear) c.getInformation());
			break;
		case CHANGE_LEFT:
			this.swapItem(ARM.LEFT, c.getInformation());
			break;
		case CHANGE_RIGHT:
			this.swapItem(ARM.RIGHT, c.getInformation());
			break;
		case REMOVE_BODY:
			this.swapBody(GlobalManager.equipment.DEFAULTBODY);
			break;
		case REMOVE_HEAD:
			this.swapHead(GlobalManager.equipment.DEFAULTHEAD);
			break;
		case REMOVE_LEFT:
			this.swapItem(ARM.LEFT, GlobalManager.equipment.DEFAULTLEFT);
			break;
		case REMOVE_RIGHT:
			this.swapItem(ARM.RIGHT, GlobalManager.equipment.DEFAULTRIGHT);
			break;
		case REMOVE_ALL:
			this.swapBody(GlobalManager.equipment.DEFAULTBODY);
			this.swapHead(GlobalManager.equipment.DEFAULTHEAD);
			this.swapItem(ARM.LEFT, GlobalManager.equipment.DEFAULTLEFT);
			this.swapItem(ARM.RIGHT, GlobalManager.equipment.DEFAULTRIGHT);
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

}
