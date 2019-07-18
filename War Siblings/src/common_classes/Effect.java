package common_classes;

import global_managers.GlobalManager;

public class Effect extends Modifier {
	protected String affectedManager;
	protected String affectedSubManager;

	public Effect(String eName, double val) {
		super(eName, val, false, false, false);
		this.setIsMulti(eName);
		this.findExtra();
	}

	public Effect(String eName) {
		super(eName, GlobalManager.UNUSED, false, false, false);
		this.setIsMulti(eName);
		this.findExtra();
	}

	/** Only used for GlobalMananger */
	public Effect(String eName, String aM, String aSM, boolean isFinal) {
		super(eName, GlobalManager.UNUSED, false, isFinal, false);
		this.setIsMulti(eName);
		this.affectedManager = aM;
		this.affectedSubManager = aSM;
	}

	private void setIsMulti(String eName) {
		this.isMulti = eName.contains("Percent");
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
			this.finalAdd = temp.finalAdd;
		} catch (NullPointerException nu) {
			nu.printStackTrace();
		}
	}

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
		System.out.println("-" + this.name + ": " + this.value + ", " + this.isMulti + ", " + this.affectedSubManager);
	}
}
