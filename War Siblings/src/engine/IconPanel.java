/** War Siblings
 * PanelAttempt
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import net.miginfocom.swing.MigLayout;
import storage_classes.BarDisplay;

public class IconPanel extends JPanel {
	protected JProgressBar progressBar;
	protected JLabel star;

	protected static final StretchIcon oneStar = new StretchIcon("res/images/Attributes/1_Star_Talent.png", true);
	protected static final StretchIcon twoStar = new StretchIcon("res/images/Attributes/2_Star_Talent.png", true);
	protected static final StretchIcon threeStar = new StretchIcon("res/images/Attributes/3_Star_Talent.png", true);

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
		JLabel icon = new JLabel("");
		icon.setIcon(new StretchIcon(filename, true));

		progressBar = new JProgressBar();
		progressBar.setString("");
		progressBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		progressBar.setOpaque(true);
		progressBar.setBackground(new Color(47, 79, 79));
		progressBar.setForeground(color);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		setLayout(new MigLayout("", "[50,grow][10,grow][10,grow][50,grow][50,grow][50,grow][50,grow][50,grow][50,grow]",
				"[15,grow][10, grow][15,grow][5,grow][25,grow]"));
		add(icon, "cell 0 0 3 5,grow");

		star = new JLabel("");
		add(star, "cell 3 1 2 2, grow");
		add(progressBar, "cell 3 2 6 2,grow");
	}

	public void setValue(BarDisplay bd) {
		progressBar.setString(bd.getOutputString());
		progressBar.setValue(bd.getOutputVal());
		setStars(bd.getStars());
	}

	protected void setStars(int numStars) {
		switch (numStars) {
		case 1:
			star.setIcon(oneStar);
			break;
		case 2:
			star.setIcon(twoStar);
			break;
		case 3:
			star.setIcon(threeStar);
			break;
		default:
			star.setIcon(null);
			break;
		}
	}

}
