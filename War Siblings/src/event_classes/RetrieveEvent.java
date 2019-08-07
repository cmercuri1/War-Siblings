/** War Siblings
 * RetrieveEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class RetrieveEvent extends InfoEvent {
	protected String information;

	public RetrieveEvent(String info, Object src) {
		super(info, src);
	}

	public String getInformation() {
		return this.information;
	}
}
