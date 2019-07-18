package event_classes;

public interface ObserveeSpecific<T extends EventObject> {
	
	void registerObserver(Observer o);
	
	void removeObserver(Observer o);
	
	void notifyObservers(T information);
	
	void notifyObserver(Observer o, T information);
	
}
