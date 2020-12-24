/** War Siblings
 * InventoryPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class InventoryPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public InventoryPanel(ItemHandler handler) {
		setBackground(new Color(153, 0, 0));
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane(new InventoryDisplayPanel(handler));
		add(scrollPane, "cell 0 0, grow");
	}

}
