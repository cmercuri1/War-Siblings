/** War Siblings
 * PanelAttempt
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;
import net.miginfocom.swing.MigLayout;

public class IconPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @wbp.nonvisual location=58,74
	 */

	/**
	 * Create the panel.
	 */
	public IconPanel(String filename, Color color) {
		setBackground(new Color(153, 0, 0));
		setMaximumSize(new Dimension(32767, 20));
		setMinimumSize(new Dimension(10, 20));
		setPreferredSize(new Dimension(465, 58));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new StretchIcon(filename, true));

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		progressBar.setPreferredSize(new Dimension(146, 15));
		progressBar.setOpaque(true);
		progressBar.setMaximumSize(new Dimension(32767, 40));
		progressBar.setMinimumSize(new Dimension(10, 15));
		progressBar.setBackground(new Color(47, 79, 79));
		progressBar.setForeground(color);
		progressBar.setValue(50);
		progressBar.setStringPainted(true);
		setLayout(new MigLayout("",
				"[10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10]",
				"[10][10][10][10][10][10][10]"));
		add(lblNewLabel, "cell 0 0 7 7,grow");
		add(progressBar, "cell 7 1 30 5,grow");

	}

}
