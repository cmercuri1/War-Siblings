/** War Siblings
 * AttributePanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import character.AttributeManager;
import character.InventoryManager;
import net.miginfocom.swing.MigLayout;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;

public class AttributePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AttributePanel() {
		setBackground(new Color(153, 0, 0));
		setLayout(new MigLayout("",
				"[370,grow][370,grow]",
				"[70,grow][70,grow][70,grow][70,grow][70,grow][70,grow][70,grow][70,grow]"));
		
		IconPanel iconPanel_14 = new IconPanel("res/images/Attributes/Helmet_stat_icon.png", new Color(176, 196, 222));
		iconPanel_14.setToolTipText("<html>When the head of a character is hit, the armor points of the worn helmet<br>\r\n\t\tare reduced. Depending on how much damage is done, some of the<br>\r\n\t\tdamage might go directly through the helmet to the hitpoints of the<br>\r\n\t\tcharacter. The head of a character is harder to hit than the body, but more<br>\r\n\t\tvulnerable to damage.</html>");
		add(iconPanel_14, "cell 0 0, grow");
		
		IconPanel iconPanel_15 = new IconPanel("res/images/Attributes/Melee_skill.png", new Color(0, 100, 0));
		iconPanel_15.setToolTipText("<html>Determines the base probability of hitting a target with a melee<br>\r\n\t\tattack, such as with swords and spears. Can be increased as the<br>\r\n\t\tcharacter gains experience.</html>");
		add(iconPanel_15, "cell 1 0, grow");
		
		IconPanel iconPanel_12 = new IconPanel("res/images/Attributes/Armor_stat_icon.png", new Color(176, 196, 222));
		iconPanel_12.setToolTipText("<html>When the body of a character is hit, the armor points of the worn body<br>\r\n\t\tarmor are reduced. Depending on how much damage is done, some of<br>\r\n\t\tthe damage might go directly through the armor to the hitpoints of the<br>\r\n\t\tcharacter.</html>");
		add(iconPanel_12, "cell 0 1, grow");
		
		IconPanel iconPanel_13 = new IconPanel("res/images/Attributes/Ranged_skill.png", new Color(0, 100, 0));
		iconPanel_13.setToolTipText("<html>Determines the base probability of hitting a target with a ranged<br>\r\n\t\tattack, such as with bows and crossbows. Can be increased as the<br>\r\n\t\tcharacter gains experience.</html>");
		add(iconPanel_13, "cell 1 1, grow");
		
		IconPanel iconPanel_11 = new IconPanel("res/images/Attributes/Health.png", new Color(220, 20, 60));
		iconPanel_11.setToolTipText("<html>Hitpoints represent the damage a character can take before dying.<br>\r\n\t\tOnce these reach zero, the character is considered dead. The higher<br>\r\n\t\tthe maximum hitpoints, the less likely it is a character will suffer<br>\r\n\t\tdebilitating injuries when hit.</html>");
		add(iconPanel_11, "cell 0 2, grow");
		
		IconPanel iconPanel_10 = new IconPanel("res/images/Attributes/Melee_defense.png", new Color(0, 100, 0));
		iconPanel_10.setToolTipText("<html>A higher melee defense reduces the probability of being hit with a<br>\r\n\t\tmassive attack, such as the thrust of a spear. It can be increased as<br>\r\n\t\tthe character gains experience and by equipping a good shield.</html>");
		add(iconPanel_10, "cell 1 2, grow");
		
		IconPanel iconPanel_8 = new IconPanel("res/images/Attributes/Action_points.png", new Color(255, 215, 0));
		iconPanel_8.setToolTipText("<html>Action Points (AP) are spent for every action, like moving or using a<br>\r\n\t\tskill. Once all points are spent, the current character's turn ends<br>\r\n\t\tautomatically. AP are fully refreshed each turn.</html>");
		add(iconPanel_8, "cell 0 3, grow");
		
		IconPanel iconPanel_9 = new IconPanel("res/images/Attributes/Ranged_defense.png", new Color(0, 100, 0));
		iconPanel_9.setToolTipText("<html>A higher ranged defense reduces the probability of being hit with a<br>\r\n\t\tranged attack, such as an arrow shot from afar. It can be increased<br>\r\n\t\tas the character gains experience and by equipping a good shield.</html>");
		add(iconPanel_9, "cell 1 3, grow");
		
		IconPanel iconPanel_6 = new IconPanel("res/images/Attributes/Fatigue.png", new Color(100, 149, 237));
		iconPanel_6.setToolTipText("<html>Fatigue is gained for every action, like moving or using skills, and<br>\r\n\t\twhen being hit in combat or when dodging/blocking an attack. It is<br>\r\n\t\treduced at a certain rate each turn. If a character accumulates too<br>\r\n\t\tmuch Fatigue they may need to rest a turn (i.e. do nothing) before<br>\r\n\t\tbeing able to use more specialized skills again. Fatigue has a synergy<br>\r\n\t\twith Initiative. Initiative is lowered as Fatigue is accumulated.</html>");
		add(iconPanel_6, "cell 0 4, grow");
		
		IconPanel iconPanel_7 = new IconPanel("res/images/Attributes/Damage_dealt.png", new Color(0, 100, 0));
		iconPanel_7.setToolTipText("<html>The base damage the currently equipped weapon does. Will be<br>\r\n\t\tapplied in full against hitpoints if no armor is protecting the target. If<br>\r\n\t\tthe target is protected by armor, the damage is applied to armor<br>\r\n\t\tinstead based on the weapon's effectiveness against armor. Depending<br>\r\n\t\ton how much damage is done, some of the damage might go directly<br>\r\n\t\tthrough the worn armor to the hitpoints. The actual damage done is<br>\r\n\t\tmodified by the skill used and the target hit.</html>");
		add(iconPanel_7, "cell 1 4, grow");
		
		IconPanel iconPanel_5 = new IconPanel("res/images/Attributes/Morale_state.png", new Color(154, 205, 50));
		iconPanel_5.setToolTipText("<html>Morale is one of five states and represents the mental condition of<br>\r\n\t\tcombatants and their effectiveness in battle. At the lowest state,<br>\r\n\t\tfleeing, a character will be outside your control - although they may<br>\r\n\t\teventually rally again. Morale changes as the battle unfolds, with<br>\r\n\t\tcharacters that have high resolve less likely to fall to low morale<br>\r\n\t\tstates. Many of your opponents are affected by morale as well.</html>");
		add(iconPanel_5, "cell 0 5, grow");
		
		IconPanel iconPanel_4 = new IconPanel("res/images/Attributes/Armor_damage.png", new Color(0, 100, 0));
		iconPanel_4.setToolTipText("<html>The base percentage of damage that will be applied when hitting a<br>\r\n\t\ttarget protected by armor. Once the armor is destroyed, the weapon<br>\r\n\t\tdamage applies at 100% to hitpoints. The actual damage done is<br>\r\n\t\tmodified by the skill used and the target hit.</html>");
		add(iconPanel_4, "cell 1 5, grow");
		
		IconPanel iconPanel_3 = new IconPanel("res/images/Attributes/Resolve.png", new Color(0, 100, 0));
		iconPanel_3.setToolTipText("<html>Resolve represents the willpower and bravery of characters. High<br>\r\n\t\tResolve makes it less likely that characters fall to lower morale states<br>\r\n\t\tdue to negative events and the more likely that characters gain<br>\r\n\t\tconfidence from positive events. Resolve acts as defense against<br>\r\n\t\tcertain mental attacks that inflict panic, fear or mind control. Resolve<br>\r\n\t\talso affects the ability of a character to rally (leave the Fleeing<br>\r\n\t\tmorale state) and the effectiveness of the Rally the Troops Perk.</html>");
		add(iconPanel_3, "cell 0 6, grow");
		
		IconPanel iconPanel_2 = new IconPanel("res/images/Attributes/Chance_to_hit_head.png", new Color(0, 100, 0));
		iconPanel_2.setToolTipText("<html>The base percentage chance to hit a target's head for increased<br>\r\n\t\tdamage. The final chance can be modified by the skill or weapon used.</html>");
		add(iconPanel_2, "cell 1 6, grow");
		
		IconPanel iconPanel_1 = new IconPanel("res/images/Attributes/Initiative.png", new Color(0, 100, 0));
		iconPanel_1.setToolTipText("<html>The higher this value, the earlier the position in the turn order.<br>\r\n\t\tInitiative is reduced by the current fatigue as well as any penalty to<br>\r\n\t\tmaximum fatigue (such as from heavy armor). In general, someone<br>\r\n\t\tin light armor will act before someone in heavy armor, and someone<br>\r\n\t\tfresh will act before someone fatigued.</html>");
		add(iconPanel_1, "cell 0 7, grow");
		
		IconPanel iconPanel = new IconPanel("res/images/Attributes/Vision.png", new Color(0, 100, 0));
		iconPanel.setToolTipText("<html>Vision, or view range, determines how far a character can see to<br>\r\n\t\tuncover the fog of war, discover threats and hit with ranged attacks.<br>\r\n\t\tHeavier helmets can reduce vision. Certain Traits or Injuries can also<br>\r\n\t\taffect Vision.</html>");
		add(iconPanel, "cell 1 7, grow");

	}

	/**
	 * @param im
	 * @param am
	 */
	public void update(InventoryManager im, AttributeManager am) {
		
	}

}
