/** War Siblings
 * PostEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.PostDataNotifier;

public class PostDataEvent extends InfoEvent {
	public enum Task {
		GOT, GOT_OTHER
	};

	protected Task task;
	protected String requestedInfo;
	protected PostDataNotifier source;

	public PostDataEvent(Task t, String requestedInfo, Object info, PostDataNotifier src) {
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

	public PostDataNotifier getSource() {
		return this.source;
	}
}
