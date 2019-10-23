/** War Siblings
 * InventoryDisplayPanel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.MatteBorder;

import global_managers.GlobalManager;
import items.Item;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InventoryDisplayPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int rows;
	protected int columns;
	protected String boxSize;

	/**
	 * Create the panel.
	 */
	public InventoryDisplayPanel() {
		DragMouseAdapter listener = new DragMouseAdapter();
		setBackground(new Color(102, 0, 0));
		this.rows = 17;
		this.columns = 15;

		this.boxSize = "[70]";

		setLayout(new MigLayout("", new String(new char[columns]).replace("\0", boxSize),
				new String(new char[rows]).replace("\0", boxSize)));

		ItemLabel[] items = this.makeStash(rows * columns);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.add(items[i * columns + j], "cell " + j + " " + i + " ,grow");
			}
		}

	}

	private class DragMouseAdapter extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);
		}
	}

	protected ItemLabel[] makeStash(int size) {
		ItemLabel[] temp = new ItemLabel[size];

		int i = 0;
		for (Item item : GlobalManager.equipment.getItemList()) {
			temp[i] = new ItemLabel(item, true);
			i++;
		}

		while (i < temp.length) {
			temp[i] = new ItemLabel(true);
			i++;
		}

		return temp;
	}
}
