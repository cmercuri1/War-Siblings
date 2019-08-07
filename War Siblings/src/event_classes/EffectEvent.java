/** War Siblings
 * AttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import storage_classes.Effect;

public class EffectEvent extends InfoEvent {
	public enum Task {
		ADD, REMOVE, GOT
	};

	protected Task task;
	protected Effect information;

	public EffectEvent(Task t, Effect info, Object src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Effect getInformation() {
		return this.information;
	}
}
