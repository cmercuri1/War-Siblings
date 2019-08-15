/** War Siblings
 * Effect_DoT
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.AttributeEvent;
import event_classes.TriggeredEffectEvent;
import listener_interfaces.AttributeListener;
import storage_classes.Attribute;
import storage_classes.Modifier;

public class Effect_DoT extends Effect_TurnSituational implements AttributeListener {

	protected Attribute duration;
	protected Attribute damageOnTurn;

	public Effect_DoT(double initialDuration, double damage) {
		this.duration = new Attribute(initialDuration);
		this.damageOnTurn = new Attribute(damage);

		this.duration.addAttributeListener(this);
		this.damageOnTurn.addAttributeListener(this);
	}

	public void alterDuration(Modifier m) {
		this.duration.addModifier(m);
	}

	public Double getRemainingTurns() {
		return this.duration.getAlteredValue();
	}

	@Override
	public void triggerStart() {
	}
	
	@Override
	public void triggerEnd() {
		this.notifyTriggeredEffectListeners(
				new TriggeredEffectEvent(TriggeredEffectEvent.Task.DAMAGE, this.damageOnTurn.getAlteredValue(), this));
		this.duration.addModifier(new Modifier("trigger", -1, false, true, false));
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
