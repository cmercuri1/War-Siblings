/** War Siblings
 * AbilityPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;

import character.Character;

import java.awt.Color;
import javax.swing.ScrollPaneConstants;

public class AbilityPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane scrollPane;

	AbilityDisplayPanel a;

	/**
	 * Create the panel.
	 */
	public AbilityPanel() {
		setBackground(new Color(102, 0, 0));
		setLayout(new MigLayout("", "[grow]", "[grow]"));

		a = new AbilityDisplayPanel();

		scrollPane = new JScrollPane(a);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setForeground(new Color(165, 42, 42));
		scrollPane.setBackground(new Color(165, 42, 42));

		add(scrollPane, "cell 0 0,grow");
	}

	/**
	 * @param abm
	 */
	public void update(Character curr) {
		a.update(curr);
		this.revalidate();
		this.repaint();
	}

}
