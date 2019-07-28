package engine;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Surface extends JPanel {
	private Image oneStar;
	private Image twoStar;
	private Image thrStar;
	private Image ap;
	private Image armDam;
	private Image arm;
	private Image crowns;
	private Image dam;
	private Image damIgn;
	private Image dur;
	private Image fatRec;
	private Image fat;
	private Image head;
	private Image hp;
	private Image helm;
	private Image init;
	private Image lvl;
	private Image mDef;
	private Image mor;
	private Image mSk;
	private Image rDef;
	private Image rSk;
	private Image res;
	private Image shiDam;
	private Image vis;

	private Image xP;

	public Surface() {
		this.loadImages();
		this.setSurfaceSize();
	}

	private void setSurfaceSize() {
		Dimension d = new Dimension();
		d.width = 500;
		d.height = 300;
		setPreferredSize(d);
	}

	private void loadImages() {
		this.oneStar = new ImageIcon("res/images/1_Star_Talent.png").getImage();
		this.twoStar = new ImageIcon("res/images/2_Star_Talent.png").getImage();
		this.thrStar = new ImageIcon("res/images/3_Star_Talent.png").getImage();
		this.ap = new ImageIcon("res/images/Action_points.png").getImage();
		this.armDam = new ImageIcon("res/images/Armor_damage.png").getImage();
		this.arm = new ImageIcon("res/images/Armor_stat_icon.png").getImage();
		this.crowns = new ImageIcon("res/images/Crowns.png").getImage();
		this.dam = new ImageIcon("res/images/Regular_damage.png").getImage();
		this.damIgn = new ImageIcon("res/images/Direct_damage").getImage();
		this.dur = new ImageIcon("res/images/Supplies_icon.png").getImage();
		this.fatRec = new ImageIcon("res/images/Fatigue_recovery.png").getImage();
		this.fat = new ImageIcon("res/images/Fatigue.png").getImage();
		this.head = new ImageIcon("res/images/Chance_to_hit_head.png").getImage();
		this.hp = new ImageIcon("res/images/Health.png").getImage();
		this.helm = new ImageIcon("res/images/Helmet_stat_icon.png").getImage();
		this.init = new ImageIcon("res/images/Initiative.png").getImage();
		this.lvl = new ImageIcon("res/images/Level.png").getImage();
		this.mDef = new ImageIcon("res/images/Melee_defense.png").getImage();
		this.mor = new ImageIcon("res/images/Morale_state.png").getImage();
		this.mSk = new ImageIcon("res/images/Melee_skill.png").getImage();
		this.rDef = new ImageIcon("res/images/Ranged_defense.png").getImage();
		this.rSk = new ImageIcon("res/images/Ranged_skill.png").getImage();
		this.res = new ImageIcon("res/images/Resolve.png").getImage();
		this.shiDam = new ImageIcon("res/images/Shield_damage.png").getImage();
		this.vis = new ImageIcon("res/images/Vision.png").getImage();
		this.xP = new ImageIcon("res/images/Xp_recieved.png").getImage();
	}

	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.helm, 0, 0, null);
		g2d.drawImage(this.arm, 0, 25, null);
		g2d.drawImage(this.hp, 0, 50, null);
		g2d.drawImage(this.ap, 0, 75, null);
		g2d.drawImage(this.fat, 0, 100, null);
		g2d.drawImage(this.mor, 0, 125, null);
		g2d.drawImage(this.res, 0, 150, null);
		g2d.drawImage(this.init, 0, 175, null);

		g2d.drawImage(this.mSk, 200, 0, null);
		g2d.drawImage(this.rSk, 200, 25, null);
		g2d.drawImage(this.mDef, 200, 50, null);
		g2d.drawImage(this.rDef, 200, 75, null);
		g2d.drawImage(this.dam, 200, 100, null);
		g2d.drawImage(this.armDam, 200, 125, null);
		g2d.drawImage(this.head, 200, 150, null);
		g2d.drawImage(this.vis, 200, 175, null);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
}
