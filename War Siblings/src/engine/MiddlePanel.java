/** War Siblings
 * MiddlePanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class MiddlePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MiddlePanel(ItemHandler handler) {
		setBackground(new Color(153, 0, 0));
		setForeground(Color.DARK_GRAY);
		setLayout(new MigLayout("", "[1560px]", "[880px]"));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, "cell 0 0, grow");

		InventoryPanel inventoryPanel = new InventoryPanel(handler);
		tabbedPane.addTab("Stash", null, inventoryPanel, null);

		PerkPanel perkPanel = new PerkPanel();
		tabbedPane.addTab("Perks", null, perkPanel, null);

	}

}
