/** War Siblings
 * TemporaryInjury class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import storage_classes.ArrayList;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.Observer;
import global_managers.GlobalManager;

/**
 * A class used for storing and assisting in resolving use of Temporary Injuries
 * that can affect characters/creatures
 */
public class TemporaryInjury extends ObservableAbility implements Observer {
	protected boolean isHead;
	protected String damageType;
	protected double damageThreshold;
	protected Attribute minDays;
	protected Attribute maxDays;

	public TemporaryInjury(String name, String desc, boolean isHead, String dType, double dThres,
			ArrayList<Effect> effects, double minDays, double maxDays) {
		super(name, effects);
		this.desc = desc;
		this.isHead = isHead;
		this.damageType = dType;
		this.damageThreshold = dThres;
		this.minDays = new Attribute(minDays, this);
		this.maxDays = new Attribute(maxDays, this);
	}

	/**
	 * healInjury: tells the injury to heal itself a bit and then checks to see if
	 * it has actually healed
	 */
	public void healInjury() {
		this.minDays.addModifier(new Modifier("Healing", -1.0, false, true, false));
		this.maxDays.addModifier(new Modifier("Healing", -1.0, false, true, false));
	}

	/**
	 * checkForHealed: checks to see if injury has healed. This can happen if
	 * either: The max days reaches 0, or, The min days reaches 0 and a random roll
	 * succeeds
	 */
	protected void checkForHealed() {
		if (this.maxDays.getAlteredValue() == 0.0) {
			this.notifyObservers(new EventObject(null, EventType.HEALED, null, this));
			return;
		}

		if (this.minDays.getAlteredValue() == 0.0) {
			if (GlobalManager.d100Roll() <= 100 / (this.maxDays.getAlteredValue() + 1.0)) {
				this.notifyObservers(new EventObject(null, EventType.HEALED, null, this));
				return;
			} else {
				this.minDays.addModifier(new Modifier("Still Injured", 1.0, false, true, false));
			}
		}
	}

	/* Getters */

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

	@Override
	public void onEventHappening(EventObject information) {

	}

	public void display() {
		super.display();
		System.out.println("Days remaining: " + this.minDays.toString() + " - " + this.maxDays.toString());
	}
}
