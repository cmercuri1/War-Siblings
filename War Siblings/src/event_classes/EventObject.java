package event_classes;

public abstract class EventObject {
	protected String task;
	protected Object information;
	
	public Object getTask() {
		return this.task;
	}
	
	public Object getInformation() {
		return this.information;
	}
	
	public String toString() {
		return this.task.toString() + ": " + this.information.toString();
	}
}
