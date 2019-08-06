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
	protected Type task;
	protected Object information; // Is Object so that anything can be placed here
	protected Object requester;

	public EventObject(Target target, Type task, Object information, Object requester) {
		this.target = target;
		this.task = task;
		this.information = information;
		this.requester = requester;
	}

	public Target getTarget() {
		return this.target;
	}

	public Type getTask() {
		return this.task;
	}

	public Object getInformation() {
		return this.information;
	}

	public Object getRequester() {
		return this.requester;
	}

	public String toString() {
		return this.task.toString() + ": " + this.information.toString();
	}
}
