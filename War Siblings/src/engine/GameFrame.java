package engine;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import static javax.swing.GroupLayout.Alignment.BASELINE;

import character.Character;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private Character character;

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
		this.character = new Character();
		this.initUI();
	}

	public void initUI() {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);

		AttributePanel headArmorPanel = new AttributePanel("res/images/Helmet_stat_icon.png");
		AttributePanel bodyArmorPanel = new AttributePanel("res/images/Armor_stat_icon.png");
		AttributePanel hitpointPanel = new AttributePanel("res/images/Health.png");
		AttributePanel actionPointPanel = new AttributePanel("res/images/Action_points.png");
		AttributePanel fatiguePanel = new AttributePanel("res/images/Fatigue.png");
		AttributePanel moralePanel = new AttributePanel("res/images/Morale_state.png");
		AttributePanel resolvePanel = new AttributePanel("res/images/Resolve.png");
		AttributePanel initiativePanel = new AttributePanel("res/images/Initiative.png");

		AttributePanel meleeSkillPanel = new AttributePanel("res/images/Melee_skill.png");
		AttributePanel rangedSkillPanel = new AttributePanel("res/images/Ranged_skill.png");
		AttributePanel meleeDefensePanel = new AttributePanel("res/images/Melee_defense.png");
		AttributePanel rangedDefensePanel = new AttributePanel("res/images/Ranged_defense.png");
		AttributePanel damageHPPanel = new AttributePanel("res/images/Regular_damage.png");
		AttributePanel damageArmorPanel = new AttributePanel("res/images/Armor_damage.png");
		AttributePanel headshotPanel = new AttributePanel("res/images/Chance_to_hit_head.png");
		AttributePanel visionPanel = new AttributePanel("res/images/Vision.png");

		gl.setHorizontalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup().addComponent(headArmorPanel).addComponent(bodyArmorPanel)
						.addComponent(hitpointPanel).addComponent(actionPointPanel).addComponent(fatiguePanel)
						.addComponent(moralePanel).addComponent(resolvePanel).addComponent(initiativePanel))
				.addGroup(gl.createParallelGroup().addComponent(meleeSkillPanel).addComponent(rangedSkillPanel)
						.addComponent(meleeDefensePanel).addComponent(rangedDefensePanel).addComponent(damageHPPanel)
						.addComponent(damageArmorPanel).addComponent(headshotPanel).addComponent(visionPanel)));

		gl.setVerticalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup(BASELINE).addComponent(headArmorPanel).addComponent(meleeSkillPanel))
				.addGroup(gl.createParallelGroup(BASELINE).addComponent(bodyArmorPanel).addComponent(rangedSkillPanel))
				.addGroup(gl.createParallelGroup(BASELINE).addComponent(hitpointPanel).addComponent(meleeDefensePanel))
				.addGroup(gl.createParallelGroup(BASELINE).addComponent(actionPointPanel)
						.addComponent(rangedDefensePanel))
				.addGroup(gl.createParallelGroup(BASELINE).addComponent(fatiguePanel).addComponent(damageHPPanel))
				.addGroup(gl.createParallelGroup(BASELINE).addComponent(moralePanel).addComponent(damageArmorPanel))
				.addGroup(gl.createParallelGroup(BASELINE).addComponent(resolvePanel).addComponent(headshotPanel))
				.addGroup(gl.createParallelGroup(BASELINE).addComponent(initiativePanel).addComponent(visionPanel)));

		pack();

		add(new Surface());

		setTitle("War Siblings");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
