/** War Siblings
 * ModifierEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import effect_classes.Modifier;
import notifier_interfaces.ModifierNotifier;

public class ModifierEvent extends InfoEvent {
	public enum Task {
		ADD, REMOVE
	};

	protected Task task;
	protected Modifier information;
	protected ModifierNotifier source;

	public ModifierEvent(Task t, Modifier info, ModifierNotifier src) {
		super(info, src);
		this.information = info;
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Modifier getInformation() {
		return this.information;
	}

	public ModifierNotifier getSource() {
		return this.source;
	}
}
