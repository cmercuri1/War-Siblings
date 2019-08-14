/** War Siblings
 * LevelUpAttribute
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.LevelUpAttributeNotifier;

public class LevelUpAttributeEvent extends InfoEvent {
	public enum Task {
		LEVEL_UP
	};

	protected Task task;
	protected double information;
	protected LevelUpAttributeNotifier source;

	public LevelUpAttributeEvent(Task t, double info, LevelUpAttributeNotifier src) {
		super(info, src);
		this.information = info;
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public Double getInformation() {
		return this.information;
	}

	public LevelUpAttributeNotifier getSource() {
		return this.source;
	}
}
