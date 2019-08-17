/** War Siblings
 * Effect_TimedDuration_Turn
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.AttributeEvent;
import event_classes.TriggeredEffectEvent;
import listener_interfaces.AttributeListener;
import storage_classes.Attribute;
import storage_classes.Modifier;

public class Effect_TimedDuration_Turn extends Effect_TurnSituational implements AttributeListener {

	protected Attribute duration;
	
	public Effect_TimedDuration_Turn(double initialDuration) {
		this.duration = new Attribute(initialDuration);
		
		this.duration.addAttributeListener(this);
	}

	@Override
	protected void triggerStart() {
		// TODO Auto-generated method stub

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
			if (a.getInformation() >= 0) {
				this.notifyTriggeredEffectListeners(
						new TriggeredEffectEvent(TriggeredEffectEvent.Task.REMOVE, null, this));
			}
			break;
		}
	}

}
