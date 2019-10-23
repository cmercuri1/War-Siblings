/** War Siblings
 * ItemLabel
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import items.Item;

public class ItemLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Item item;
	protected boolean isStash;

	/**
	 * 
	 */
	public ItemLabel() {
		this.isStash = false;
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	}
	
	public ItemLabel(boolean isStash) {
		this.isStash = isStash;
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	}
	
	public ItemLabel(Item item) {
		this.isStash = false;
		this.setItem(item);
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	}
	
	public ItemLabel(Item item, boolean isStash) {
		this.isStash = isStash;
		this.setItem(item);
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
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

	public void setStash(boolean isStash) {
		this.isStash = isStash;
	}

}
