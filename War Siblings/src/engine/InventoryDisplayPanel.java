/** War Siblings
 * InventoryDisplayPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JLabel;
import javax.swing.JPanel;

import global_managers.GlobalManager;
import items.Item;
import net.miginfocom.swing.MigLayout;

public class InventoryDisplayPanel extends JPanel {

	protected int rows;
	protected int columns;
	protected String boxSize;

	/**
	 * Create the panel.
	 */
	public InventoryDisplayPanel() {
		this.rows = 21;
		this.columns = 9;

		this.boxSize = "[120]";

		setLayout(new MigLayout("", new String(new char[columns]).replace("\0", boxSize),
				new String(new char[rows]).replace("\0", boxSize)));

		int i = 0;
		int j = 0;
		
		JLabel temp;
		for (Item item:GlobalManager.equipment.getItemList()) {
			temp = new JLabel(new StretchIcon(item.getImage().getImage(), true));
			temp.setToolTipText(item.toString());
			add(temp, "cell " + i + " " + j + ", grow");
			i++;
			if(i >= columns) {
				i = 0;
				j++;
			}
		}
	}

}
