/** War Siblings
 * Effect class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import global_managers.GlobalManager;

/**
 * Extention of modifier class, effects have a particular target and sub-target
 * manager that they affect
 */
public class Effect extends Modifier {
	protected String affectedManager;
	protected String affectedSubManager;

	public Effect(String eName, double val) {
		super(eName, val, false, false, false);
		this.setIsMulti(this.name);
		this.setFinalAdd(this.name);
		this.setIsUnique(this.name);
		this.findExtra();
	}

	public Effect(String eName) {
		super(eName, GlobalManager.UNUSED, false, false, false);
		this.setIsMulti(this.name);
		this.setFinalAdd(this.name);
		this.setIsUnique(this.name);
		this.findExtra();
	}

	/** Only used for GlobalMananger */
	public Effect(String eName, String aM, String aSM) {
		super(eName, GlobalManager.UNUSED, false, false, false);
		this.affectedManager = aM;
		this.affectedSubManager = aSM;
	}

	/**
	 * Only used for Testing to create special effect without having to find correct
	 * one
	 */
	public Effect(String eName, double value, boolean isMulti, boolean isFinal, boolean isUnique) {
		super(eName, value, isMulti, isFinal, isUnique);
	}

	private void setIsMulti(String eName) {
		if (eName.contains("_Percent")) {
			this.isMulti = true;
			this.name = eName.replace("_Percent", "");
		}
	}

	private void setFinalAdd(String eName) {
		if (eName.contains("_Final")) {
			this.finalAdd = true;
			this.name = eName.replace("_Final", "");
		}
	}

	private void setIsUnique(String eName) {
		if (eName.contains("_Unique")) {
			this.isUnique = true;
			this.name = eName.replace("_Unique", "");
		}
	}

	/**
	 * Finds the general effect and configures this instances managers to match so
	 * that it knows who to tell to adjust
	 */
	public void findExtra() {
		Effect temp;
		try {
			temp = GlobalManager.effects.search(this.name);

			this.affectedManager = temp.affectedManager;
			this.affectedSubManager = temp.affectedSubManager;
		} catch (NullPointerException nu) {
			nu.printStackTrace();
		}
	}

	/* Getters */

	/** Returns the Modifier form of this effect */
	public Modifier getModifier() {
		return new Modifier(this.name, this.value, this.isMulti, this.finalAdd, this.isUnique);
	}

	public String getAffectedManager() {
		return this.affectedManager;
	}

	public String getAffectedSubManager() {
		return this.affectedSubManager;
	}

	public void display() {
		System.out.print("	");
		super.display();
		System.out.println("	" + this.affectedManager + ", " + this.affectedSubManager);
	}
}
