package items;

import common_classes.Attribute;

public class Item {
	protected String name;
	protected Attribute value;
	protected String desc;

	public Item(String name, double value, String desc) {
		this.name = name;
		this.value = new Attribute(value);
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void display() {
		System.out.println(this.name);
		System.out.println(this.value);
		System.out.println(this.desc);
	}
}
