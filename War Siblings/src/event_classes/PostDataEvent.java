/** War Siblings
 * PostEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class PostDataEvent extends InfoEvent {
	public enum Task {GOT, GOT_OTHER};
	
	protected Task task;
	
	public PostDataEvent(Task t, Object info, Object src) {
		super(info, src);
		this.task = t;
	}
	
	public Task getTask() {
		return this.task;
	}
}
