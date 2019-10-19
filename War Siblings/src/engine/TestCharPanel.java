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
	
	protected String selectedBg;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TestCharPanel() {
		this.selectedBg = "Random";
		
		JLabel lblSelectedBackground = new JLabel("Selected Background");
		JButton btnNewCharacter = new JButton("New Character");
		JComboBox<String> bgBox = new JComboBox<String>(GlobalManager.backgrounds.getBgNames());
		
		setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		add(lblSelectedBackground, "cell 1 0");
		add(btnNewCharacter, "cell 0 1");
		add(bgBox, "cell 1 1,growx");

		bgBox.addItemListener((event)->this.selectedBg = (String) event.getItem());
		btnNewCharacter.addActionListener((event)->this.firePropertyChange("Background Selected", null, this.selectedBg));
	}

}
