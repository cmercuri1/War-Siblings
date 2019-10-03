/** War Siblings
 * AttributePanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;

public class AttributePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AttributePanel() {
		setBackground(new Color(153, 0, 0));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		IconPanel iconPanel_14 = new IconPanel("res/images/Attributes/Helmet_stat_icon.png", new Color(176, 196, 222));
		add(iconPanel_14, "2, 2, fill, fill");
		
		IconPanel iconPanel_15 = new IconPanel("res/images/Attributes/Melee_skill.png", new Color(0, 100, 0));
		add(iconPanel_15, "4, 2, fill, fill");
		
		IconPanel iconPanel_12 = new IconPanel("res/images/Attributes/Armor_stat_icon.png", new Color(176, 196, 222));
		add(iconPanel_12, "2, 4, fill, fill");
		
		IconPanel iconPanel_13 = new IconPanel("res/images/Attributes/Ranged_skill.png", new Color(0, 100, 0));
		add(iconPanel_13, "4, 4, fill, fill");
		
		IconPanel iconPanel_11 = new IconPanel("res/images/Attributes/Health.png", new Color(220, 20, 60));
		add(iconPanel_11, "2, 6, fill, fill");
		
		IconPanel iconPanel_10 = new IconPanel("res/images/Attributes/Melee_defense.png", new Color(0, 100, 0));
		add(iconPanel_10, "4, 6, fill, fill");
		
		IconPanel iconPanel_8 = new IconPanel("res/images/Attributes/Action_points.png", new Color(255, 215, 0));
		add(iconPanel_8, "2, 8, fill, fill");
		
		IconPanel iconPanel_9 = new IconPanel("res/images/Attributes/Ranged_defense.png", new Color(0, 100, 0));
		add(iconPanel_9, "4, 8, fill, fill");
		
		IconPanel iconPanel_6 = new IconPanel("res/images/Attributes/Fatigue.png", new Color(100, 149, 237));
		add(iconPanel_6, "2, 10, fill, fill");
		
		IconPanel iconPanel_7 = new IconPanel("res/images/Attributes/Damage_dealt.png", new Color(0, 100, 0));
		add(iconPanel_7, "4, 10, fill, fill");
		
		IconPanel iconPanel_5 = new IconPanel("res/images/Attributes/Morale_state.png", new Color(154, 205, 50));
		add(iconPanel_5, "2, 12, fill, fill");
		
		IconPanel iconPanel_4 = new IconPanel("res/images/Attributes/Armor_damage.png", new Color(0, 100, 0));
		add(iconPanel_4, "4, 12, fill, fill");
		
		IconPanel iconPanel_3 = new IconPanel("res/images/Attributes/Resolve.png", new Color(0, 100, 0));
		add(iconPanel_3, "2, 14, fill, fill");
		
		IconPanel iconPanel_2 = new IconPanel("res/images/Attributes/Chance_to_hit_head.png", new Color(0, 100, 0));
		add(iconPanel_2, "4, 14, fill, fill");
		
		IconPanel iconPanel_1 = new IconPanel("res/images/Attributes/Initiative.png", new Color(0, 100, 0));
		add(iconPanel_1, "2, 16, fill, fill");
		
		IconPanel iconPanel = new IconPanel("res/images/Attributes/Vision.png", new Color(0, 100, 0));
		add(iconPanel, "4, 16, fill, fill");

	}

}
