/** War Siblings
 * CarrierEvent class
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public abstract class InfoEvent extends Event {
	protected Object information;

	public InfoEvent(Object info, Object src) {
		super(src);
		this.information = info;
	}

	public Object getInformation() {
		return this.information;
	}

}
