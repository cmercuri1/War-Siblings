/** War Siblings
 * Effect_Modifier_TimedDuration
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.AttributeEvent;
import event_classes.TriggeredEffectEvent;

public class Effect_Modifier_TimedDuration_Turn extends Effect_TimedDuration_Turn {

	protected Modifier mod;

	public Effect_Modifier_TimedDuration_Turn(double initialDuration, String modName, double modValue) {
		super(initialDuration);
		this.mod = new Modifier(modName, modValue);
	}

	public Effect_Modifier_TimedDuration_Turn(double initialDuration, Modifier mod) {
		super(initialDuration);
		this.mod = mod;
	}

	public Modifier getMod() {
		return this.mod;
	}

	@Override
	public void onAttributeEvent(AttributeEvent a) {
		switch (a.getTask()) {
		case UPDATE:
			if (a.getSource().equals(duration))
				if (a.getInformation() >= 0) {
					this.notifyTriggeredEffectListeners(
							new TriggeredEffectEvent(TriggeredEffectEvent.Task.REMOVE, this.mod, this));
				}
			break;
		}
	}

}
