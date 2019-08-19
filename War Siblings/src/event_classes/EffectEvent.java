/** War Siblings
 * AttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import effect_classes.Effect;
import notifier_interfaces.EffectNotifier;

public class EffectEvent extends InfoEvent {
	public enum Task {
		ADD, REMOVE
	};

	protected Task task;
	protected Effect information;
	protected EffectNotifier source;

	public EffectEvent(Task t, Effect info, EffectNotifier src) {
		super(info, src);
		this.information = info;
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Effect getInformation() {
		return this.information;
	}

	public EffectNotifier getSource() {
		return this.source;
	}
}
