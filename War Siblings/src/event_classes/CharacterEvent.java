/** War Siblings
 * CharacterEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.CharacterNotifier;

public class CharacterEvent extends InfoEvent {
	public enum Task {
		CHANGED_CHARACTER, FINISHED_CHARACTER
	};

	protected Task task;
	protected CharacterNotifier source;

	public CharacterEvent(Task t, Object info, CharacterNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public CharacterNotifier getSource() {
		return this.source;
	}
}
