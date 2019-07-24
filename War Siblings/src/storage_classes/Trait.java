/** War Siblings
 * Trait class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import storage_classes.ArrayList;

/**
 * A class used for storing information about Trait style abilities which can be
 * given at character creation
 */
public class Trait extends Ability {
	protected ArrayList<String> mutalExcl; // A list of which traits this one is incompatible with

	public Trait(String name, ArrayList<Effect> effects, ArrayList<String> mutalExcl) {
		super(name, effects);
		this.mutalExcl = mutalExcl;
	}

	/* Getters */

	public ArrayList<String> getMutalExcl() {
		return this.mutalExcl;
	}

}
