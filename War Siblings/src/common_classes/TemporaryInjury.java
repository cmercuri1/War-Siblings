package common_classes;

import java.util.ArrayList;

import global_managers.GlobalManager;

public class TemporaryInjury extends Ability {
	protected boolean isHead;
	protected String damageType;
	protected double damageThreshold;
	protected Attribute minDays;
	protected Attribute maxDays;
	
	protected boolean isHealed;

	public TemporaryInjury(String name, String desc, boolean isHead, String dType, double dThres,
			ArrayList<Effect> effects, double minDays, double maxDays) {
		super(name, effects);
		this.desc = desc;
		this.isHead = isHead;
		this.damageType = dType;
		this.damageThreshold = dThres;
		this.minDays = new Attribute(minDays);
		this.maxDays = new Attribute(maxDays);
		
		this.isHealed = false;
	}

	public void healInjury() {
		this.minDays.addModifier(new Modifier("Healing", -1.0, false, true, false));
		this.maxDays.addModifier(new Modifier("Healing", -1.0, false, true, false));

		this.checkForHealed();
	}

	public void checkForHealed() {
		if (this.maxDays.getAlteredValue() == 0.0) {
			this.isHealed = true;
			return;
		}

		if (this.minDays.getAlteredValue() == 0.0) {
			if (GlobalManager.d100Roll() <= 100 / (this.maxDays.getAlteredValue() + 1.0)) {
				this.isHealed = true;
				return;
			} else {
				this.minDays.addModifier(new Modifier("Still Injured", 1.0, false, true, false));
			}
		}
	}

	public boolean isHead() {
		return this.isHead;
	}

	public String getDamageType() {
		return this.damageType;
	}

	public double getDamageThreshold() {
		return this.damageThreshold;
	}

	public Attribute getMinDays() {
		return this.minDays;
	}

	public Attribute getMaxDays() {
		return this.maxDays;
	}
	
	public boolean isHealed() {
		return this.isHealed;
	}

	public void display() {
		super.display();
		System.out.println("Days remaining: " + this.minDays.toString() + " - " + this.maxDays.toString());
	}
}
