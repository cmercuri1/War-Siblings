/** War Siblings
 * Event class
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public abstract class Event {
	protected Object source;

	public Event(Object src) {
		this.source = src;
	}

	public Object getSource() {
		return this.source;
	}
}
