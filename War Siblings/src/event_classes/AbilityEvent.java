/** War Siblings
 * AbilityEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import storage_classes.Ability;

public class AbilityEvent extends InfoEvent {
	public enum Task {ADD, REMOVE, GOT};
	
	protected Task thisTask;
	protected Ability information;


	public AbilityEvent(Task t, Ability a, Object source) {
		super(a, source);
		this.thisTask = t;
	}
	
	public Task getTask() {
		return this.thisTask;
	}
	
	public Ability getInformation() {
		return this.information;
	}

}
