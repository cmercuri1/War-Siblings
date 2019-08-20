/** War Siblings
 * Effect_Irrational
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.MoraleRollOutcomeEvent;
import event_classes.MoraleRollEvent;
import global_managers.GlobalManager;
import listener_interfaces.MoraleRollOutcomeListener;
import listener_interfaces.MoraleRollListener;

public class Effect_Irrational extends Effect_Modifier_Triggered implements MoraleRollListener, MoraleRollOutcomeListener {
	protected double modValue;

	public Effect_Irrational(double modValue) {
		super(new Modifier("resolve", modValue, false, false, false));
		this.modValue = modValue;
	}

	protected void rerollIrrational() {
		if (GlobalManager.d100Roll() < 51)
			this.mod = new Modifier("resolve", -modValue, false, false, false);
		else
			this.mod = new Modifier("resolve", modValue, false, false, false);
	}

	@Override
	public void onMoraleRollOutcomeEvent(MoraleRollOutcomeEvent m) {
		this.triggerEnd();
	}

	@Override
	public void onMoraleRollEvent(MoraleRollEvent m) {
		this.rerollIrrational();
		this.triggerStart();
	}

}
