/** War Siblings
 * CharacterPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;

import character.Character;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class CharacterPanel extends JPanel {
	Character currChar;
	EquipmentPanel equipmentPanel;
	AbilityPanel abilityPanel;
	AttributePanel attributePanel;
	
	/**
	 * Create the panel.
	 */
	public CharacterPanel() {
		setBackground(new Color(153, 0, 0));
		
		setLayout(new MigLayout("", "[350,grow]", "[80,grow][360,grow][100,grow][320,grow]"));
		
		equipmentPanel = new EquipmentPanel();
		add(equipmentPanel, "cell 0 1,grow");
		
		abilityPanel = new AbilityPanel();
		add(abilityPanel, "cell 0 2,grow");
		
		attributePanel = new AttributePanel();
		attributePanel.setBackground(new Color(102, 0, 0));
		add(attributePanel, "cell 0 3,grow");
	}
	
	public void applyCharacter(Character c) {
		equipmentPanel.update(c.getIm());
		abilityPanel.update(c.getAbm());
		attributePanel.update(c.getIm(), c.getAm());
	}

}