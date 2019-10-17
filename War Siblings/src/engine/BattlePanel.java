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
	protected String selectedToD;
	protected String selectedBf;
	protected String selectedFoe;

	protected BattleConditions battle;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BattlePanel() {
		this.selectedToD = "DAY";
		this.selectedBf = "SWAMP";
		this.selectedFoe = "HUMAN";
		
		JButton btnStartBattle = new JButton("Start Battle");
		JLabel lblBattleConditions = new JLabel("Battle Conditions:");
		JButton btnEndBattle = new JButton("End battle");
		JLabel lblTimeOfDay = new JLabel("Time of Day");
		JComboBox<String> todBox = new JComboBox<String>(
				Arrays.stream(BattleConditions.TimeOfDay.values()).map(Enum::name).toArray(String[]::new));
		JLabel lblBattlefield = new JLabel("Battlefield");
		JComboBox<String> bfBox = new JComboBox<String>(
				Arrays.stream(BattleConditions.Battlefield.values()).map(Enum::name).toArray(String[]::new));
		JLabel lblEnemy = new JLabel("Enemy");
		JComboBox<String> foeBox = new JComboBox<String>(
				Arrays.stream(BattleConditions.Foes.values()).map(Enum::name).toArray(String[]::new));

		btnEndBattle.setVisible(false);
		
		setLayout(new MigLayout("", "[][][][grow]", "[][][][]"));
		add(btnStartBattle, "cell 0 0,growx");
		add(lblBattleConditions, "cell 3 0");
		add(btnEndBattle, "cell 0 1,growx");
		add(lblTimeOfDay, "cell 2 1");
		add(todBox, "cell 3 1,growx");
		add(lblBattlefield, "cell 2 2,grow");
		add(bfBox, "cell 3 2,growx");
		add(lblEnemy, "cell 2 3");
		add(foeBox, "cell 3 3,growx");

		btnStartBattle.addActionListener((event) -> {
			this.battle = new BattleConditions(BattleConditions.TimeOfDay.valueOf(selectedToD),
					BattleConditions.Battlefield.valueOf(selectedBf), BattleConditions.Foes.valueOf(selectedFoe));
			btnStartBattle.setVisible(false);
			todBox.setEnabled(false);
			bfBox.setEnabled(false);
			foeBox.setEnabled(false);
			btnEndBattle.setVisible(true);
			System.out.println(battle.toString());
		});

		btnEndBattle.addActionListener((event) -> {
			btnStartBattle.setVisible(true);
			todBox.setEnabled(true);
			bfBox.setEnabled(true);
			foeBox.setEnabled(true);
			btnEndBattle.setVisible(false);
		});

		todBox.addItemListener((event) -> this.selectedToD = (String) event.getItem());

		bfBox.addItemListener((event) -> this.selectedBf = (String) event.getItem());

		foeBox.addItemListener((event) -> this.selectedFoe = (String) event.getItem());
	}

}
