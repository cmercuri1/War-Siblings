package common_classes;

public class EventAbility extends EventObject {
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
