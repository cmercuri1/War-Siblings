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

	/**
	 * Create the panel.
	 */
	public EquipmentPanel() {
		setBackground(Color.DARK_GRAY);

		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/EmptyAccessory.png"), true));
		setLayout(new MigLayout("",
				"[30,grow][30,grow][30,grow][30,grow][30,grow][30,grow][30,grow][30,grow]",
				"[10,grow][50,grow][10,grow][110,grow][10,grow][5,grow][30,grow][30,grow]"));
		add(label, "cell 0 1 2 2,grow");

		JLabel label_1 = new JLabel("");
		label_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_1.setIcon(
				new StretchIcon(EquipmentPanel.class.getResource("/images/Items/Headgear/EmptyHead.png"), true));
		add(label_1, "cell 3 0 2 2,grow");

		JLabel label_5 = new JLabel("");
		label_5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_5.setIcon(
				new StretchIcon(EquipmentPanel.class.getResource("/images/Items/Weapons/EmptyRight.png"), true));
		add(label_5, "cell 0 3 2 2,grow");

		JLabel label_2 = new JLabel("");
		label_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_2.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/Shields/EmptyLeft.png"), true));
		add(label_2, "cell 6 3 2 2,grow");

		JLabel label_3 = new JLabel("");
		label_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_3.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/Armor/EmptyBody.png"), true));
		add(label_3, "cell 3 2 2 2,grow");

		JLabel label_4 = new JLabel("");
		label_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_4.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/EmptyAmmo.png"), true));
		add(label_4, "cell 6 1 2 2,grow");

		JLabel label_6 = new JLabel("");
		label_6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_6.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/EmptyBag.png"), true));
		add(label_6, "cell 2 6 2 2, grow");

		JLabel label_7 = new JLabel("");
		label_7.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_7.setIcon(new StretchIcon(EquipmentPanel.class.getResource("/images/Items/EmptyBag.png"), true));
		add(label_7, "cell 4 6 2 2, grow");

	}

	/**
	 * @param im
	 */
	public void update(InventoryManager im) {
		// TODO Auto-generated method stub
		
	}
}
