package common_classes;

import java.util.ArrayList;

import event_classes.EventObject;

public abstract class Observeree {
	protected ArrayList<Observer> observers;

	protected void setUpObservers() {
		this.observers = new ArrayList<Observer>();
	}

	public void registerObserver(Observer o) {
		this.observers.add(o);
	}

	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}

	public void notifyObservers(EventObject information) {
		for (Observer o : this.observers) {
			o.onEventHappening(information);
		}
	}
}
