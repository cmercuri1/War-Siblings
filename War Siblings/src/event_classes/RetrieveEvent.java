/** War Siblings
 * RetrieveEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import listener_interfaces.PostDataListener;
import notifier_interfaces.RetrievalNotifier;

public class RetrieveEvent extends InfoEvent {

	protected PostDataListener target;
	protected String information;
	protected RetrievalNotifier source;

	public RetrieveEvent(PostDataListener target, String info, RetrievalNotifier src) {
		super(info, src);
		this.information = info;
		this.target = target;
	}

	public PostDataListener getTarget() {
		return this.target;
	}

	public String getInformation() {
		return this.information;
	}

	public RetrievalNotifier getSource() {
		return this.source;
	}
}
