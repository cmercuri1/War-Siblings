/** War Siblings
 * ItemEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.ItemNotifier;

public class ItemEvent extends InfoEvent {
	public enum Task {
		BROKEN, MODIFY_VALUE, REPAIRED
	};

	protected Task task;
	protected double information;
	protected ItemNotifier source;

	public ItemEvent(Task t, double info, ItemNotifier src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Double getInformation() {
		return this.information;
	}

	public ItemNotifier getSource() {
		return this.source;
	}

}
