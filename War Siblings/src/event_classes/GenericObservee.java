/** War Siblings
 * GenericObservee class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import storage_classes.ArrayList;

/**
 * Abstract class that implements the Observee interface. Most Managers extend
 * this class to allow them to be Observed
 */
public abstract class GenericObservee implements Observee {
	protected ArrayList<Observer> observerObjects;

	protected void setUpObservers() {
		this.observerObjects = new ArrayList<Observer>();
	}

	public void registerObserver(Observer o) {
		this.observerObjects.add(o);
	}

	public void removeObserver(Observer o) {
		this.observerObjects.remove(o);
	}

	public void notifyObservers(EventObject event) {
		this.observerObjects.forEach(o -> o.onEventHappening(event));
	}

	public void notifyObserver(Observer o, EventObject event) {
		for (Observer ob : this.observerObjects) {
			if (ob.equals(o)) {
				ob.onEventHappening(event);
			}
		}
	}
}
