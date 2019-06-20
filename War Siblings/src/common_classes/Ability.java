package common_classes;

import java.util.ArrayList;

public class Ability {
	protected String name;
	protected String desc;
	protected ArrayList<Effect> effects;

	public Ability(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void display() {
		System.out.print(this.name + ": ");
		System.out.println(this.desc);
	}

}
