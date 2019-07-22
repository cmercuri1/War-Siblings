/** War Siblings
 * Item Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */

package items;

import storage_classes.Attribute;

/** A baseline class for item objects */
public class Item {
	protected String name; // Item name
	protected Attribute value; // Value of item
	protected String desc; // Description of item

	/** Constructor */
	public Item(String name, double value, String desc) {
		this.name = name;
		this.value = new Attribute(value);
		this.desc = desc;
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

	/**
	 * Display: Called to output all the statistics of this item, mainly used during
	 * testing
	 */
	public void display() {
		System.out.println(this.name);
		System.out.println(this.value);
		System.out.println(this.desc);
	}
}
