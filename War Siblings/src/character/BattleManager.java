package character;

import java.util.ArrayList;

import common_classes.Attribute;
import common_classes.Modifier;
import event_classes.EventObject;
import event_classes.EventType;
import event_classes.GenericObservee;
import event_classes.Observer;
import event_classes.Target;

public class BattleManager extends GenericObservee implements Observer {
	protected Attribute chanceToHit;
	
	protected ArrayList<Observer> targets;

	public BattleManager(Observer o) {
		this.setUpObservers();
		this.registerObserver(o);
		
		this.chanceToHit = new Attribute(0);
	}
	
	protected void setUpObservers() {
		super.setUpObservers();
		this.targets = new ArrayList<Observer>();
	}
	
	public void setUpChanceToHit() {
		this.getMeleeSkill();
		this.getTargetMeleeDefense();
		this.getOtherModifiers();
	}
	
	private void getMeleeSkill() {
		this.notifyObservers(new EventObject(Target.ATTRIBUTE, EventType.GET, "meleeSkill", this));
	}
	
	private void getTargetMeleeDefense() {
		this.notifyTargets(new EventObject(Target.ATTRIBUTE, EventType.GET, "meleeDefense", this));
	}
	
	private void getOtherModifiers() {
		// TODO
		this.notifyObservers(null);
	}

	public void applyMeleeSkill(double value) {
		this.chanceToHit.addModifier(new Modifier("Melee Skill", value, false, true, true));
	}
	
	public void applyTargetMeleeDefense(double value) {
		this.chanceToHit.addModifier(new Modifier("Target Melee Defense", -value, false, true, true));
	}
	
	public void applyModifier(Modifier mod) {
		this.chanceToHit.addModifier(mod);
	}
	
	public void registerTarget(Observer o) {
		this.targets.add(o);
	}
	
	public void removeTarget(Observer o) {
		this.targets.remove(o);
	}
	
	public void notifyTargets(EventObject information) {
		for (Observer o: targets) {
			o.onEventHappening(information);
		}
	}

	@Override
	public void onEventHappening(EventObject information) {
		// TODO Auto-generated method stub
		
	}
}
