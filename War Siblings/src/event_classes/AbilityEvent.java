/** War Siblings
 * AbilityEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import storage_classes.Ability;

public class AbilityEvent extends InfoEvent {
	public enum Task {
		ADD, REMOVE, REMOVE_ALL
	};

	protected Task task;
	protected Ability information;

	public AbilityEvent(Task t, Ability a, Object source) {
		super(a, source);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Ability getInformation() {
		return this.information;
	}

}
