/** War Siblings
 * TraitEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.TraitNotifier;
import storage_classes.Trait;

public class TraitEvent extends InfoEvent {
	public enum Task {
		ADD, REMOVE, REMOVE_ALL
	};

	protected Task task;
	protected Trait information;
	protected TraitNotifier source;

	public TraitEvent(Task t, Trait info, TraitNotifier source) {
		super(info, source);
		this.task = t;
		this.information = info;
		this.source = source;
	}

	public Task getTask() {
		return this.task;
	}

	public Trait getInformation() {
		return this.information;
	}

	public TraitNotifier getSource() {
		return this.source;
	}
}
