/** War Siblings
 * TestPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;

import global_managers.GlobalManager;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class TestCharPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TestCharPanel() {
		setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		JLabel lblSelectedBackground = new JLabel("Selected Background");
		add(lblSelectedBackground, "cell 1 0");
		
		JButton btnNewCharacter = new JButton("New Character");
		add(btnNewCharacter, "cell 0 1");
		
		JComboBox<String> comboBox = new JComboBox<String>(GlobalManager.backgrounds.getBgNames());
		add(comboBox, "cell 1 1,growx");

	}

}
