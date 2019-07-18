package event_classes;

public interface Observee {

	void registerObserver(Observer o);

	void removeObserver(Observer o);

	void notifyObservers(EventObject information);
	
	void notifyObserver(Observer o, EventObject information);

}
