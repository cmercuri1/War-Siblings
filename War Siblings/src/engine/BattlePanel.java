/** War Siblings
 * BattlePanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import storage_classes.BattleConditions;

import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class BattlePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BattlePanel() {
		setLayout(new MigLayout("", "[][][][grow]", "[][][][]"));

		JButton btnStartBattle = new JButton("Start Battle");
		add(btnStartBattle, "cell 0 0,growx");

		JLabel lblBattleConditions = new JLabel("Battle Conditions:");
		add(lblBattleConditions, "cell 3 0");

		JButton btnEndBattle = new JButton("End battle");
		add(btnEndBattle, "cell 0 1,growx");

		JLabel lblTimeOfDay = new JLabel("Time of Day");
		add(lblTimeOfDay, "cell 2 1");

		JComboBox<String> comboBox = new JComboBox<String>(
				Arrays.stream(BattleConditions.TimeOfDay.values()).map(Enum::name).toArray(String[]::new));
		add(comboBox, "cell 3 1,growx");

		JLabel lblBattlefield = new JLabel("Battlefield");
		add(lblBattlefield, "cell 2 2,grow");

		JComboBox<String> comboBox_1 = new JComboBox<String>(
				Arrays.stream(BattleConditions.Battlefield.values()).map(Enum::name).toArray(String[]::new));
		add(comboBox_1, "cell 3 2,growx");

		JLabel lblEnemy = new JLabel("Enemy");
		add(lblEnemy, "cell 2 3");

		JComboBox<String> comboBox_2 = new JComboBox<String>(
				Arrays.stream(BattleConditions.Foes.values()).map(Enum::name).toArray(String[]::new));
		add(comboBox_2, "cell 3 3,growx");

	}

}
