package common_classes;

import event_classes.EventObject;

public interface Observer {
	void onEventHappening(EventObject information);
}
