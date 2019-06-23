package common_classes;

import java.util.ArrayList;

public class Ability {
	protected String name;
	protected String desc;
	protected ArrayList<Effect> effects;

	/** Constructor for when you have all the effects */
	public Ability(String name, ArrayList<Effect> effects) {
		this.name = name;
		this.effects = effects;
		this.desc = this.retrieveDesc();
	}

	private String retrieveDesc() {
		return null;
	}

	public String getName() {
		return this.name;
	}
	
	public ArrayList<Effect> getEffects() {
		return this.effects;
	}

	public void display() {
		System.out.print(this.name + ": ");
		System.out.println(this.desc);
	}

}
