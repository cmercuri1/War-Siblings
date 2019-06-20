package items;

import java.util.ArrayList;

import common_classes.Ability;
import common_classes.Attribute;

public class Shield extends AbilityItem {
	protected Attribute meleeDef;
	protected Attribute rangedDef;

	public Shield(String name, double value, String desc, double dura, double fatRed, double mDef, double rDef,
			ArrayList<Ability> abilityList) {
		super(name, value, desc, dura, fatRed, abilityList);
		this.meleeDef = new Attribute(mDef);
		this.rangedDef = new Attribute(rDef);
	}
	
	public void display() {
		System.out.println(this.name);
		System.out.println(this.durability.getAlteredCurrentValue() + "/" + this.durability.getAlteredValue());
		System.out.println(this.desc);
		System.out.println("Reduces Max Fatigue by " + this.fatigueRed.getAlteredValue());

		System.out.println("Grants use of: ");
		for (Ability a : this.abilityList) {
			System.out.print(" -");
			a.display();
		}
		System.out.println("");
	}

}
