/** War Siblings
 * AttributeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import storage_classes.Attribute;

public class EffectEvent extends InfoEvent {
	protected enum Task {ADD, REMOVE, GOT};
	
	protected Task thisTask;
	protected Attribute information;
	
	public EffectEvent(Task t, Attribute info, Object src) {
		super(info, src);
		this.thisTask = t;
	}
	
	public Attribute getInformation() {
		return this.information;
	}
}
