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
import javax.swing.SwingConstants;

import static javax.swing.GroupLayout.Alignment.*;

import character.Character;
import event_classes.CharacterEvent;
import global_managers.GlobalManager;
import listener_interfaces.CharacterListener;
import notifier_interfaces.CharacterNotifier;
import storage_classes.ArrayList;

public class GameFrame extends JFrame implements ActionListener, ItemListener, CharacterListener, CharacterNotifier {
	/**
	 * 
	 */
	protected static final long serialVersionUID = -2418441605648905558L;
	protected String message;
	protected Character character;
	protected ArrayList<CharacterListener> characterListeners;

	protected JComboBox<String> box;

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
	JLabel trait3;

	JLabel headArmor;
	JLabel bodyArmor;
	JLabel rightItem;
	JLabel leftItem;

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
		this.message = "Random";

		box = new JComboBox<>(GlobalManager.backgrounds.getBgNames());
		box.addItemListener(this);

		JButton newCharButton = new JButton("New Character");
		newCharButton.addActionListener(this);
		newCharButton.setMnemonic(KeyEvent.VK_C);

		this.character = new Character();
		this.characterListeners = new ArrayList<CharacterListener>();
		this.characterListeners.add(character);
		this.character.addCharacterListener(this);

		this.setUpData();

		createLayout(charSpec, box, newCharButton);

		setTitle("War Siblings");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected void createLayout(JComponent... arg) {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup().addGroup(gl.createParallelGroup()
				.addGroup(gl.createSequentialGroup().addComponent(bgIcon).addComponent(trait1).addComponent(trait2)
						.addComponent(trait3))
				.addGroup(gl.createSequentialGroup()
						.addGroup(gl.createParallelGroup().addComponent(helm).addComponent(body).addComponent(ap)
								.addComponent(fat).addComponent(mor).addComponent(res).addComponent(init))
						.addGroup(gl.createParallelGroup().addComponent(mSk).addComponent(rSk).addComponent(mDef)
								.addComponent(rDef).addComponent(dam).addComponent(hs).addComponent(vis))))
				.addGroup(gl.createParallelGroup()
						.addGroup(gl.createSequentialGroup().addComponent(rightItem)
								.addGroup(gl.createParallelGroup().addComponent(headArmor).addComponent(bodyArmor))
								.addComponent(leftItem))
						.addGroup(gl.createParallelGroup().addComponent(arg[0]).addComponent(arg[1],
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(arg[2]))));

		gl.setVerticalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup()
						.addGroup(gl.createSequentialGroup()
								.addGroup(gl.createParallelGroup().addComponent(bgIcon).addComponent(trait1)
										.addComponent(trait2).addComponent(trait3))
								.addGroup(gl.createParallelGroup(BASELINE).addComponent(helm).addComponent(mSk))
								.addGroup(gl.createParallelGroup(BASELINE).addComponent(body).addComponent(rSk))
								.addGroup(gl.createParallelGroup(BASELINE).addComponent(ap).addComponent(mDef))
								.addGroup(gl.createParallelGroup(BASELINE).addComponent(fat).addComponent(rDef))
								.addGroup(gl.createParallelGroup(BASELINE).addComponent(mor).addComponent(dam))
								.addGroup(gl.createParallelGroup(BASELINE).addComponent(res).addComponent(hs))
								.addGroup(gl.createParallelGroup(BASELINE).addComponent(init).addComponent(vis)))
						.addGroup(gl.createSequentialGroup().addComponent(headArmor)
								.addGroup(gl.createParallelGroup(BASELINE).addComponent(rightItem)
										.addComponent(bodyArmor).addComponent(leftItem))))
				.addGroup(
						gl.createSequentialGroup().addComponent(arg[0]).addComponent(arg[1], GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(arg[2])));

		pack();
	}

	protected void setUpData() {
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
		this.trait3 = new JLabel();

		this.headArmor = new JLabel();
		this.bodyArmor = new JLabel();
		this.rightItem = new JLabel();
		this.leftItem = new JLabel();
	}

	protected void applyCharacter(Character tba) {
		this.helm.setText(tba.getIm().getHead().getDurability().toString());
		this.body.setText(tba.getIm().getBody().getDurability().toString());
		this.hp.setText(tba.getAm().getAttributes()[0].toString());
		this.ap.setText(tba.getAm().getAttributes()[1].toString());
		this.fat.setText(tba.getAm().getAttributes()[2].toString());
		this.mor.setText(tba.getAm().getCurrentState().toString());
		this.res.setText(tba.getAm().getAttributes()[3].toString());
		this.init.setText(tba.getAm().getAttributes()[4].toString());
		this.mSk.setText(tba.getAm().getAttributes()[5].toString());
		this.rSk.setText(tba.getAm().getAttributes()[6].toString());
		this.mDef.setText(tba.getAm().getAttributes()[7].toString());
		this.rDef.setText(tba.getAm().getAttributes()[8].toString());
		this.dam.setText(tba.getIm().getRight().getDamage());
		this.armDam.setText(tba.getIm().getRight().getArmorDamage());
		this.hs.setText(tba.getAm().getAttributes()[9].toString() + "%");
		this.vis.setText(tba.getAm().getAttributes()[10].toString());

		this.bgIcon.setIcon(tba.getBgIcon());

		try {
			this.trait1.setIcon(tba.getAbm().getTraits().get(0).getImage());
			this.trait1.setToolTipText(tba.getAbm().getTraits().get(0).toString());
		} catch (IndexOutOfBoundsException e) {
			this.trait1.setIcon(null);
		}
		try {
			this.trait2.setIcon(tba.getAbm().getTraits().get(1).getImage());
			this.trait2.setToolTipText(tba.getAbm().getTraits().get(1).toString());
		} catch (IndexOutOfBoundsException e) {
			this.trait2.setIcon(null);
		}
		try {
			this.trait3.setIcon(tba.getAbm().getTraits().get(2).getImage());
			this.trait3.setToolTipText(tba.getAbm().getTraits().get(2).toString());
		} catch (IndexOutOfBoundsException e) {
			this.trait3.setIcon(null);
		}

		this.headArmor.setIcon(tba.getIm().getHead().getImage());
		this.headArmor.setToolTipText(tba.getIm().getHead().toString());

		this.bodyArmor.setIcon(tba.getIm().getBody().getImage());
		this.bodyArmor.setToolTipText(tba.getIm().getBody().toString());

		this.rightItem.setIcon(tba.getIm().getRight().getImage());
		this.rightItem.setToolTipText(tba.getIm().getRight().toString());

		this.leftItem.setIcon(tba.getIm().getLeft().getImage());
		this.leftItem.setToolTipText(tba.getIm().getLeft().toString());

		//tba.display();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.notifyCharacterListeners(new CharacterEvent(CharacterEvent.Task.CHANGED_CHARACTER, message, this));
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			this.message = e.getItem().toString();
		}
	}

	@Override
	public void addCharacterListener(CharacterListener c) {
		this.characterListeners.add(c);
	}

	@Override
	public void removeCharacterListener(CharacterListener c) {
		this.characterListeners.remove(c);
	}

	@Override
	public void notifyCharacterListeners(CharacterEvent c) {
		this.characterListeners.forEach(l -> l.onCharacterEvent(c));
	}

	@Override
	public void notifyCharacterListener(CharacterListener c, CharacterEvent e) {
		this.characterListeners.get(c).onCharacterEvent(e);
	}

	@Override
	public void onCharacterEvent(CharacterEvent c) {
		switch (c.getTask()) {
		case CHANGED_CHARACTER:
			break;
		case FINISHED_CHARACTER:
			this.applyCharacter((Character) c.getInformation());
			break;
		}
	}
}
