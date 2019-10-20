/** War Siblings
 * CharacterPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;

import character.Character;
import event_classes.CharacterEvent;
import listener_interfaces.CharacterListener;
import net.miginfocom.swing.MigLayout;
import storage_classes.BattleConditions;

import java.awt.Color;

public class CharacterPanel extends JPanel implements CharacterListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Character currChar;
	EquipmentPanel equipmentPanel;
	AbilityPanel abilityPanel;
	AttributePanel attributePanel;

	/**
	 * Create the panel.
	 */
	public CharacterPanel() {
		setBackground(new Color(153, 0, 0));

		setLayout(new MigLayout("", "[360,grow]", "[50,grow][275,grow][60,grow][150,grow]"));

		equipmentPanel = new EquipmentPanel();
		add(equipmentPanel, "cell 0 1,grow");

		abilityPanel = new AbilityPanel();
		add(abilityPanel, "cell 0 2,grow");

		attributePanel = new AttributePanel();
		attributePanel.setBackground(new Color(102, 0, 0));
		add(attributePanel, "cell 0 3,grow");

		currChar = new Character();
		currChar.addCharacterListener(this);
	}

	public void applyCharacter() {
		equipmentPanel.update(currChar.getIm());
		abilityPanel.update(currChar);
		attributePanel.update(currChar.getIm(), currChar.getAm());
	}

	public void changeCharacter(String background) {
		currChar.onCharacterEvent(new CharacterEvent(CharacterEvent.Task.CHANGED_CHARACTER, background, null));
		applyCharacter();
	}

	public void startBattle(BattleConditions conditions) {
		currChar.getEm().startBattle(conditions);
	}

	public void endBattle(BattleConditions conditions) {
		currChar.getEm().endBattle(conditions);
	}

	@Override
	public void onCharacterEvent(CharacterEvent c) {
		switch (c.getTask()) {
		case CHANGED_CHARACTER:
			break;
		case FINISHED_CHARACTER:
			break;
		case UPDATED_CHARACTER:
			applyCharacter();
			break;
		}
	}

}
