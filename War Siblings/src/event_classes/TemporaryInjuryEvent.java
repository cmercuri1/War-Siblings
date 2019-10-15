/** War Siblings
 * TemporaryInjuryEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import abilities.TemporaryInjury;
import notifier_interfaces.TemporaryInjuryNotifier;;

public class TemporaryInjuryEvent extends InfoEvent {
	public enum Task {
		ADD, HEAL_ALL, HEALED
	};

	protected Task task;
	protected TemporaryInjury information;
	protected TemporaryInjuryNotifier source;

	public TemporaryInjuryEvent(Task t, TemporaryInjury info, TemporaryInjuryNotifier source) {
		super(info, source);
		this.information = info;
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public TemporaryInjury getInformation() {
		return this.information;
	}
	
	public TemporaryInjuryNotifier getSource() {
		return this.source;
	}

}
