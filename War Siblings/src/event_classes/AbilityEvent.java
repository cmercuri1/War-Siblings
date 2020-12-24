/** War Siblings
 * AbilityEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import abilities.Ability;
import notifier_interfaces.AbilityNotifier;

public class AbilityEvent extends InfoEvent {
	public enum Task {
		ADD, REMOVE, REMOVE_ALL
	};

	protected Task task;
	protected Ability information;
	protected AbilityNotifier source;

	public AbilityEvent(Task t, Ability info, AbilityNotifier source) {
		super(info, source);
		this.information = info;
		this.task = t;
		this.source = source;
	}

	public Task getTask() {
		return this.task;
	}

	public Ability getInformation() {
		return this.information;
	}

	public AbilityNotifier getSource() {
		return this.source;
	}
}
