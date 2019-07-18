package event_classes;

public interface ObserverObject extends Observer {
	void onEventHappening(EventObject information);
}