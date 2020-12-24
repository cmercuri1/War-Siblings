/** War Siblings
 * Test
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import storage_classes.BattleConditions;

public class Test extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setTitle("War Siblings");
		setBackground(new Color(153, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[360, grow][1300, grow][260, grow]", "[880, grow][200, grow]"));

		ItemHandler handler = new ItemHandler();
		
		CharacterPanel charPanel = new CharacterPanel(handler);
		contentPane.add(charPanel, "cell 0 0 1 2, grow");

		TestPanel testPanel = new TestPanel();
		contentPane.add(testPanel, "cell 2 1,grow");

		testPanel.addPropertyChangeListener((event) -> {
			if (event.getPropertyName().equals("Background Selected")) {
				charPanel.changeCharacter((String) event.getNewValue());
			} else if (event.getPropertyName().equals("Start Battle")) {
				charPanel.startBattle((BattleConditions) event.getNewValue());
			} else if (event.getPropertyName().equals("End Battle")) {
				charPanel.endBattle((BattleConditions) event.getNewValue());
			}
		});

		MiddlePanel middlePanel = new MiddlePanel(handler);
		contentPane.add(middlePanel, "cell 1 0 2 1, grow");
	}
}
