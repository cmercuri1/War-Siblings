/** War Siblings
 * SkillPreferenceEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.SkillPreferenceNotifier;

public class SkillPreferenceEvent extends InfoEvent {
	public enum Task {
		MELEE_PREF, RANGED_PREF
	};

	protected Task task;
	protected double information;
	protected SkillPreferenceNotifier source;

	public SkillPreferenceEvent(Task t, double info, SkillPreferenceNotifier src) {
		super(info, src);
		this.task = t;
		this.information = info;
		this.source = src;
	}

	public Task getTask() {
		return this.task;
	}
	
	public Double getInformation() {
		return this.information;
	}

	public SkillPreferenceNotifier getSource() {
		return this.source;
	}


}
