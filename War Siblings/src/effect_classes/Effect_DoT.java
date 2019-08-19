/** War Siblings
 * Effect_DoT
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.AttributeEvent;
import event_classes.TriggeredEffectEvent;
import listener_interfaces.AttributeListener;
import storage_classes.Attribute;

public class Effect_DoT extends Effect_TimedDuration_Turn implements AttributeListener {

	protected Attribute damageOnTurn;

	public Effect_DoT(double initialDuration, double damage) {
		super(initialDuration);
		this.damageOnTurn = new Attribute(damage);
		this.damageOnTurn.addAttributeListener(this);
	}

	@Override
	protected void triggerEnd() {
		this.notifyTriggeredEffectListeners(
				new TriggeredEffectEvent(TriggeredEffectEvent.Task.DAMAGE, this.damageOnTurn.getAlteredValue(), this));
		super.triggerEnd();
	}

	@Override
	public void onAttributeEvent(AttributeEvent a) {

	}

}
