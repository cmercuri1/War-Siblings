/** War Siblings
 * Item Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */

package items;

import javax.swing.ImageIcon;

import event_classes.EventObject;
import event_classes.Observer;
import storage_classes.Attribute;

/** A baseline class for item objects */
public class Item implements Observer {
	protected String name; // Item name
	protected Attribute value; // Value of item
	protected String desc; // Description of item
	protected ImageIcon image;

	/** Constructor */
	public Item(String name, double value, String desc) {
		this.name = name;
		this.value = new Attribute(value, this);
		this.desc = desc;
		this.setIcon();
	}

	protected void setIcon() {
	}

	/* Getters */

	public String getName() {
		return this.name;
	}

	public Attribute getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	public ImageIcon getImage() {
		return this.image;
	}

	/**
	 * Display: Called to output all the statistics of this item, mainly used during
	 * testing
	 */
	public void display() {
		System.out.println(this.name);
		System.out.println(this.value.toStringFull());
		System.out.println(this.desc);
	}

	public String toString() {
		return "<html>" + this.name + "<br>" + this.desc + "<br>Worth " + this.value.toString() + "crowns</html>";
	}

	@Override
	public void onEventHappening(EventObject information) {
		// TODO Auto-generated method stub

	}
}
