/** War Siblings
 * Test
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.GridLayout;

public class Test extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=68,114
	 */
	private final ImageIcon imageIcon = new ImageIcon();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		imageIcon.setImage(Toolkit.getDefaultToolkit().getImage(Test.class.getResource("/images/Attributes/Armor_damage.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		PanelAttempt panelAttempt = new PanelAttempt();
		contentPane.add(panelAttempt, BorderLayout.CENTER);
		panelAttempt.setLayout(new GridLayout(1, 0, 0, 0));
	}

}
