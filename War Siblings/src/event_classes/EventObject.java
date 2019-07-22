/** War Siblings
 * EventObject class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

/**
 * A class used in Event Handling, has a Target, an EventType, Information and
 * the Requester
 */
public class EventObject {
	protected Target target;
	protected EventType task;
	protected Object information; // Is Object so that anything can be placed here
	protected Observer requester;

	public EventObject(Target target, EventType task, Object information, Observer requester) {
		this.target = target;
		this.task = task;
		this.information = information;
		this.requester = requester;
	}

	public Target getTarget() {
		return this.target;
	}

	public EventType getTask() {
		return this.task;
	}

	public Object getInformation() {
		return this.information;
	}

	public Observer getRequester() {
		return this.requester;
	}

	public String toString() {
		return this.task.toString() + ": " + this.information.toString();
	}
}
