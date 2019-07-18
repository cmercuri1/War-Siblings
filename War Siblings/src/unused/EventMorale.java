package unused;

import common_classes.MoraleState;

public class EventMorale {
	protected MoraleState[] information;
	private String task;
	
	public EventMorale(String todo, MoraleState[] information) {
		this.task = todo;
		this.information = information;
	}
	
	public MoraleState[] getInformation() {
		return this.information;
	}
}
