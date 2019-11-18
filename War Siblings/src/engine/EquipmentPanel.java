/** War Siblings
 * EquipmentPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import notifier_interfaces.CharacterInventoryNotifier;
import storage_classes.ArrayList;

import java.awt.Color;

import character.InventoryManager;
import event_classes.CharacterInventoryEvent;
import event_classes.CharacterInventoryEvent.Task;
import global_managers.GlobalManager;
import items.ComboItem;
import items.Item;
import listener_interfaces.CharacterInventoryListener;

public class EquipmentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected EquipmentLabel headArmor;
	protected EquipmentLabel bodyArmor;
	protected EquipmentLabel rightItem;
	protected EquipmentLabel leftItem;
	protected EquipmentLabel bag1;
	protected EquipmentLabel bag2;
	protected EquipmentLabel accessory;
	protected EquipmentLabel ammo;

	/**
	 * Create the panel.
	 */
	public EquipmentPanel(ItemHandler handler) {
		setBackground(Color.DARK_GRAY);
		setLayout(new MigLayout("", "[30,grow][30,grow][30,grow][30,grow][30,grow][30,grow][30,grow][30,grow]",
				"[10,grow][50,grow][10,grow][110,grow][10,grow][5,grow][30,grow][30,grow]"));

		accessory = new EquipmentLabel(GlobalManager.equipment.DEFAULTACCESSORY, false, Task.CHANGE_ACCESSORY);
		accessory.setTransferHandler(handler);
		add(accessory, "cell 0 1 2 2,grow");

		headArmor = new EquipmentLabel(GlobalManager.equipment.DEFAULTHEAD, false, Task.CHANGE_HEAD);
		headArmor.setTransferHandler(handler);
		add(headArmor, "cell 3 0 2 2,grow");

		rightItem = new EquipmentLabel(GlobalManager.equipment.DEFAULTRIGHT, false, Task.CHANGE_RIGHT);
		rightItem.setTransferHandler(handler);
		add(rightItem, "cell 0 3 2 2,grow");

		leftItem = new EquipmentLabel(GlobalManager.equipment.DEFAULTLEFT, false, Task.CHANGE_LEFT);
		leftItem.setTransferHandler(handler);
		add(leftItem, "cell 6 3 2 2,grow");

		bodyArmor = new EquipmentLabel(GlobalManager.equipment.DEFAULTBODY, false, Task.CHANGE_BODY);
		bodyArmor.setTransferHandler(handler);
		add(bodyArmor, "cell 3 2 2 2,grow");

		ammo = new EquipmentLabel(GlobalManager.equipment.DEFAULTAMMO, false, Task.CHANGE_AMMO);
		ammo.setTransferHandler(handler);
		add(ammo, "cell 6 1 2 2,grow");

		bag1 = new EquipmentLabel(GlobalManager.equipment.DEFAULTBAG, true, Task.CHANGE_RIGHT);
		bag1.setTransferHandler(handler);
		add(bag1, "cell 2 6 2 2, grow");

		bag2 = new EquipmentLabel(GlobalManager.equipment.DEFAULTBAG, true, Task.CHANGE_RIGHT);
		bag2.setTransferHandler(handler);
		add(bag2, "cell 4 6 2 2, grow");

	}

	/**
	 * @param im
	 */
	public void update(InventoryManager im) {
		headArmor.setItem(im.getHead());

		bodyArmor.setItem(im.getBody());

		rightItem.setItem(im.getRight());

		leftItem.setItem(im.getLeft());

		bag1.setItem(im.getBag().get(0));

		bag2.setItem(im.getBag().get(1));

		accessory.setItem(im.getAccessory());

		ammo.setItem(im.getAmmunition());
	}

	public void group(InventoryManager im) {
		headArmor.addCharacterInventoryListener(im);

		bodyArmor.addCharacterInventoryListener(im);

		rightItem.addCharacterInventoryListener(im);

		leftItem.addCharacterInventoryListener(im);

		bag1.addCharacterInventoryListener(im);

		bag2.addCharacterInventoryListener(im);

		accessory.addCharacterInventoryListener(im);

		ammo.addCharacterInventoryListener(im);
	}

	private class EquipmentLabel extends ItemLabel implements CharacterInventoryNotifier {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected ArrayList<CharacterInventoryListener> listeners;
		protected Task task;

		public EquipmentLabel(Item item, boolean b, Task task) {
			super(item, b);
			this.listeners = new ArrayList<CharacterInventoryListener>();
			this.task = task;
		}

		public void recieveItem(Item item) {
			this.notifyCharacterInventoryListeners(new CharacterInventoryEvent(task, (ComboItem) item, this));
		}

		@Override
		public void addCharacterInventoryListener(CharacterInventoryListener c) {
			this.listeners.add(c);
		}

		@Override
		public void removeCharacterInventoryListener(CharacterInventoryListener c) {
			this.listeners.remove(c);
		}

		@Override
		public void notifyCharacterInventoryListeners(CharacterInventoryEvent c) {
			this.listeners.forEach(l -> l.onCharacterInventoryEvent(c));
		}

		@Override
		public void notifyCharacterInventoryListener(CharacterInventoryListener c, CharacterInventoryEvent e) {
			this.listeners.get(c).onCharacterInventoryEvent(e);
		}
	}
}
