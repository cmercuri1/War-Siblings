/** War Siblings
 * Positive_Morale
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.MoraleRollOutcomeEvent;
import event_classes.MoraleRollEvent;
import listener_interfaces.MoraleRollOutcomeListener;
import listener_interfaces.MoraleRollListener;

public class Effect_Morale_Roll extends Effect_Modifier_Triggered implements MoraleRollListener, MoraleRollOutcomeListener {
	protected String type;

	public Effect_Morale_Roll(String type, double modValue) {
		super(new Modifier("resolve", modValue, false, false, false));
		this.type = type;
	}

	@Override
	public void onMoraleRollOutcomeEvent(MoraleRollOutcomeEvent m) {
		if (m.getTask().toString().contains(this.type)) {
			this.triggerEnd();
		}
	}

	@Override
	public void onMoraleRollEvent(MoraleRollEvent m) {
		if (m.getTask().toString().contains(this.type)) {
			this.triggerStart();
		}
	}

}
