/** War Siblings
 * PostEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class PostDataEvent extends InfoEvent {
	public enum Task {GOT, GOT_OTHER};
	
	protected Task task;
	protected String requestedInfo;
	
	public PostDataEvent(Task t, String requestedInfo, Object info, Object src) {
		super(info, src);
		this.task = t;
		this.requestedInfo = requestedInfo;
	}
	
	public Task getTask() {
		return this.task;
	}
	
	public String getRequestedInfo() {
		return this.requestedInfo;
	}
}
