/** War Siblings
 * PermanentInjuryEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import abilities.PermanentInjury;
import notifier_interfaces.PermanentInjuryNotifier;

public class PermanentInjuryEvent extends InfoEvent {
	public enum Task {
		ADD, HEAL_ALL, HEALED
	};

	protected Task task;
	protected PermanentInjury information;
	protected PermanentInjuryNotifier source;

	public PermanentInjuryEvent(Task t, PermanentInjury info, PermanentInjuryNotifier source) {
		super(info, source);
		this.information = info;
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public PermanentInjury getInformation() {
		return this.information;
	}

	public PermanentInjuryNotifier getSource() {
		return this.source;
	}
}
