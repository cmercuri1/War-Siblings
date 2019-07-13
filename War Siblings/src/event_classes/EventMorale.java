package event_classes;

import common_classes.MoraleState;

public class EventMorale extends EventObject {
	protected MoraleState[] information;
	
	public EventMorale(String todo, MoraleState[] information) {
		this.task = todo;
		this.information = information;
	}
	
	public MoraleState[] getInformation() {
		return this.information;
	}
}
