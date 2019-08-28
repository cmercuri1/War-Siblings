/** War Siblings
 * BattleManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import storage_classes.ArrayList;
import storage_classes.BattleConditions;
import event_classes.MoraleRollOutcomeEvent;
import event_classes.AbilityEvent;
import event_classes.BattleControlEvent;
import event_classes.CombatEvent;
import event_classes.RoundControlEvent;
import event_classes.TurnControlEvent;
import listener_interfaces.MoraleRollOutcomeListener;
import listener_interfaces.AbilityListener;
import listener_interfaces.BattleControlListener;
import listener_interfaces.CombatListener;
import listener_interfaces.RoundControlListener;
import listener_interfaces.TurnControlListener;
import notifier_interfaces.AbilityNotifier;
import notifier_interfaces.BattleControlNotifier;
import notifier_interfaces.CombatNotifier;
import notifier_interfaces.MultiNotifier;
import notifier_interfaces.RoundControlNotifier;
import notifier_interfaces.TurnControlNotifier;

/**
 * A manager class that handles actions and consequences of battle. This acts as
 * the gatekeeper to the rest of the character for any interactions
 */
public class EventManager implements BattleControlListener, MoraleRollOutcomeListener, MultiNotifier, AbilityNotifier,
		BattleControlNotifier, CombatNotifier, TurnControlNotifier, RoundControlNotifier {

	protected ArrayList<AbilityListener> abilityListeners;
	protected ArrayList<CombatListener> combatListeners;
	protected ArrayList<BattleControlListener> battleControlListeners;
	protected ArrayList<RoundControlListener> roundControlListeners;
	protected ArrayList<TurnControlListener> turnControlListeners;

	public EventManager() {
		this.setUpListeners();
	}

	@Override
	public void setUpListeners() {
		this.abilityListeners = new ArrayList<AbilityListener>();
		this.combatListeners = new ArrayList<CombatListener>();
		this.battleControlListeners = new ArrayList<BattleControlListener>();
		this.roundControlListeners = new ArrayList<RoundControlListener>();
		this.turnControlListeners = new ArrayList<TurnControlListener>();
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
	public void onMoraleRollOutcomeEvent(MoraleRollOutcomeEvent m) {
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

	public void startBattle(BattleConditions bc) {
		this.notifyBattleControlListeners(new BattleControlEvent(BattleControlEvent.Task.START_BATTLE, bc, this));
	}

	public void startRound() {
		this.notifyRoundControlListeners(new RoundControlEvent(RoundControlEvent.Task.START_ROUND, null, this));
	}

	public void startTurn() {
		this.notifyTurnControlListeners(new TurnControlEvent(TurnControlEvent.Task.START_TURN, null, this));
	}

	public void endBattle(BattleConditions bc) {
		this.notifyBattleControlListeners(new BattleControlEvent(BattleControlEvent.Task.END_BATTLE, bc, this));
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
	public void addAbilityListener(AbilityListener a) {
		this.abilityListeners.add(a);
	}

	@Override
	public void removeAbilityListener(AbilityListener a) {
		this.abilityListeners.remove(a);
	}

	@Override
	public void notifyAbilityListeners(AbilityEvent a) {
		this.abilityListeners.forEach(l -> l.onAbilityEvent(a));
	}

	@Override
	public void notifyAbilityListener(AbilityListener a, AbilityEvent e) {
		this.abilityListeners.get(a).onAbilityEvent(e);
	}
}
