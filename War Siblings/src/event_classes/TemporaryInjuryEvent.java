/** War Siblings
 * TemporaryInjuryEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import storage_classes.TemporaryInjury;;

public class TemporaryInjuryEvent extends InfoEvent {
	public enum Task {
		ADD, HEAL_ALL, HEALED
	};

	protected Task task;
	protected TemporaryInjury information;

	public TemporaryInjuryEvent(Task t, TemporaryInjury a, Object source) {
		super(a, source);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public TemporaryInjury getInformation() {
		return this.information;
	}

}
