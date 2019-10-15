/** War Siblings
 * PerminentInjury class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package abilities;

import effect_classes.Effect;
import event_classes.PermanentInjuryEvent;
import listener_interfaces.PermanentInjuryListener;
import notifier_interfaces.PermanentInjuryNotifier;
import storage_classes.ArrayList;
import storage_classes.PassiveAbility;

/**
 * A class used for storing and assisting in resolving use of Permanent Injuries
 * that can affect characters/creatures
 */
public class PermanentInjury extends PassiveAbility implements PermanentInjuryNotifier {
	protected boolean contentInReserve;

	protected ArrayList<PermanentInjuryListener> permaInjuryListeners;

	public PermanentInjury(String name, String desc, ArrayList<Effect> effects, boolean contentInReserve) {
		super(name, desc, effects);
		this.contentInReserve = contentInReserve;

		this.setUpNotificationSystem();
	}

	protected void setUpNotificationSystem() {
		this.permaInjuryListeners = new ArrayList<PermanentInjuryListener>();
	}

	/* Getters */

	public boolean isContentInReserve() {
		return this.contentInReserve;
	}

	@Override
	public void addPermanentInjuryListener(PermanentInjuryListener p) {
		this.permaInjuryListeners.add(p);
	}

	@Override
	public void removePermanentInjuryListener(PermanentInjuryListener p) {
		this.permaInjuryListeners.remove(p);
	}

	@Override
	public void notifyPermanentInjuryListeners(PermanentInjuryEvent p) {
		this.permaInjuryListeners.forEach(l -> l.onPermanentInjuryEvent(p));
	}

	@Override
	public void notifyPermanentInjuryListener(PermanentInjuryListener p, PermanentInjuryEvent e) {
		this.permaInjuryListeners.get(p).onPermanentInjuryEvent(e);
	}

}
