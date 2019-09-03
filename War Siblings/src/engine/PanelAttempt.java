/** War Siblings
 * PanelAttempt
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class PanelAttempt extends JPanel {
	/**
	 * @wbp.nonvisual location=58,74
	 */
	private final ImageIcon imageIcon = new ImageIcon();

	/**
	 * Create the panel.
	 */
	public PanelAttempt() {
		imageIcon.setImage(Toolkit.getDefaultToolkit().getImage(PanelAttempt.class.getResource("/images/Attributes/Armor_stat_icon.png")));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new StretchIcon("res/images/Attributes/Fatigue.png", true));
		add(lblNewLabel);

	}

}
