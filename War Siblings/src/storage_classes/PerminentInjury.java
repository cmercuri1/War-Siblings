/** War Siblings
 * PerminentInjury class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import storage_classes.ArrayList;

/**
 * A class used for storing and assisting in resolving use of Perminent Injuries
 * that can affect characters/creatures
 */
public class PerminentInjury extends ObservableAbility {
	protected boolean contentInReserve;	

	public PerminentInjury(String name, String desc, ArrayList<Effect> effects, boolean contentInReserve) {
		super(name, desc, effects);
		this.contentInReserve = contentInReserve;
	}

	/* Getters */
	
	public boolean isContentInReserve() {
		return this.contentInReserve;
	}

}
