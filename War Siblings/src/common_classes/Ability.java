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
	}

	/** Constructor for when you have all the effects */
	public Ability(String name) {
		this.name = name;
		this.effects = this.retrieveEffects();
	}

	private ArrayList<Effect> retrieveEffects() {
		// TODO Add JSON data for general weapon effects?
		return null;
	}

	public String getName() {
		return this.name;
	}

	public String getDesc() {
		return this.desc;
	}

	public ArrayList<Effect> getEffects() {
		return this.effects;
	}

	public void display() {
		System.out.print(this.name + ": " + this.desc + "\n");
		for (Effect e : this.effects) {
			e.display();
		}
	}

}
