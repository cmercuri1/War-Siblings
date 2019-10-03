/** War Siblings
 * AbilityPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;

public class AbilityPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AbilityPanel() {
		setBackground(new Color(102, 0, 0));
		setLayout(new MigLayout("", "[grow]", "[grow]"));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(165, 42, 42));
		scrollPane.setBackground(new Color(165, 42, 42));
		add(scrollPane, "cell 0 0,grow");
	}

}
