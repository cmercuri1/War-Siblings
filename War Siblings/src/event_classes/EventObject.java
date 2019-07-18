package event_classes;

public class EventObject {
	protected Target target;
	protected EventType task;
	protected Object information;
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
