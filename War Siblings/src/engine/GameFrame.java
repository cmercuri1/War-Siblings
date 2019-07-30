package engine;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.SwingConstants.*;
import static javax.swing.LayoutStyle.ComponentPlacement.*;

import character.Character;
import event_classes.EventObject;
import event_classes.Observer;
import global_managers.GlobalManager;

public class GameFrame extends JFrame implements ActionListener, ItemListener, Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2418441605648905558L;
	private String message;
	private Character character;

	private JComboBox<String> box;
	private String[] backgrounds;

	JLabel helmData;
	JLabel bodyData;
	JLabel hpData;
	JLabel apData;
	JLabel fatData;
	JLabel morData;
	JLabel resData;
	JLabel initData;
	JLabel mSkData;
	JLabel rSkData;
	JLabel mDefData;
	JLabel rDefData;
	JLabel damData;
	JLabel armDamData;
	JLabel hsData;
	JLabel visData;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				GameFrame ex = new GameFrame();
				ex.setVisible(true);
			}
		});
	}

	public GameFrame() {
		this.initUI();
	}

	public void initUI() {
		JLabel charSpec = new JLabel("Specify a Background:");
		this.message = "Character";

		backgrounds = GlobalManager.backgrounds.getBgNames();

		box = new JComboBox<>(backgrounds);
		box.addItemListener(this);

		JButton newCharButton = new JButton("New Character");
		newCharButton.addActionListener(this);
		newCharButton.setMnemonic(KeyEvent.VK_C);

		this.setUpData();

		JLabel helmIcon = new JLabel(new ImageIcon("res/images/Helmet_stat_icon.png"));
		JLabel bodyIcon = new JLabel(new ImageIcon("res/images/Armor_stat_icon.png"));
		JLabel hpIcon = new JLabel(new ImageIcon("res/images/Health.png"));
		JLabel apIcon = new JLabel(new ImageIcon("res/images/Action_points.png"));
		JLabel fatIcon = new JLabel(new ImageIcon("res/images/Fatigue.png"));
		JLabel morIcon = new JLabel(new ImageIcon("res/images/Morale_state.png"));
		JLabel resIcon = new JLabel(new ImageIcon("res/images/Resolve.png"));
		JLabel initIcon = new JLabel(new ImageIcon("res/images/Initiative.png"));
		JLabel mSkIcon = new JLabel(new ImageIcon("res/images/Melee_skill.png"));
		JLabel rSkIcon = new JLabel(new ImageIcon("res/images/Ranged_skill.png"));
		JLabel mDefIcon = new JLabel(new ImageIcon("res/images/Melee_defense.png"));
		JLabel rDefIcon = new JLabel(new ImageIcon("res/images/Ranged_defense.png"));
		JLabel damIcon = new JLabel(new ImageIcon("res/images/Regular_damage.png"));
		JLabel armDamIcon = new JLabel(new ImageIcon("res/images/Armor_damage.png"));
		JLabel hsIcon = new JLabel(new ImageIcon("res/images/Chance_to_hit_head.png"));
		JLabel visIcon = new JLabel(new ImageIcon("res/images/Vision.png"));

		createLayout(helmIcon, helmData, bodyIcon, bodyData, hpIcon, hpData, apIcon, apData, fatIcon, fatData, morIcon,
				morData, resIcon, resData, initIcon, initData, mSkIcon, mSkData, rSkIcon, rSkData, mDefIcon, mDefData,
				rDefIcon, rDefData, damIcon, damData, armDamIcon, armDamData, hsIcon, hsData, visIcon, visData,
				charSpec, box, newCharButton);

		setTitle("War Siblings");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setUpData() {
		this.helmData = new JLabel();
		this.bodyData = new JLabel();
		this.hpData = new JLabel();
		this.apData = new JLabel();
		this.fatData = new JLabel();
		this.morData = new JLabel();
		this.resData = new JLabel();
		this.initData = new JLabel();
		this.mSkData = new JLabel();
		this.rSkData = new JLabel();
		this.mDefData = new JLabel();
		this.rDefData = new JLabel();
		this.damData = new JLabel();
		this.armDamData = new JLabel();
		this.hsData = new JLabel();
		this.visData = new JLabel();
	}

	private void createLayout(JComponent... arg) {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup().addComponent(arg[0]).addComponent(arg[2]).addComponent(arg[4])
						.addComponent(arg[6]).addComponent(arg[8]).addComponent(arg[10]).addComponent(arg[12])
						.addComponent(arg[14]))
				.addGroup(gl.createParallelGroup().addComponent(arg[1]).addComponent(arg[3]).addComponent(arg[5])
						.addComponent(arg[7]).addComponent(arg[9]).addComponent(arg[11]).addComponent(arg[13])
						.addComponent(arg[15]))
				.addGroup(gl.createParallelGroup().addComponent(arg[16]).addComponent(arg[18]).addComponent(arg[20])
						.addComponent(arg[22]).addComponent(arg[24]).addComponent(arg[26]).addComponent(arg[28])
						.addComponent(arg[30]))
				.addGroup(gl.createParallelGroup().addComponent(arg[17]).addComponent(arg[19]).addComponent(arg[21])
						.addComponent(arg[23]).addComponent(arg[25]).addComponent(arg[27]).addComponent(arg[29])
						.addComponent(arg[31]))
				.addGroup(gl.createParallelGroup().addComponent(arg[32]).addComponent(arg[33]).addComponent(arg[34])));

		gl.setVerticalGroup(
				gl.createSequentialGroup()
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[0]).addComponent(arg[1])
								.addComponent(arg[16]).addComponent(arg[17]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[2]).addComponent(arg[3])
								.addComponent(arg[18]).addComponent(arg[19]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[4]).addComponent(arg[5])
								.addComponent(arg[20]).addComponent(arg[21]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[6]).addComponent(arg[7])
								.addComponent(arg[22]).addComponent(arg[23]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[8]).addComponent(arg[9])
								.addComponent(arg[24]).addComponent(arg[25]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[10]).addComponent(arg[11])
								.addComponent(arg[26]).addComponent(arg[27]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[12]).addComponent(arg[13])
								.addComponent(arg[28]).addComponent(arg[29]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[14]).addComponent(arg[15])
								.addComponent(arg[30]).addComponent(arg[31]))
						.addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl
								.createSequentialGroup().addComponent(arg[32]).addComponent(arg[33],
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(arg[34])));

		pack();
	}

	private void applyCharacter(Character tba) {
		this.helmData.setText(tba.getIm().getHead().getDurability().toString());
		this.bodyData.setText(tba.getIm().getBody().getDurability().toString());
		this.hpData.setText(tba.getAm().getAttribute("hitpoint").toString());
		this.apData.setText(tba.getAm().getAttribute("actionPoints").toString());
		this.fatData.setText(tba.getAm().getAttribute("fatigue").toString());
		this.morData.setText(tba.getMm().getCurrentState().toString());
		this.resData.setText(tba.getAm().getAttribute("resolve").toString());
		this.initData.setText(tba.getAm().getAttribute("initiative").toString());
		this.mSkData.setText(tba.getAm().getAttribute("meleeSkill").toString());
		this.rSkData.setText(tba.getAm().getAttribute("rangedSkill").toString());
		this.mDefData.setText(tba.getAm().getAttribute("meleeDefense").toString());
		this.rDefData.setText(tba.getAm().getAttribute("rangedDefense").toString());
		this.damData.setText(tba.getIm().getRight().getDamage());
		this.armDamData.setText(tba.getIm().getRight().getArmorDamage());
		this.hsData.setText(tba.getAm().getAttribute("headshot").toString() + "%");
		this.visData.setText(tba.getAm().getAttribute("vision").toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "New " + message + "!", "Information", JOptionPane.INFORMATION_MESSAGE);
		if (message.equals("Character")) {
			this.character = new Character(this);
		} else {
			this.character = new Character(message, this);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (e.getItem().toString().equals("Please select")) {
				this.message = "Character";
			} else {
				this.message = e.getItem().toString();
			}
		}
	}

	@Override
	public void onEventHappening(EventObject event) {
		switch (event.getTarget()) {
		case UI:
			switch (event.getTask()) {
			case FINISHED_GENERATING:
				this.applyCharacter((Character) event.getInformation());
				break;
			default:
				break;
			}
			break;
		case UNDEFINED:
			break;
		default:
			break;

		}

	}
}
