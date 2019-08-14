/** War Siblings
 * BattleManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import storage_classes.ArrayList;
import event_classes.EffectEvent;
import event_classes.MoraleEvent;
import event_classes.BattleControlEvent;
import event_classes.CombatEvent;
import event_classes.PostDataEvent;
import event_classes.RetrieveEvent;
import event_classes.RoundControlEvent;
import event_classes.TurnControlEvent;
import global_managers.GlobalManager;
import listener_interfaces.EffectListener;
import listener_interfaces.MoraleListener;
import listener_interfaces.BattleControlListener;
import listener_interfaces.CombatListener;
import listener_interfaces.PostDataListener;
import listener_interfaces.RetrievalListener;
import listener_interfaces.RoundControlListener;
import listener_interfaces.TurnControlListener;
import notifier_interfaces.BattleControlNotifier;
import notifier_interfaces.CombatNotifier;
import notifier_interfaces.EffectNotifier;
import notifier_interfaces.MultiNotifier;
import notifier_interfaces.RetrievalNotifier;
import notifier_interfaces.RoundControlNotifier;
import notifier_interfaces.TurnControlNotifier;
import storage_classes.AttackAttribute;
import storage_classes.Attribute;
import storage_classes.Effect;
import storage_classes.Modifier;

/**
 * A manager class that handles actions and consequences of battle. This acts as
 * the gatekeeper to the rest of the character for any interactions
 */
public class BattleManager
		implements EffectListener, BattleControlListener, MoraleListener, BattleControlNotifier, RetrievalNotifier,
		PostDataListener, CombatNotifier, MultiNotifier, TurnControlNotifier, RoundControlNotifier, EffectNotifier {
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

	protected ArrayList<BattleControlListener> battleControlListeners;
	protected ArrayList<RetrievalListener> retrievalListeners;
	protected ArrayList<CombatListener> combatListeners;
	protected ArrayList<TurnControlListener> turnControlListeners;
	protected ArrayList<RoundControlListener> roundControlListeners;
	protected ArrayList<EffectListener> effectListeners;

	public BattleManager() {
		this.setUpListeners();
		this.setUpAttributes();
	}

	@Override
	public void setUpListeners() {
		this.battleControlListeners = new ArrayList<BattleControlListener>();
		this.combatListeners = new ArrayList<CombatListener>();
		this.retrievalListeners = new ArrayList<RetrievalListener>();
		this.turnControlListeners = new ArrayList<TurnControlListener>();
		this.roundControlListeners = new ArrayList<RoundControlListener>();
		this.effectListeners = new ArrayList<EffectListener>();
	}

	protected void setUpAttributes() {
		this.chanceToHit = new AttackAttribute(0);
		this.actionPointsOnMovement = new Attribute(0);
		this.fatigueOnMovement = new Attribute(0);
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

	public void startBattle(ArrayList<String> enemies) {
		try {
			this.foeCheck(EffectEvent.Task.ADD, enemies);
		} catch (NullPointerException nu) {

		}
		this.notifyBattleControlListeners(new BattleControlEvent(BattleControlEvent.Task.START_BATTLE, enemies, this));
	}

	public void startRound() {
		this.notifyRetrievalListeners(new RetrieveEvent(this, "initiative", this));
	}

	protected void sendInitiative(double value) {
		// POST INITIATIVE TO RIGHT LISTENER?
	}

	public void startTurn() {
		this.notifyTurnControlListeners(new TurnControlEvent(TurnControlEvent.Task.START_TURN, null, this));
	}

	public void endBattle(ArrayList<String> enemies) {
		this.notifyBattleControlListeners(new BattleControlEvent(BattleControlEvent.Task.END_BATTLE, enemies, this));
		try {
			this.foeCheck(EffectEvent.Task.REMOVE, enemies);
		} catch (NullPointerException nu) {

		}
	}

	protected void foeCheck(EffectEvent.Task task, ArrayList<String> enemies) {
		if (enemies.contains("Beasts")) {
			this.notifyEffectListeners(
					new EffectEvent(task, new Effect("Resolve", this.resolveBeasts.getValue()), this));
		}
		if (enemies.contains("Greenskins")) {
			this.notifyEffectListeners(
					new EffectEvent(task, new Effect("Resolve", this.resolveGreenskin.getValue()), this));
		}
		if (enemies.contains("Undead")) {
			this.notifyEffectListeners(
					new EffectEvent(task, new Effect("Resolve", this.resolveUndead.getValue()), this));
		}
	}

	public void getChanceToHit() {
		this.getMeleeSkill();
		this.getTargetMeleeDefense();
		this.getOtherModifiers();
	}

	protected void getMeleeSkill() {
		this.notifyRetrievalListeners(new RetrieveEvent(this, "meleeSkill", this));
	}

	protected void getTargetMeleeDefense() {
		this.notifyRetrievalListeners(new RetrieveEvent(this, "initiative", this));
	}

	protected void getOtherModifiers() {
		// TODO
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

	public void attack() {
		double roll = GlobalManager.d100Roll();
		this.getChanceToHit();

		if (roll <= this.chanceToHit.getAlteredValue()) {
			System.out.println("Succeeded: rolled: " + roll + ", needed: " + this.chanceToHit.getAlteredValue());
			this.notifyCombatListeners(new CombatEvent(CombatEvent.Task.HIT, roll, this));
		} else {
			System.out.println("Failed: rolled: " + roll + ", needed: " + this.chanceToHit.getAlteredValue());
			this.notifyCombatListeners(new CombatEvent(CombatEvent.Task.MISS, roll, this));
		}
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
			// TODO
		} else if (e.getName().equals("Morale_AllyDeath")) {
			this.ignoreAllyDeathMorale = true;
		} else if (e.getName().equals("Morale_Deathwish")) {
			this.ignoreInjuryMorale = true;
		} else if (e.getName().equals("Poison_Time")) {
			this.poisonTime = e;
		} else if (e.getName().equals("RerollChance")) {
			// TODO
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
			// TODO
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
			// TODO
		} else if (e.getName().equals("Morale_AllyDeath")) {
			this.ignoreAllyDeathMorale = false;
		} else if (e.getName().equals("Morale_Deathwish")) {
			this.ignoreInjuryMorale = false;
		} else if (e.getName().equals("Poison_Time")) {
			this.poisonTime = null;
		} else if (e.getName().equals("RerollChance")) {
			// TODO
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
			// TODO
		}
	}

	@Override
	public void addCombatListener(CombatListener c) {
		this.combatListeners.add(c);
	}

	@Override
	public void removeCombatListener(CombatListener c) {
		this.combatListeners.remove(c);
	}

	@Override
	public void notifyCombatListeners(CombatEvent c) {
		this.combatListeners.forEach(l -> l.onCombatEvent(c));
	}

	@Override
	public void notifyCombatListener(CombatListener c, CombatEvent e) {
		this.combatListeners.get(c).onCombatEvent(e);
	}

	@Override
	public void onPostDataEvent(PostDataEvent p) {
		switch (p.getTask()) {
		case GOT:
			if (p.getRequestedInfo().equals("meleeSkill")) {
				this.applyMeleeSkill((double) p.getInformation());
			} else if (p.getRequestedInfo().equals("initiative")) {
				this.sendInitiative((double) p.getInformation());
			}
			break;
		case GOT_OTHER:
			if (p.getRequestedInfo().equals("meleeDefense"))
				this.applyTargetMeleeDefense((double) p.getInformation());
			break;
		}
	}

	@Override
	public void addRetrievalListener(RetrievalListener r) {
		this.retrievalListeners.add(r);
	}

	@Override
	public void removeRetrievalListener(RetrievalListener r) {
		this.retrievalListeners.remove(r);
	}

	@Override
	public void notifyRetrievalListeners(RetrieveEvent r) {
		this.retrievalListeners.forEach(l -> l.onRetrieveEvent(r));
	}

	@Override
	public void notifyRetrievalListener(RetrievalListener r, RetrieveEvent e) {
		this.retrievalListeners.get(r).onRetrieveEvent(e);
	}

	@Override
	public void addBattleControlListener(BattleControlListener b) {
		this.battleControlListeners.add(b);
	}

	@Override
	public void removeBattleControlListener(BattleControlListener b) {
		this.battleControlListeners.remove(b);
	}

	@Override
	public void notifyBattleControlListeners(BattleControlEvent b) {
		this.battleControlListeners.forEach(l -> l.onBattleControlEvent(b));
	}

	@Override
	public void notifyBattleControlListener(BattleControlListener b, BattleControlEvent e) {
		this.battleControlListeners.get(b).onBattleControlEvent(e);
	}

	@Override
	public void onEffectEvent(EffectEvent a) {
		if (a.getInformation().getAffectedManager().equals("Battle"))
			switch (a.getTask()) {
			case ADD:
				this.setEffect(a.getInformation());
				break;
			case REMOVE:
				this.removeEffect(a.getInformation());
				break;
			}
	}

	@Override
	public void onBattleControlEvent(BattleControlEvent b) {
		switch (b.getTask()) {
		case END_BATTLE:
			this.endBattle(b.getInformation());
			break;
		case START_BATTLE:
			this.startBattle(b.getInformation());
			break;
		}
	}

	@Override
	public void addRoundControlListener(RoundControlListener r) {
		this.roundControlListeners.add(r);
	}

	@Override
	public void removeRoundControlListener(RoundControlListener r) {
		this.roundControlListeners.remove(r);
	}

	@Override
	public void notifyRoundControlListeners(RoundControlEvent r) {
		this.roundControlListeners.forEach(l -> l.onRoundControlEvent(r));
	}

	@Override
	public void notifyRoundControlListener(RoundControlListener r, RoundControlEvent e) {
		this.roundControlListeners.get(r).onRoundControlEvent(e);
	}

	@Override
	public void addTurnControlListener(TurnControlListener t) {
		this.turnControlListeners.add(t);
	}

	@Override
	public void removeTurnControlListener(TurnControlListener t) {
		this.turnControlListeners.remove(t);
	}

	@Override
	public void notifyTurnControlListeners(TurnControlEvent t) {
		this.turnControlListeners.forEach(l -> l.onTurnControlEvent(t));
	}

	@Override
	public void notifyTurnControlListener(TurnControlListener t, TurnControlEvent e) {
		this.turnControlListeners.get(t).onTurnControlEvent(e);
	}

	@Override
	public void onMoraleEvent(MoraleEvent m) {
		switch (m.getTask()) {
		case NEGATIVE_ROLL_FAIL:
			break;
		case NEGATIVE_ROLL_SUCCESS:
			break;
		case POSITIVE_ROLL_FAIL:
			break;
		case POSITIVE_ROLL_SUCCESS:
			break;
		case SPECIAL_ROLL_FAIL:
			break;
		case SPECIAL_ROLL_SUCCESS:
			break;
		}
	}

	@Override
	public void addEffectListener(EffectListener e) {
		this.effectListeners.add(e);
	}

	@Override
	public void removeEffectListener(EffectListener e) {
		this.effectListeners.remove(e);
	}

	@Override
	public void notifyEffectListeners(EffectEvent e) {
		this.effectListeners.forEach(l -> l.onEffectEvent(e));
	}

	@Override
	public void notifyEffectListener(EffectListener l, EffectEvent e) {
		this.effectListeners.get(l).onEffectEvent(e);
	}
}
