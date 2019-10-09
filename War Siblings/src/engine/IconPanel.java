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
		setBackground(new Color(102, 0, 0));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new StretchIcon(filename, true));

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		progressBar.setOpaque(true);
		progressBar.setBackground(new Color(47, 79, 79));
		progressBar.setForeground(color);
		progressBar.setValue(50);
		progressBar.setStringPainted(true);
		setLayout(new MigLayout("",
				"[50,grow][10,grow][10,grow][50,grow][50,grow][50,grow][50,grow][50,grow][50,grow]",
				"[10,grow][50,grow][10,grow]"));
		add(lblNewLabel, "cell 0 0 3 3,grow");
		add(progressBar, "cell 3 1 6 1,grow");
	}
	
	public void setValue(String val) {
		
	}

}
