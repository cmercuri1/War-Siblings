/** War Siblings
 * BattleEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.BattleControlNotifier;
import storage_classes.BattleConditions;

public class BattleControlEvent extends InfoEvent {
	public enum Task {
		START_BATTLE, END_BATTLE
	};

	protected Task task;
	protected BattleConditions information;
	protected BattleControlNotifier source;

	public BattleControlEvent(Task t, BattleConditions info, BattleControlNotifier src) {
		super(info, src);
		this.information = info;
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}

	public BattleConditions getInformation() {
		return this.information;
	}

	public BattleControlNotifier getSource() {
		return this.source;
	}
}
