/** War Siblings
 * Trait class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package abilities;

import javax.swing.ImageIcon;

import effect_classes.Effect;
import storage_classes.ArrayList;

/**
 * A class used for storing information about Trait style abilities which can be
 * given at character creation
 */
public class Trait extends PassiveAbility {
	protected ArrayList<String> mutalExcl; // A list of which traits this one is incompatible with
	protected String specificBackground;

	public Trait(String name, ArrayList<Effect> effects, ArrayList<String> mutalExcl) {
		super(name, effects);
		this.mutalExcl = mutalExcl;
	}

	public Trait(String name, ArrayList<Effect> effects, String specificBackground) {
		super(name, effects);
		this.specificBackground = specificBackground;
	}

	protected void setImage() {
		try {
			this.image = new ImageIcon(Trait.class.getResource("/images/Traits/" + this.name + ".png"));
		} catch (NullPointerException n) {
			System.out.println(this.name);
		}
	}

	/* Getters */

	public ArrayList<String> getMutalExcl() {
		return this.mutalExcl;
	}

	public String getSpecificBackground() {
		return this.specificBackground;
	}

}
