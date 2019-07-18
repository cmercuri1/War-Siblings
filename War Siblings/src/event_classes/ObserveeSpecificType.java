package event_classes;

import java.util.ArrayList;

public class ObserveeSpecificType<T extends EventObject> implements ObserveeSpecific<T> {
	protected ArrayList<Observer> observerObjects;

	protected void setUpObservers() {
		this.observerObjects = new ArrayList<Observer>();
	}

	@Override
	public void registerObserver(Observer o) {
		this.observerObjects.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		this.observerObjects.remove(o);
	}

	@Override
	public void notifyObservers(T information) {
		for (Observer o : this.observerObjects) {
			o.onEventHappening((T) information);
		}
	}

	@Override
	public void notifyObserver(Observer o, T information) {
		for (Observer ob: this.observerObjects) {
			if (ob.equals(o)) {
				ob.onEventHappening((T) information);
			}
		}
	}

}
