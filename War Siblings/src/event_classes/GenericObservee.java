package event_classes;

import java.util.ArrayList;

/**
 * Abstract class for allowing management by an overall Manager, generally the
 * Character class that creates the instance of this class
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
		for (Observer ob: this.observerObjects) {
			if (ob.equals(o)) {
				ob.onEventHappening(information);
			}
		}
	}
}
