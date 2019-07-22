/** War Siblings
 * GenericObservee class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import java.util.ArrayList;

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

	public void notifyObservers(EventObject information) {
		for (Observer o : this.observerObjects) {
			o.onEventHappening(information);
		}
	}

	public void notifyObserver(Observer o, EventObject information) {
		for (Observer ob : this.observerObjects) {
			if (ob.equals(o)) {
				ob.onEventHappening(information);
			}
		}
	}
}
