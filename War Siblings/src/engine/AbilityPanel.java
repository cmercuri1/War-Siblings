/** War Siblings
 * AbilityPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import storage_classes.Trait;

import javax.swing.JScrollPane;

import character.AbilityManager;

import javax.swing.JLabel;
import java.awt.Color;

public class AbilityPanel extends JPanel {
	JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	public AbilityPanel() {
		setBackground(new Color(102, 0, 0));
		setLayout(new MigLayout("", "[grow]", "[grow]"));

		scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(165, 42, 42));
		scrollPane.setBackground(new Color(165, 42, 42));
		add(scrollPane, "cell 0 0,grow");
	}

	/**
	 * @param abm
	 */
	public void update(AbilityManager abm) {
		scrollPane.removeAll();
		for (Trait t:abm.getTraits()) {
			JLabel temp = new JLabel("");
			temp.setIcon(new StretchIcon(t.getImage().getImage(),true));
			temp.setToolTipText(t.toString());
			scrollPane.add(temp);
		}
	}

}
