package event_classes;

import unused.EventAbility;

public interface ObserverAbility extends Observer {
	void onEventHappening(EventAbility event);
}
