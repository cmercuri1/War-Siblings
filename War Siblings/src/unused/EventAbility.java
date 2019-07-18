package unused;

import common_classes.Ability;
import event_classes.EventObject;

public class EventAbility {
	protected EventAbilityType task;
	protected Ability information;

	public EventAbility(EventAbilityType todo, Ability information) {
		this.task = todo;
		this.information = information;
	}
	
	public EventAbilityType getTask() {
		return this.task;
	}
	
	public Ability getInformation() {
		return this.information;
	}

}
