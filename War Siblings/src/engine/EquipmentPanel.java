/** War Siblings
 * EquipmentPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;

import character.InventoryManager;
import global_managers.GlobalManager;

public class EquipmentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ItemLabel headArmor;
	protected ItemLabel bodyArmor;
	protected ItemLabel rightItem;
	protected ItemLabel leftItem;
	protected ItemLabel bag1;
	protected ItemLabel bag2;
	protected ItemLabel accessory;
	protected ItemLabel ammo;

	/**
	 * Create the panel.
	 */
	public EquipmentPanel() {
		setBackground(Color.DARK_GRAY);
		setLayout(new MigLayout("", "[30,grow][30,grow][30,grow][30,grow][30,grow][30,grow][30,grow][30,grow]",
				"[10,grow][50,grow][10,grow][110,grow][10,grow][5,grow][30,grow][30,grow]"));
		
		ItemHandler handler = new ItemHandler();

		accessory = new ItemLabel(GlobalManager.equipment.DEFAULTACCESSORY, false);
		accessory.setTransferHandler(handler);
		add(accessory, "cell 0 1 2 2,grow");

		headArmor = new ItemLabel(GlobalManager.equipment.DEFAULTHEAD, false);
		accessory.setTransferHandler(handler);
		add(headArmor, "cell 3 0 2 2,grow");

		rightItem = new ItemLabel(GlobalManager.equipment.DEFAULTRIGHT, false);
		rightItem.setTransferHandler(handler);
		add(rightItem, "cell 0 3 2 2,grow");

		leftItem = new ItemLabel(GlobalManager.equipment.DEFAULTLEFT, false);
		leftItem.setTransferHandler(handler);
		add(leftItem, "cell 6 3 2 2,grow");

		bodyArmor = new ItemLabel(GlobalManager.equipment.DEFAULTBODY, false);
		bodyArmor.setTransferHandler(handler);
		add(bodyArmor, "cell 3 2 2 2,grow");

		ammo = new ItemLabel(GlobalManager.equipment.DEFAULTAMMO, false);
		ammo.setTransferHandler(handler);
		add(ammo, "cell 6 1 2 2,grow");

		bag1 = new ItemLabel(GlobalManager.equipment.DEFAULTBAG, true);
		bag1.setTransferHandler(handler);
		add(bag1, "cell 2 6 2 2, grow");

		bag2 = new ItemLabel(GlobalManager.equipment.DEFAULTBAG, true);
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
}
