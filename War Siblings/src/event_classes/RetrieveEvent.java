/** War Siblings
 * RetrieveEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import listener_interfaces.PostDataListener;

public class RetrieveEvent extends InfoEvent {
	protected String information;
	protected PostDataListener source;
	
	public RetrieveEvent(String info, PostDataListener src) {
		super(info, src);
	}

	public String getInformation() {
		return this.information;
	}
	
	public PostDataListener getSource() {
		return this.source;
	}
}
