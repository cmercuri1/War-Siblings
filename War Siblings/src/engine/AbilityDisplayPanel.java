/** War Siblings
 * AbilityDisplayPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import storage_classes.Ability;
import storage_classes.ArrayList;
import storage_classes.Trait;
import character.Character;

public class AbilityDisplayPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AbilityDisplayPanel() {
		setBackground(new Color(102, 0, 0));
		setLayout(new MigLayout("", "[40][40][40][40][40][40][40][40]", "[40][40]"));

	}

	// 8 Icons per row
	public void update(Character curr) {
		ArrayList<JLabel> abilities = new ArrayList<JLabel>();
		ArrayList<JLabel> other = new ArrayList<JLabel>();

		JLabel temp;

		for (Ability a : curr.getAbm().getAbilities()) {
			try {
				temp = new JLabel(new StretchIcon(a.getImage().getImage(), true));
				temp.setToolTipText(a.toString());
				abilities.add(temp);
			} catch (NullPointerException n) {

			}
		}

		temp = new JLabel(new StretchIcon(curr.getBgIcon().getImage(), true));
		temp.setToolTipText(curr.getBackgroundName());
		other.add(temp);

		for (Trait t : curr.getAbm().getTraits()) {
			temp = new JLabel(new StretchIcon(t.getImage().getImage(), true));
			temp.setToolTipText(t.toString());
			other.add(temp);
		}

		this.configureLayout(abilities, other);
	}

	protected void configureLayout(ArrayList<JLabel> abilityList, ArrayList<JLabel> otherList) {
		int temp = abilityList.size();
		int abilityRows = 0;

		String rows = "[40]";
		while (temp > 8) {
			temp -= 8;
			rows += "[40]";
			abilityRows++;
		}

		temp = otherList.size();
		while (temp > 8) {
			temp -= 8;
			rows += "[40]";
		}
		setLayout(new MigLayout("", "[40][40][40][40][40][40][40][40]", rows));

		int i = 0, j = 0;
		int pos = 0;

		while (pos < abilityList.size()) {
			add(abilityList.get(pos), "cell " + i + " " + j + ", grow");
			pos++;
			i++;
			if (i > 7) {
				i = 0;
				j++;
			}
		}

		pos = 0;
		i = 0;
		j = abilityRows;

		while (pos < otherList.size()) {
			add(otherList.get(pos), "cell " + i + " " + j + ", grow");
			pos++;
			i++;
			if (i > 7) {
				i = 0;
				j++;
			}
		}
	}

}
