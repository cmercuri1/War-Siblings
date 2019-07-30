/** War Siblings
 * Observer interface
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

/** An interface that helps with Event Handling through receiving of EventObjects */
public interface Observer {
	/**
	 * onEventHappening: the class that implements this handles how it acts when
	 * told that an Event has happened
	 */
	void onEventHappening(EventObject event);
}
