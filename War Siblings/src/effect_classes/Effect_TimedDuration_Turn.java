/** War Siblings
 * Effect_TimedDuration_Turn
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.AttributeEvent;
import event_classes.TriggeredEffectEvent;
import listener_interfaces.AttributeListener;
import storage_classes.Attribute;

public class Effect_TimedDuration_Turn extends Effect_Turn_Triggered implements AttributeListener {

	protected Attribute duration;

	public Effect_TimedDuration_Turn(double initialDuration) {
		this.duration = new Attribute(initialDuration);

		this.duration.addAttributeListener(this);
	}

	@Override
	protected void triggerStart() {

	}

	@Override
	protected void triggerEnd() {
		this.duration.addModifier(new Modifier("trigger", -1, false, true, false));
	}

	public void alterDuration(Modifier m) {
		this.duration.addModifier(m);
	}

	public Double getRemainingTurns() {
		return this.duration.getAlteredValue();
	}

	@Override
	public void onAttributeEvent(AttributeEvent a) {
		switch (a.getTask()) {
		case UPDATE:
			if (a.getSource().equals(duration))
				if (a.getInformation() <= 0) {
					this.notifyTriggeredEffectListeners(
							new TriggeredEffectEvent(TriggeredEffectEvent.Task.REMOVE, null, this));
				}
			break;
		}
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

}
