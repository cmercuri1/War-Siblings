package engine;

import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

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
        add(new Surface());

        setTitle("War Siblings");
        setSize(1300, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
