/** War Siblings
 * ItemLabel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;
import javax.swing.border.MatteBorder;

import items.Item;

public class ItemLabel extends JLabel implements MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MouseEvent firstMouseEvent = null;
	protected Item item;
	protected boolean isStash;

	/**
	 * 
	 */
	public ItemLabel() {
		this.isStash = false;
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.addMouseListener(new DragMouseAdapter());
	}

	public ItemLabel(boolean isStash) {
		this.isStash = isStash;
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.addMouseListener(new DragMouseAdapter());
	}

	public ItemLabel(Item item) {
		this.isStash = false;
		this.setItem(item);
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.addMouseListener(new DragMouseAdapter());
	}

	public ItemLabel(Item item, boolean isStash) {
		this.isStash = isStash;
		this.setItem(item);
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.addMouseListener(new DragMouseAdapter());
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
		if (this.isStash) {
			this.setIcon(new StretchIcon(item.getInvImage().getImage(), true));
		} else {
			this.setIcon(new StretchIcon(item.getImage().getImage(), true));
		}
		this.setToolTipText(item.toString());
	}

	public void recieveItem(Item item) {
	}

	public void setStash(boolean isStash) {
		this.isStash = isStash;
	}

	public void mousePressed(MouseEvent e) {
		// Don't bother to drag if there is no image.
		if (item == null)
			return;

		firstMouseEvent = e;
		e.consume();
	}

	public void mouseDragged(MouseEvent e) {
		// Don't bother to drag if the component displays no image.
		if (item == null)
			return;

		if (firstMouseEvent != null) {
			e.consume();

			// If they are holding down the control key, COPY rather than MOVE
			int ctrlMask = InputEvent.CTRL_DOWN_MASK;
			int action = ((e.getModifiersEx() & ctrlMask) == ctrlMask) ? TransferHandler.COPY : TransferHandler.MOVE;

			int dx = Math.abs(e.getX() - firstMouseEvent.getX());
			int dy = Math.abs(e.getY() - firstMouseEvent.getY());
			// Arbitrarily define a 5-pixel shift as the
			// official beginning of a drag.
			if (dx > 5 || dy > 5) {
				// This is a drag, not a click.
				JComponent c = (JComponent) e.getSource();
				TransferHandler handler = c.getTransferHandler();
				// Tell the transfer handler to initiate the drag.
				handler.exportAsDrag(c, firstMouseEvent, action);
				firstMouseEvent = null;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		firstMouseEvent = null;
	}

	public void mouseMoved(MouseEvent e) {
	}

	private class DragMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);
		}
	}
}
