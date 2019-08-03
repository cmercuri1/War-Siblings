package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

public class StatisticsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public StatisticsPanel() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setBackground(new Color(47, 79, 79));
		
		JProgressBar helmBar = new JProgressBar();
		helmBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		helmBar.setToolTipText("<html>\r\nWhen the head of a character is hit, the armor points of the worn helmet<br>\r\nare reduced. Depending on how much damage is done, some of the<br>\r\ndamage might go directly through the helmet to the hitpoints of the<br>\r\ncharacter. The head of a character is harder to hit than the body, but more<br>\r\nvulnerable to damage.</html>");
		helmBar.setForeground(new Color(192, 192, 192));
		helmBar.setBackground(Color.DARK_GRAY);
		helmBar.setStringPainted(true);
		
		JProgressBar armorBar = new JProgressBar();
		armorBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		armorBar.setToolTipText("<html>\r\nWhen the body of a character is hit, the armor points of the worn body<br>\r\narmor are reduced. Depending on how much damage is done, some of<br>\r\nthe damage might go directly through the armor to the hitpoints of the<br>\r\ncharacter.</html>");
		armorBar.setForeground(new Color(192, 192, 192));
		armorBar.setBackground(Color.DARK_GRAY);
		armorBar.setStringPainted(true);
		
		JProgressBar hpBar = new JProgressBar();
		hpBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		hpBar.setToolTipText("<html>\r\nHitpoints represent the damage a character can take before dying.<br>\r\nOnce these reach zero, the character is considered dead. The higher<br>\r\nthe maximum hitpoints, the less likely it is a character will suffer<br>\r\ndebilitating injuries when hit.</html>\"");
		hpBar.setForeground(Color.RED);
		hpBar.setBackground(Color.DARK_GRAY);
		hpBar.setStringPainted(true);
		
		JProgressBar apBar = new JProgressBar();
		apBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		apBar.setToolTipText("<html>\r\nAction Points (AP) are spent for every action, like moving or using a<br>\r\nskill. Once all points are spent, the current character's turn ends<br>\r\nautomatically. AP are fully refreshed each turn.</html>");
		apBar.setForeground(Color.GREEN);
		apBar.setBackground(Color.DARK_GRAY);
		apBar.setStringPainted(true);
		
		JProgressBar fatBar = new JProgressBar();
		fatBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		fatBar.setToolTipText("<html>\r\nFatigue is gained for every action, like moving or using skills, and<br>\r\nwhen being hit in combat or when dodging/blocking an attack. It is<br>\r\nreduced at a certain rate each turn. If a character accumulates too<br>\r\nmuch Fatigue they may need to rest a turn (i.e. do nothing) before<br>\r\nbeing able to use more specialized skills again. Fatigue has a synergy<br>\r\nwith Initiative. Initiative is lowered as Fatigue is accumulated.</html>");
		fatBar.setForeground(new Color(0, 255, 255));
		fatBar.setBackground(Color.DARK_GRAY);
		fatBar.setStringPainted(true);
		
		JProgressBar morBar = new JProgressBar();
		morBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		morBar.setToolTipText("<html>\r\nMorale is one of five states and represents the mental condition of<br>\r\ncombatants and their effectiveness in battle. At the lowest state,<br>\r\nfleeing, a character will be outside your control - although they may<br>\r\neventually rally again. Morale changes as the battle unfolds, with<br>\r\ncharacters that have high resolve less likely to fall to low morale<br>\r\nstates. Many of your opponents are affected by morale as well.</html>");
		morBar.setForeground(Color.ORANGE);
		morBar.setBackground(Color.DARK_GRAY);
		morBar.setStringPainted(true);
		
		JProgressBar resBar = new JProgressBar();
		resBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		resBar.setToolTipText("<html>\r\nResolve represents the willpower and bravery of characters. High<br>\r\nResolve makes it less likely that characters fall to lower morale states<br>\r\ndue to negative events and the more likely that characters gain<br>\r\nconfidence from positive events. Resolve acts as defense against<br>\r\ncertain mental attacks that inflict panic, fear or mind control. Resolve<br>\r\nalso affects the ability of a character to rally (leave the Fleeing<br>\r\nmorale state) and the effectiveness of the Rally the Troops Perk.</html>");
		resBar.setForeground(new Color(0, 128, 0));
		resBar.setBackground(Color.DARK_GRAY);
		resBar.setStringPainted(true);
		
		JProgressBar initBar = new JProgressBar();
		initBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		initBar.setToolTipText("<html>\r\nThe higher this value, the earlier the position in the turn order.<br>\r\nInitiative is reduced by the current fatigue as well as any penalty to<br>\r\nmaximum fatigue (such as from heavy armor). In general, someone<br>\r\nin light armor will act before someone in heavy armor, and someone<br>\r\nfresh will act before someone fatigued.</html>");
		initBar.setForeground(new Color(0, 128, 0));
		initBar.setBackground(Color.DARK_GRAY);
		initBar.setStringPainted(true);
		
		JProgressBar rSkillBar = new JProgressBar();
		rSkillBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rSkillBar.setToolTipText("<html>\r\nDetermines the base probability of hitting a target with a ranged<br>\r\nattack, such as with bows and crossbows. Can be increased as the<br>\r\ncharacter gains experience.</html>");
		rSkillBar.setForeground(new Color(0, 128, 0));
		rSkillBar.setBackground(Color.DARK_GRAY);
		rSkillBar.setStringPainted(true);
		
		JProgressBar mDefBar = new JProgressBar();
		mDefBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mDefBar.setToolTipText("<html>\r\nA higher melee defense reduces the probability of being hit with a<br>\r\nmassive attack, such as the thrust of a spear. It can be increased as<br>\r\nthe character gains experience and by equipping a good shield.</html>");
		mDefBar.setForeground(new Color(0, 128, 0));
		mDefBar.setBackground(Color.DARK_GRAY);
		mDefBar.setStringPainted(true);
		
		JProgressBar rDefBar = new JProgressBar();
		rDefBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rDefBar.setToolTipText("<html>\r\nA higher ranged defense reduces the probability of being hit with a<br>\r\nranged attack, such as an arrow shot from afar. It can be increased<br>\r\nas the character gains experience and by equipping a good shield.</html>");
		rDefBar.setForeground(new Color(0, 128, 0));
		rDefBar.setBackground(Color.DARK_GRAY);
		rDefBar.setStringPainted(true);
		
		JProgressBar damBar = new JProgressBar();
		damBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		damBar.setToolTipText("<html>\r\nThe base damage the currently equipped weapon does. Will be<br>\r\napplied in full against hitpoints if no armor is protecting the target. If<br>\r\nthe target is protected by armor, the damage is applied to armor<br>\r\ninstead based on the weapon's effectiveness against armor. Depending<br>\r\non how much damage is done, some of the damage might go directly<br>\r\nthrough the worn armor to the hitpoints. The actual damage done is<br>\r\nmodified by the skill used and the target hit.</html>");
		damBar.setForeground(new Color(0, 128, 0));
		damBar.setBackground(Color.DARK_GRAY);
		damBar.setStringPainted(true);
		
		JProgressBar aDamBar = new JProgressBar();
		aDamBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		aDamBar.setToolTipText("<html>\r\nThe base percentage of damage that will be applied when hitting a<br>\r\ntarget protected by armor. Once the armor is destroyed, the weapon<br>\r\ndamage applies at 100% to hitpoints. The actual damage done is<br>\r\nmodified by the skill used and the target hit.</html>");
		aDamBar.setForeground(new Color(0, 128, 0));
		aDamBar.setBackground(Color.DARK_GRAY);
		aDamBar.setStringPainted(true);
		
		JProgressBar headShotBar = new JProgressBar();
		headShotBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		headShotBar.setToolTipText("<html>\r\nThe base percentage chance to hit a target's head for increased<br>\r\ndamage. The final chance can be modified by the skill or weapon<br>\r\nused.</html>");
		headShotBar.setForeground(new Color(0, 128, 0));
		headShotBar.setBackground(Color.DARK_GRAY);
		headShotBar.setStringPainted(true);
		
		JProgressBar visBar = new JProgressBar();
		visBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		visBar.setToolTipText("<html>\r\nVision, or view range, determines how far a character can see to<br>\r\nuncover the fog of war, discover threats and hit with ranged attacks.<br>\r\nHeavier helmets can reduce vision. Certain Traits or Injuries can also<br>\r\naffect Vision.</html>");
		visBar.setForeground(new Color(0, 128, 0));
		visBar.setBackground(Color.DARK_GRAY);
		visBar.setStringPainted(true);
		
		JProgressBar mSkillBar = new JProgressBar();
		mSkillBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mSkillBar.setToolTipText("<html>\r\nDetermines the base probability of hitting a target with a melee<br>\r\nattack, such as with swords and spears. Can be increased as the<br>\r\ncharacter gains experience.</html>");
		mSkillBar.setForeground(new Color(0, 128, 0));
		mSkillBar.setBackground(Color.DARK_GRAY);
		mSkillBar.setStringPainted(true);
		
		JLabel helmIcon = new JLabel("");
		helmIcon.setToolTipText("<html>\r\nWhen the head of a character is hit, the armor points of the worn helmet<br>\r\nare reduced. Depending on how much damage is done, some of the<br>\r\ndamage might go directly through the helmet to the hitpoints of the<br>\r\ncharacter. The head of a character is harder to hit than the body, but more<br>\r\nvulnerable to damage.</html>");
		helmIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Helmet_stat_icon.png")));
		helmIcon.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel armorIcon = new JLabel("");
		armorIcon.setToolTipText("<html>\r\nWhen the body of a character is hit, the armor points of the worn body<br>\r\narmor are reduced. Depending on how much damage is done, some of<br>\r\nthe damage might go directly through the armor to the hitpoints of the<br>\r\ncharacter.</html>");
		armorIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Armor_stat_icon.png")));
		armorIcon.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel hpIcon = new JLabel("");
		hpIcon.setToolTipText("<html>\r\nHitpoints represent the damage a character can take before dying.<br>\r\nOnce these reach zero, the character is considered dead. The higher<br>\r\nthe maximum hitpoints, the less likely it is a character will suffer<br>\r\ndebilitating injuries when hit.</html>\"");
		hpIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Health.png")));
		
		JLabel apIcon = new JLabel("");
		apIcon.setToolTipText("<html>\r\nAction Points (AP) are spent for every action, like moving or using a<br>\r\nskill. Once all points are spent, the current character's turn ends<br>\r\nautomatically. AP are fully refreshed each turn.</html>");
		apIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Action_points.png")));
		
		JLabel fatIcon = new JLabel("");
		fatIcon.setToolTipText("<html>\r\nFatigue is gained for every action, like moving or using skills, and<br>\r\nwhen being hit in combat or when dodging/blocking an attack. It is<br>\r\nreduced at a certain rate each turn. If a character accumulates too<br>\r\nmuch Fatigue they may need to rest a turn (i.e. do nothing) before<br>\r\nbeing able to use more specialized skills again. Fatigue has a synergy<br>\r\nwith Initiative. Initiative is lowered as Fatigue is accumulated.</html>");
		fatIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Fatigue.png")));
		
		JLabel morIcon = new JLabel("");
		morIcon.setToolTipText("<html>\r\nMorale is one of five states and represents the mental condition of<br>\r\ncombatants and their effectiveness in battle. At the lowest state,<br>\r\nfleeing, a character will be outside your control - although they may<br>\r\neventually rally again. Morale changes as the battle unfolds, with<br>\r\ncharacters that have high resolve less likely to fall to low morale<br>\r\nstates. Many of your opponents are affected by morale as well.</html>");
		morIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Morale_state.png")));
		
		JLabel resIcon = new JLabel("");
		resIcon.setToolTipText("<html>\r\nResolve represents the willpower and bravery of characters. High<br>\r\nResolve makes it less likely that characters fall to lower morale states<br>\r\ndue to negative events and the more likely that characters gain<br>\r\nconfidence from positive events. Resolve acts as defense against<br>\r\ncertain mental attacks that inflict panic, fear or mind control. Resolve<br>\r\nalso affects the ability of a character to rally (leave the Fleeing<br>\r\nmorale state) and the effectiveness of the Rally the Troops Perk.</html>");
		resIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Resolve.png")));
		
		JLabel initIcon = new JLabel("");
		initIcon.setToolTipText("<html>\r\nThe higher this value, the earlier the position in the turn order.<br>\r\nInitiative is reduced by the current fatigue as well as any penalty to<br>\r\nmaximum fatigue (such as from heavy armor). In general, someone<br>\r\nin light armor will act before someone in heavy armor, and someone<br>\r\nfresh will act before someone fatigued.</html>");
		initIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Initiative.png")));
		
		JLabel mSkIcon = new JLabel("");
		mSkIcon.setToolTipText("<html>\r\nDetermines the base probability of hitting a target with a melee<br>\r\nattack, such as with swords and spears. Can be increased as the<br>\r\ncharacter gains experience.</html>");
		mSkIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Melee_skill.png")));
		
		JLabel rSkIcon = new JLabel("");
		rSkIcon.setToolTipText("<html>\r\nDetermines the base probability of hitting a target with a ranged<br>\r\nattack, such as with bows and crossbows. Can be increased as the<br>\r\ncharacter gains experience.</html>");
		rSkIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Ranged_skill.png")));
		
		JLabel mDefIcon = new JLabel("");
		mDefIcon.setToolTipText("<html>\r\nA higher melee defense reduces the probability of being hit with a<br>\r\nmassive attack, such as the thrust of a spear. It can be increased as<br>\r\nthe character gains experience and by equipping a good shield.</html>");
		mDefIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Melee_defense.png")));
		
		JLabel rDefIcon = new JLabel("");
		rDefIcon.setToolTipText("<html>\r\nA higher ranged defense reduces the probability of being hit with a<br>\r\nranged attack, such as an arrow shot from afar. It can be increased<br>\r\nas the character gains experience and by equipping a good shield.</html>");
		rDefIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Ranged_defense.png")));
		
		JLabel damIcon = new JLabel("");
		damIcon.setToolTipText("<html>\r\nThe base damage the currently equipped weapon does. Will be<br>\r\napplied in full against hitpoints if no armor is protecting the target. If<br>\r\nthe target is protected by armor, the damage is applied to armor<br>\r\ninstead based on the weapon's effectiveness against armor. Depending<br>\r\non how much damage is done, some of the damage might go directly<br>\r\nthrough the worn armor to the hitpoints. The actual damage done is<br>\r\nmodified by the skill used and the target hit.</html>");
		damIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Regular_damage.png")));
		
		JLabel aDamIcon = new JLabel("");
		aDamIcon.setToolTipText("<html>\r\nThe base percentage of damage that will be applied when hitting a<br>\r\ntarget protected by armor. Once the armor is destroyed, the weapon<br>\r\ndamage applies at 100% to hitpoints. The actual damage done is<br>\r\nmodified by the skill used and the target hit.</html>");
		aDamIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Armor_damage.png")));
		
		JLabel headShotIcon = new JLabel("");
		headShotIcon.setToolTipText("<html>\r\nThe base percentage chance to hit a target's head for increased<br>\r\ndamage. The final chance can be modified by the skill or weapon<br>\r\nused.</html>");
		headShotIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Chance_to_hit_head.png")));
		
		JLabel visIcon = new JLabel("");
		visIcon.setToolTipText("<html>\r\nVision, or view range, determines how far a character can see to<br>\r\nuncover the fog of war, discover threats and hit with ranged attacks.<br>\r\nHeavier helmets can reduce vision. Certain Traits or Injuries can also<br>\r\naffect Vision.</html>");
		visIcon.setIcon(new ImageIcon(StatisticsPanel.class.getResource("/images/Attributes/Vision.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(helmIcon)
						.addComponent(armorIcon)
						.addComponent(hpIcon)
						.addComponent(apIcon)
						.addComponent(fatIcon)
						.addComponent(morIcon)
						.addComponent(resIcon)
						.addComponent(initIcon))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(helmBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addComponent(armorBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addComponent(hpBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addComponent(apBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addComponent(fatBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addComponent(morBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addComponent(resBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addComponent(initBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rDefIcon)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(rDefBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(mSkIcon)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(mSkillBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rSkIcon)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(rSkillBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(mDefIcon)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(mDefBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(damIcon)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(damBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(aDamIcon)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(aDamBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(headShotIcon)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(headShotBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(visIcon)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(visBar, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(helmIcon)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(mSkillBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(mSkIcon))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rSkillBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(rSkIcon))
							.addGap(13)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(mDefBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(mDefIcon))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rDefBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(rDefIcon))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(damBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(damIcon))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(aDamBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(aDamIcon))
							.addGap(13)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(headShotBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(headShotIcon))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(visIcon)
								.addComponent(visBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(helmBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(armorBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(armorIcon))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(hpBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(hpIcon))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(apBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(apIcon))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(fatIcon)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(fatBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(morIcon)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(morBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(resIcon)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(resBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(initIcon)
														.addComponent(initBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))))))))))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
