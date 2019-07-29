package engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AttributePanel extends JPanel {
	
	private Image image;

	public AttributePanel(String fileName) {
		this.initPanel(fileName);
	}
	
	private void initPanel(String fileName) {
		this.loadImage(fileName);
		Dimension dm = new Dimension(image.getWidth(null), image.getHeight(null));
		this.setPreferredSize(dm);
	}

	private void loadImage(String fileName) {
		this.image = new ImageIcon(fileName).getImage();
	}
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(this.image, 0, 0, null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.doDrawing(g);
	}
}
