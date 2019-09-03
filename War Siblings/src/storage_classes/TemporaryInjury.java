/** War Siblings
 * TemporaryInjury class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import storage_classes.ArrayList;
import attributes.Attribute;
import effect_classes.Effect;
import effect_classes.Modifier;
import event_classes.AttributeEvent;
import event_classes.TemporaryInjuryEvent;
import global_managers.GlobalManager;
import listener_interfaces.AttributeListener;
import listener_interfaces.TemporaryInjuryListener;
import notifier_interfaces.TemporaryInjuryNotifier;

/**
 * A class used for storing and assisting in resolving use of Temporary Injuries
 * that can affect characters/creatures
 */
public class TemporaryInjury extends Ability implements TemporaryInjuryNotifier, AttributeListener {
	protected boolean isHead;
	protected String damageType;
	protected double damageThreshold;
	protected Attribute minDays;
	protected Attribute maxDays;

	protected ArrayList<TemporaryInjuryListener> tempInuryListeners;

	public TemporaryInjury(String name, String desc, boolean isHead, String dType, double dThres,
			ArrayList<Effect> effects, double minDays, double maxDays) {
		super(name, desc, effects);
		this.isHead = isHead;
		this.damageType = dType;
		this.damageThreshold = dThres;
		this.minDays = new Attribute(minDays);
		this.maxDays = new Attribute(maxDays);

		this.setUpNotificationSystem();
	}

	protected void setUpNotificationSystem() {
		this.tempInuryListeners = new ArrayList<TemporaryInjuryListener>();
		this.minDays.addAttributeListener(this);
		this.maxDays.addAttributeListener(this);
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
			this.notifyTemporaryInjuryListeners(new TemporaryInjuryEvent(TemporaryInjuryEvent.Task.HEALED, null, this));
			return;
		}

		if (this.minDays.getAlteredValue() == 0.0) {
			if (GlobalManager.d100Roll() <= 100 / (this.maxDays.getAlteredValue())) {
				this.notifyTemporaryInjuryListeners(
						new TemporaryInjuryEvent(TemporaryInjuryEvent.Task.HEALED, null, this));
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

	public void display() {
		super.display();
		System.out.println("Days remaining: " + this.minDays.toString() + " - " + this.maxDays.toString());
	}

	@Override
	public void onAttributeEvent(AttributeEvent a) {
		switch (a.getTask()) {
		case UPDATE:
			this.checkForHealed();
			break;
		}
	}

	@Override
	public void addTemporaryInjuryListener(TemporaryInjuryListener t) {
		this.tempInuryListeners.add(t);
	}

	@Override
	public void removeTemporaryInjuryListener(TemporaryInjuryListener t) {
		this.tempInuryListeners.remove(t);
	}

	@Override
	public void notifyTemporaryInjuryListeners(TemporaryInjuryEvent t) {
		this.tempInuryListeners.forEach(l -> l.onTemporaryInjuryEvent(t));
	}

	@Override
	public void notifyTemporaryInjuryListener(TemporaryInjuryListener t, TemporaryInjuryEvent e) {
		this.tempInuryListeners.get(t).onTemporaryInjuryEvent(e);
	}
}
