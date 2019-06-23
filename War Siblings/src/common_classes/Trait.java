package common_classes;

import java.util.ArrayList;

public class Trait extends Ability {
	protected ArrayList<String> specBg;
	protected ArrayList<String> invalBg;
	protected ArrayList<String> mutalExcl;

	public Trait(String name, ArrayList<Effect> effects, ArrayList<String> specBg, ArrayList<String> invalBg,
			ArrayList<String> mutalExcl) {
		super(name, effects);
		this.specBg = specBg;
		this.invalBg = invalBg;
		this.mutalExcl = mutalExcl;
	}

	public ArrayList<String> getSpecBg() {
		return this.specBg;
	}

	public ArrayList<String> getInvalBg() {
		return this.invalBg;
	}

	public ArrayList<String> getMutalExcl() {
		return this.mutalExcl;
	}

}
