/** War Siblings
 * GameFrame class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
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
import javax.swing.SwingConstants;

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

	JLabel helm;
	JLabel body;
	JLabel hp;
	JLabel ap;
	JLabel fat;
	JLabel mor;
	JLabel res;
	JLabel init;
	JLabel mSk;
	JLabel rSk;
	JLabel mDef;
	JLabel rDef;
	JLabel dam;
	JLabel armDam;
	JLabel hs;
	JLabel vis;

	JLabel bgIcon;
	JLabel trait1;
	JLabel trait2;

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

		createLayout(helm, body, hp, ap, fat, mor, res, init, mSk, rSk, mDef, rDef, dam, armDam, hs, vis, charSpec, box,
				newCharButton, bgIcon, trait1, trait2);

		setTitle("War Siblings");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setUpData() {
		this.helm = new JLabel("0/0", new ImageIcon("res/images/Attributes/Helmet_stat_icon.png"),
				SwingConstants.TRAILING);
		this.helm.setToolTipText("<html>When the head of a character is hit, the armor points of the worn helmet<br>"
				+ "are reduced. Depending on how much damage is done, some of the<br>"
				+ "damage might go directly through the helmet to the hitpoints of the<br>"
				+ "character. The head of a character is harder to hit than the body, but more<br>"
				+ "vulnerable to damage.</html>");

		this.body = new JLabel("0/0", new ImageIcon("res/images/Attributes/Armor_stat_icon.png"),
				SwingConstants.TRAILING);
		this.body.setToolTipText("<html>When the body of a character is hit, the armor points of the worn body<br>"
				+ "armor are reduced. Depending on how much damage is done, some of<br>"
				+ "the damage might go directly through the armor to the hitpoints of the<br>" + "character.</html>");

		this.hp = new JLabel("0/0", new ImageIcon("res/images/Attributes/Health.png"), SwingConstants.TRAILING);
		this.hp.setToolTipText("<html>Hitpoints represent the damage a character can take before dying.<br>"
				+ "Once these reach zero, the character is considered dead. The higher<br>"
				+ "the maximum hitpoints, the less likely it is a character will suffer<br>"
				+ "debilitating injuries when hit.</html>");

		this.ap = new JLabel("0/9", new ImageIcon("res/images/Attributes/Action_points.png"), SwingConstants.TRAILING);
		this.ap.setToolTipText("<html>Action Points (AP) are spent for every action, like moving or using a<br>"
				+ "skill. Once all points are spent, the current character's turn ends<br>"
				+ "automatically. AP are fully refreshed each turn.</html>");

		this.fat = new JLabel("0/0", new ImageIcon("res/images/Attributes/Fatigue.png"), SwingConstants.TRAILING);
		this.fat.setToolTipText("<html>Fatigue is gained for every action, like moving or using skills, and<br>"
				+ "when being hit in combat or when dodging/blocking an attack. It is<br>"
				+ "reduced at a certain rate each turn. If a character accumulates too<br>"
				+ "much Fatigue they may need to rest a turn (i.e. do nothing) before<br>"
				+ "being able to use more specialized skills again. Fatigue has a synergy<br>"
				+ "with Initiative. Initiative is lowered as Fatigue is accumulated.</html>");

		this.mor = new JLabel("Steady", new ImageIcon("res/images/Attributes/Morale_state.png"),
				SwingConstants.TRAILING);
		this.mor.setToolTipText("<html>Morale is one of five states and represents the mental condition of<br>"
				+ "combatants and their effectiveness in battle. At the lowest state,<br>"
				+ "fleeing, a character will be outside your control - although they may<br>"
				+ "eventually rally again. Morale changes as the battle unfolds, with<br>"
				+ "characters that have high resolve less likely to fall to low morale<br>"
				+ "states. Many of your opponents are affected by morale as well.</html>");

		this.res = new JLabel("0", new ImageIcon("res/images/Attributes/Resolve.png"), SwingConstants.TRAILING);
		this.res.setToolTipText("<html>Resolve represents the willpower and bravery of characters. High<br>"
				+ "Resolve makes it less likely that characters fall to lower morale states<br>"
				+ "due to negative events and the more likely that characters gain<br>"
				+ "confidence from positive events. Resolve acts as defense against<br>"
				+ "certain mental attacks that inflict panic, fear or mind control. Resolve<br>"
				+ "also affects the ability of a character to rally (leave the Fleeing<br>"
				+ "morale state) and the effectiveness of the Rally the Troops Perk.</html>");

		this.init = new JLabel("0", new ImageIcon("res/images/Attributes/Initiative.png"), SwingConstants.TRAILING);
		this.init.setToolTipText("<html>The higher this value, the earlier the position in the turn order.<br>"
				+ "Initiative is reduced by the current fatigue as well as any penalty to<br>"
				+ "maximum fatigue (such as from heavy armor). In general, someone<br>"
				+ "in light armor will act before someone in heavy armor, and someone<br>"
				+ "fresh will act before someone fatigued.</html>");

		this.mSk = new JLabel("0", new ImageIcon("res/images/Attributes/Melee_skill.png"), SwingConstants.TRAILING);
		this.mSk.setToolTipText("<html>Determines the base probability of hitting a target with a melee<br>"
				+ "attack, such as with swords and spears. Can be increased as the<br>"
				+ "character gains experience.</html>");

		this.rSk = new JLabel("0", new ImageIcon("res/images/Attributes/Ranged_skill.png"), SwingConstants.TRAILING);
		this.rSk.setToolTipText("<html>Determines the base probability of hitting a target with a ranged<br>"
				+ "attack, such as with bows and crossbows. Can be increased as the<br>"
				+ "character gains experience.</html>");

		this.mDef = new JLabel("0", new ImageIcon("res/images/Attributes/Melee_defense.png"), SwingConstants.TRAILING);
		this.mDef.setToolTipText("<html>A higher melee defense reduces the probability of being hit with a<br>"
				+ "massive attack, such as the thrust of a spear. It can be increased as<br>"
				+ "the character gains experience and by equipping a good shield.</html>");

		this.rDef = new JLabel("0", new ImageIcon("res/images/Attributes/Ranged_skill.png"), SwingConstants.TRAILING);
		this.rDef.setToolTipText("<html>A higher ranged defense reduces the probability of being hit with a<br>"
				+ "ranged attack, such as an arrow shot from afar. It can be increased<br>"
				+ "as the character gains experience and by equipping a good shield.</html>");

		this.dam = new JLabel("0-0", new ImageIcon("res/images/Attributes/Regular_damage.png"),
				SwingConstants.TRAILING);
		this.dam.setToolTipText("<html>The base damage the currently equipped weapon does. Will be<br>"
				+ "applied in full against hitpoints if no armor is protecting the target. If<br>"
				+ "the target is protected by armor, the damage is applied to armor<br>"
				+ "instead based on the weapon's effectiveness against armor. Depending<br>"
				+ "on how much damage is done, some of the damage might go directly<br>"
				+ "through the worn armor to the hitpoints. The actual damage done is<br>"
				+ "modified by the skill used and the target hit.</html>");

		this.armDam = new JLabel("0%", new ImageIcon("res/images/Attributes/Armor_damage.png"),
				SwingConstants.TRAILING);
		this.armDam.setToolTipText("<html>The base percentage of damage that will be applied when hitting a<br>"
				+ "target protected by armor. Once the armor is destroyed, the weapon<br>"
				+ "damage applies at 100% to hitpoints. The actual damage done is<br>"
				+ "modified by the skill used and the target hit.</html>");

		this.hs = new JLabel("0%", new ImageIcon("res/images/Attributes/Chance_to_hit_head.png"),
				SwingConstants.TRAILING);
		this.hs.setToolTipText("<html>The base percentage chance to hit a target's head for increased<br>"
				+ "damage. The final chance can be modified by the skill or weapon used.</html>");

		this.vis = new JLabel("0", new ImageIcon("res/images/Attributes/Vision.png"), SwingConstants.TRAILING);
		this.vis.setToolTipText("<html>Vision, or view range, determines how far a character can see to<br>"
				+ "uncover the fog of war, discover threats and hit with ranged attacks.<br>"
				+ "Heavier helmets can reduce vision. Certain Traits or Injuries can also<br>"
				+ "affect Vision.</html>");

		this.bgIcon = new JLabel();
		this.trait1 = new JLabel();
		this.trait2 = new JLabel();
	}

	private void createLayout(JComponent... arg) {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup()
				.addGroup(gl.createSequentialGroup().addComponent(arg[19]).addComponent(arg[20]).addComponent(arg[21]))
				.addGroup(gl.createParallelGroup().addComponent(arg[0]).addComponent(arg[1]).addComponent(arg[2])
						.addComponent(arg[3]).addComponent(arg[4]).addComponent(arg[5]).addComponent(arg[6])
						.addComponent(arg[7]))
				.addGroup(gl.createParallelGroup().addComponent(arg[8]).addComponent(arg[9]).addComponent(arg[10])
						.addComponent(arg[11]).addComponent(arg[12]).addComponent(arg[13]).addComponent(arg[14])
						.addComponent(arg[15]))
				.addGroup(gl.createParallelGroup().addComponent(arg[16]).addComponent(arg[17]).addComponent(arg[18])));

		gl.setVerticalGroup(
				gl.createSequentialGroup()
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[19]).addComponent(arg[20])
								.addComponent(arg[21]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[0]).addComponent(arg[8]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[1]).addComponent(arg[9]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[2]).addComponent(arg[10]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[3]).addComponent(arg[11]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[4]).addComponent(arg[12]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[5]).addComponent(arg[13]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[6]).addComponent(arg[14]))
						.addGroup(gl.createParallelGroup(BASELINE).addComponent(arg[7]).addComponent(arg[15]))
						.addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl
								.createSequentialGroup().addComponent(arg[16]).addComponent(arg[17],
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(arg[18])));

		pack();
	}

	private void applyCharacter(Character tba) {
		this.helm.setText(tba.getIm().getHead().getDurability().toString());
		this.body.setText(tba.getIm().getBody().getDurability().toString());
		this.hp.setText(tba.getAm().getAttribute("hitpoint").toString());
		this.ap.setText(tba.getAm().getAttribute("actionPoints").toString());
		this.fat.setText(tba.getAm().getAttribute("fatigue").toString());
		this.mor.setText(tba.getMm().getCurrentState().toString());
		this.res.setText(tba.getAm().getAttribute("resolve").toString());
		this.init.setText(tba.getAm().getAttribute("initiative").toString());
		this.mSk.setText(tba.getAm().getAttribute("meleeSkill").toString());
		this.rSk.setText(tba.getAm().getAttribute("rangedSkill").toString());
		this.mDef.setText(tba.getAm().getAttribute("meleeDefense").toString());
		this.rDef.setText(tba.getAm().getAttribute("rangedDefense").toString());
		this.dam.setText(tba.getIm().getRight().getDamage());
		this.armDam.setText(tba.getIm().getRight().getArmorDamage());
		this.hs.setText(tba.getAm().getAttribute("headshot").toString() + "%");
		this.vis.setText(tba.getAm().getAttribute("vision").toString());

		if (tba.getAbm().getAbility("Background Ability") != null) {
			this.bgIcon.setIcon(tba.getAbm().getAbilities().get(0).getImage());
		} else {
			this.bgIcon.setIcon(null);
		}

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
