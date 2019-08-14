/** War Siblings
 * Item Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */

package items;

import javax.swing.ImageIcon;

import event_classes.AttributeEvent;
import listener_interfaces.AttributeListener;
import storage_classes.Attribute;

/** A baseline class for item objects */
public class Item implements AttributeListener {
	protected String name; // Item name
	protected Attribute value; // Value of item
	protected String desc; // Description of item
	protected ImageIcon image;

	/** Constructor */
	public Item(String name, double value, String desc) {
		this.name = name;
		this.value = new Attribute(value);
		this.desc = desc;
		this.setIcon();
		this.setUpListeners();
	}
	
	protected void setUpListeners() {
		this.value.addAttributeListener(this);
	}

	protected void setIcon() {
		//TODO
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
		return "<html>" + this.name + "<br>" + this.desc + "<br>Worth " + this.value.toString() + " crowns</html>";
	}

	@Override
	public void onAttributeEvent(AttributeEvent a) {
		switch(a.getTask()) {
		case UPDATE:
			break;
		}
	}
}
