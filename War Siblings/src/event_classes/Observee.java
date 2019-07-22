/** War Siblings
 * Observee interface
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

/**
 * An interface that helps with Event Handling through informing Observers of
 * EventObjects
 */
public interface Observee {

	void registerObserver(Observer o);

	void removeObserver(Observer o);

	void notifyObservers(EventObject information);

	void notifyObserver(Observer o, EventObject information);

}
