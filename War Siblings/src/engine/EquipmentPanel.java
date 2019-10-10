/** War Siblings
 * EquipmentPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import character.InventoryManager;

public class EquipmentPanel extends JPanel {

	protected JLabel headArmor;
	protected JLabel bodyArmor;
	protected JLabel rightItem;
	protected JLabel leftItem;
	protected JLabel bag1;
	protected JLabel bag2;
	protected JLabel accessory;
	protected JLabel ammo;
	
	/**
	 * Create the panel.
	 */
	public EquipmentPanel() {
		setBackground(Color.DARK_GRAY);

		accessory = new JLabel("");
		accessory.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		accessory.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/EmptyAccessory.png"), true));
		setLayout(new MigLayout("",
				"[30,grow][30,grow][30,grow][30,grow][30,grow][30,grow][30,grow][30,grow]",
				"[10,grow][50,grow][10,grow][110,grow][10,grow][5,grow][30,grow][30,grow]"));
		add(accessory, "cell 0 1 2 2,grow");

		headArmor = new JLabel("");
		headArmor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		headArmor.setIcon(
				new StretchIcon(EquipmentPanel.class.getResource("/images/Items/Headgear/EmptyHead.png"), true));
		add(headArmor, "cell 3 0 2 2,grow");

		rightItem = new JLabel("");
		rightItem.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		rightItem.setIcon(
				new StretchIcon(EquipmentPanel.class.getResource("/images/Items/Weapons/EmptyRight.png"), true));
		add(rightItem, "cell 0 3 2 2,grow");

		leftItem = new JLabel("");
		leftItem.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		leftItem.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/Shields/EmptyLeft.png"), true));
		add(leftItem, "cell 6 3 2 2,grow");

		bodyArmor = new JLabel("");
		bodyArmor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		bodyArmor.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/Armor/EmptyBody.png"), true));
		add(bodyArmor, "cell 3 2 2 2,grow");

		ammo = new JLabel("");
		ammo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		ammo.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/EmptyAmmo.png"), true));
		add(ammo, "cell 6 1 2 2,grow");

		bag1 = new JLabel("");
		bag1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		bag1.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/EmptyBag.png"), true));
		add(bag1, "cell 2 6 2 2, grow");

		bag2 = new JLabel("");
		bag2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		bag2.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/EmptyBag.png"), true));
		add(bag2, "cell 4 6 2 2, grow");

	}

	/**
	 * @param im
	 */
	public void update(InventoryManager im) {
		headArmor.setIcon(new StretchIcon(im.getHead().getImage().getImage(),true));
		headArmor.setToolTipText(im.getHead().toString());

		bodyArmor.setIcon(new StretchIcon(im.getBody().getImage().getImage(),true));
		bodyArmor.setToolTipText(im.getBody().toString());

		rightItem.setIcon(new StretchIcon(im.getRight().getImage().getImage(),true));
		rightItem.setToolTipText(im.getRight().toString());

		leftItem.setIcon(new StretchIcon(im.getLeft().getImage().getImage(),true));
		leftItem.setToolTipText(im.getLeft().toString());
	}
}
