package character;

import java.util.ArrayList;

import common_classes.Attribute;
import common_classes.Effect;
import common_classes.Modifier;
import event_classes.EventObject;
import event_classes.EventType;
import event_classes.GenericObservee;
import event_classes.Observer;
import event_classes.Target;

public class BattleManager extends GenericObservee implements Observer {
	protected AttackAttribute chanceToHit;
	
	protected Attribute actionPointsOnMovement;
	protected Attribute damage;
	protected Attribute damageHeadshot;
	protected Attribute fatigueOnMovement;
	protected Attribute hitpointsOverTime;
	protected Attribute visionNight;
	
	protected boolean ignoreAllyDeathMorale;
	protected boolean ignoreInjuryMorale;
	
	protected double survivalChance;
	
	protected Effect bleedTime;
	protected Effect damageMelee;
	protected Effect damageUnarmed;
	protected Effect fatalityChance;
	protected Effect friendlyFire;
	protected Effect injuryChance;
	protected Effect poisonTime;
	protected Effect resolveBeasts;
	protected Effect resolveGreenskin;
	protected Effect resolveUndead;	

	protected ArrayList<Observer> targets;

	public BattleManager(Observer o) {
		this.setUpObservers();
		this.registerObserver(o);

		this.chanceToHit = new AttackAttribute(0);
		this.actionPointsOnMovement = new Attribute(0);
		this.fatigueOnMovement = new Attribute(0);
	}
	
	protected void setUpAttributes() {
		this.actionPointsOnMovement = new Attribute(0);
		this.damage = new Attribute(0);
		this.damageHeadshot = new Attribute(0);
		this.fatigueOnMovement = new Attribute(0);
		this.hitpointsOverTime = new Attribute(0);
		this.visionNight = new Attribute(0);
		
		this.ignoreAllyDeathMorale = false;
		this.ignoreInjuryMorale = false;
		
		this.survivalChance = 33;
	}

	protected void setUpObservers() {
		super.setUpObservers();
		this.targets = new ArrayList<Observer>();
	}
	
	public void startBattle() {
		this.notifyObservers(new EventObject(Target.MORALE, EventType.SETUP, null, null));
	}
	
	public void endBattle() {
		this.notifyObservers(new EventObject(Target.MORALE, EventType.CLOSE, null, null));
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
		this.notifyTargets(new EventObject(Target.ATTRIBUTE, EventType.GET_OTHER, "meleeDefense", this));
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
	
	public void setEffect(Effect e) {
		if (e.getName().equals("Actionpoints_OnMovement")) {
			this.actionPointsOnMovement.addModifier(e.getModifier());
		} else if (e.getName().equals("Bleed_Time")) {
			this.bleedTime = e;
		} else if (e.getName().equals("Damage_Percent")) {
			this.damage.addModifier(e.getModifier());
		} else if (e.getName().equals("Damage_Headshot_Percent")) {
			this.damageHeadshot.addModifier(e.getModifier());
		} else if (e.getName().equals("Damage_Melee_Percent")) {
			this.damageMelee = e;
		} else if (e.getName().equals("Damage_Unarmed_Percent")) {
			this.damageUnarmed = e;
		} else if (e.getName().equals("Fatality_Chance_Percent")) {
			this.fatalityChance = e;
		} else if (e.getName().equals("Fatigue_OnMovement")) {
			this.fatigueOnMovement.addModifier(e.getModifier());
		} else if (e.getName().equals("FriendlyFireChance_Percent")) {
			this.friendlyFire = e;
		} else if (e.getName().equals("Hitpoints_Over_Time")) {
			this.hitpointsOverTime.addModifier(e.getModifier());
		} else if (e.getName().equals("Injury_Chance_Percent")) {
			this.injuryChance = e;
		} else if (e.getName().equals("Injury_Ignore")) {
			//TODO
		} else if (e.getName().equals("Morale_AllyDeath")) {
			this.ignoreAllyDeathMorale = true;
		} else if (e.getName().equals("Morale_Deathwish")) {
			this.ignoreInjuryMorale = true;
		} else if (e.getName().equals("Poison_Time")) {
			this.poisonTime = e;
		} else if (e.getName().equals("RerollChance")) {
			//TODO
		} else if (e.getName().equals("Resolve_Beasts")) {
			this.resolveBeasts = e;
		} else if (e.getName().equals("Resolve_Greenskins")) {
			this.resolveGreenskin = e;
		} else if (e.getName().equals("Resolve_Undead")) {
			this.resolveUndead = e;
		} else if (e.getName().equals("SurvivalChance")) {
			this.survivalChance = e.getValue();
		} else if (e.getName().equals("Vision_Night")) {
			this.visionNight.addModifier(e.getModifier());
		} else if (e.getName().equals("WardogStartingMorale")) {
			//TODO
		}
	}
	
	public void removeEffect(Effect e) {
		if (e.getName().equals("Actionpoints_OnMovement")) {
			this.actionPointsOnMovement.removeModifier(e.getModifier());
		} else if (e.getName().equals("Bleed_Time")) {
			this.bleedTime = null;
		} else if (e.getName().equals("Damage_Percent")) {
			this.damage.removeModifier(e.getModifier());
		} else if (e.getName().equals("Damage_Headshot_Percent")) {
			this.damageHeadshot.removeModifier(e.getModifier());
		} else if (e.getName().equals("Damage_Melee_Percent")) {
			this.damageMelee = null;
		} else if (e.getName().equals("Damage_Unarmed_Percent")) {
			this.damageUnarmed = null;
		} else if (e.getName().equals("Fatality_Chance_Percent")) {
			this.fatalityChance = null;
		} else if (e.getName().equals("Fatigue_OnMovement")) {
			this.fatigueOnMovement.removeModifier(e.getModifier());
		} else if (e.getName().equals("FriendlyFireChance_Percent")) {
			this.friendlyFire = null;
		} else if (e.getName().equals("Hitpoints_Over_Time")) {
			this.hitpointsOverTime.removeModifier(e.getModifier());
		} else if (e.getName().equals("Injury_Chance_Percent")) {
			this.injuryChance = null;
		} else if (e.getName().equals("Injury_Ignore")) {
			//TODO
		} else if (e.getName().equals("Morale_AllyDeath")) {
			this.ignoreAllyDeathMorale = false;
		} else if (e.getName().equals("Morale_Deathwish")) {
			this.ignoreInjuryMorale = false;
		} else if (e.getName().equals("Poison_Time")) {
			this.poisonTime = null;
		} else if (e.getName().equals("RerollChance")) {
			//TODO
		} else if (e.getName().equals("Resolve_Beasts")) {
			this.resolveBeasts = null;
		} else if (e.getName().equals("Resolve_Greenskins")) {
			this.resolveGreenskin = null;
		} else if (e.getName().equals("Resolve_Undead")) {
			this.resolveUndead = null;
		} else if (e.getName().equals("SurvivalChance")) {
			this.survivalChance = 33;
		} else if (e.getName().equals("Vision_Night")) {
			this.visionNight.addModifier(e.getModifier());
		} else if (e.getName().equals("WardogStartingMorale")) {
			//TODO
		}
	}

	public void registerTarget(Observer o) {
		this.targets.add(o);
	}

	public void removeTarget(Observer o) {
		this.targets.remove(o);
	}

	public void notifyTargets(EventObject information) {
		for (Observer o : targets) {
			o.onEventHappening(information);
		}
	}

	@Override
	public void onEventHappening(EventObject information) {
		switch (information.getTask()) {
		case ADD:
			this.setEffect((Effect) information.getInformation());
			break;
		case REMOVE:
			this.removeEffect((Effect) information.getInformation());
			break;
		case GOT:
			this.applyMeleeSkill((double) information.getInformation());
			break;
		case GOT_OTHER:
			this.applyTargetMeleeDefense((double) information.getInformation());
			break;
		default:
			break;
		}
	}
}
