package common_classes;

import java.util.ArrayList;

public class Trait extends Ability {
	protected ArrayList<String> mutalExcl;

	public Trait(String name, ArrayList<Effect> effects, ArrayList<String> mutalExcl) {
		super(name, effects);
		this.mutalExcl = mutalExcl;
	}

	public ArrayList<String> getMutalExcl() {
		return this.mutalExcl;
	}

}
