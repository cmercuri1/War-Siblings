/** War Siblings
 * TestPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;

public class TestPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TestPanel() {
		setLayout(new MigLayout("", "[260px]", "[90px]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); 
		add(tabbedPane, "cell 0 0,alignx left,aligny top");
		
		TestCharPanel testCharPanel = new TestCharPanel();
		tabbedPane.addTab("Character Tab", null, testCharPanel, null);
		
		BattlePanel battlePanel = new BattlePanel();
		tabbedPane.addTab("Battle Tab", null, battlePanel, null);

	}

}
