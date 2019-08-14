/** War Siblings
 * MoraleManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import event_classes.AbilityEvent;
import event_classes.BattleControlEvent;
import event_classes.EffectEvent;
import event_classes.MoraleRollEvent;
import event_classes.PostDataEvent;
import event_classes.RetrieveEvent;
import global_managers.GlobalManager;
import listener_interfaces.AbilityListener;
import listener_interfaces.BattleControlListener;
import listener_interfaces.EffectListener;
import listener_interfaces.MoraleRollListener;
import listener_interfaces.PostDataListener;
import listener_interfaces.RetrievalListener;
import notifier_interfaces.AbilityNotifier;
import notifier_interfaces.BattleControlNotifier;
import notifier_interfaces.MultiNotifier;
import notifier_interfaces.RetrievalNotifier;
import storage_classes.ArrayList;
import storage_classes.Effect;
import storage_classes.MoodAttribute;
import storage_classes.MoraleState;

/**
 * A class for managing the morale state of a character, along with any changes
 * that has on the rest of the character
 */
public class MoraleManager implements EffectListener, PostDataListener, MoraleRollListener, BattleControlListener,
		BattleControlNotifier, RetrievalNotifier, AbilityNotifier, MultiNotifier {
	protected static double DEFAULTMORALE = 6;

	protected MoraleState currentMorale;

	protected MoodAttribute mood;

	protected double startingMorale;
	protected double optimistModifier;
	protected double pessimistModifier;
	protected double specialModifier;
	protected double currentResolve;

	protected boolean isIrrational;
	protected boolean isInsecure;
	protected boolean isDetermined;

	protected ArrayList<AbilityListener> abilityListeners;
	protected ArrayList<BattleControlListener> battleControlListeners;
	protected ArrayList<RetrievalListener> retrievalListeners;

	public MoraleManager() {
		this.mood = new MoodAttribute(50);

		this.startingMorale = DEFAULTMORALE;
		this.optimistModifier = 0;
		this.pessimistModifier = 0;
		this.specialModifier = 0;

		this.isIrrational = false;
		this.isInsecure = false;
		this.isDetermined = false;
		this.removeMorale();

		this.setUpListeners();
	}

	public MoraleState getCurrentState() {
		return this.currentMorale;
	}

	/** Needs to be called after abilities are set up */
	protected void setMorale() {
		double temp;
		double chanceRoll;

		if ((this.mood.getCurrentMood().getBestMoraleState() == MoraleState.CONFIDENT.getValue())
				&& (this.isDetermined)) {
			chanceRoll = 100;
		} else {
			chanceRoll = this.mood.getCurrentMood().getBestMoralechance();
		}

		if (GlobalManager.d100Roll() > chanceRoll) {
			temp = this.mood.getCurrentMood().getBestMoraleState() - 1;
		} else {
			temp = this.mood.getCurrentMood().getBestMoraleState();
		}

		if (temp > this.startingMorale) {
			this.changeState(MoraleState.valueOfMoraleValue((int) this.startingMorale));
		} else {
			this.changeState(MoraleState.valueOfMoraleValue((int) temp));
		}
	}

	protected void removeMorale() {
		this.changeState(MoraleState.STEADY);
	}

	protected void makePositiveCheck(double additionalModifier) {
		if (this.makeCheck(this.optimistModifier + additionalModifier)) {
			if (this.currentMorale.getValue() < MoraleState.CONFIDENT.getValue()) {
				if (!(this.currentMorale.equals(MoraleState.STEADY) && this.isInsecure)) {
					this.changeState(MoraleState.valueOfMoraleValue(currentMorale.getValue() + 1));
				}
			}
		}
	}

	protected void makeNegativeCheck(double additionalModifier) {
		if (!this.makeCheck(this.pessimistModifier + additionalModifier)) {
			if (this.currentMorale.getValue() > MoraleState.FLEEING.getValue()) {
				this.changeState(MoraleState.valueOfMoraleValue(currentMorale.getValue() - 1));
			}
		}
	}

	protected void makeSpecialCheck(double additionalModifier) {
		if (!this.makeCheck(this.specialModifier + this.pessimistModifier + additionalModifier)) {
			// TODO
		}
	}

	protected boolean makeCheck(Double modifier) {
		this.getResolve();
		double temp = 0;

		if (this.isIrrational) {
			if (GlobalManager.d100Roll() <= 50) {
				temp = -10;
			} else {
				temp = 10;
			}
		}

		int roll = GlobalManager.d100Roll();

		if (roll > (this.currentResolve += temp + modifier)) {
			System.out.println("Failed: rolled: " + roll + ", needed: " + this.currentResolve);
			return false;
		} else {
			System.out.println("Success: rolled: " + roll + ", needed: " + this.currentResolve);
			return true;
		}
	}

	protected void getResolve() {
		this.notifyRetrievalListeners(new RetrieveEvent(this, "resolve", this));
	}

	protected void setResolve(double resolve) {
		this.currentResolve = resolve;
	}

	protected void changeMood(Effect e) {
		this.mood.addModifier(e.getModifier());
	}

	protected void setEffect(Effect e) {
		if (e.getName().equals("Morale_Optimist")) {
			this.optimistModifier = e.getValue();
		} else if (e.getName().equals("Morale_Pessimist")) {
			this.pessimistModifier = e.getValue();
		} else if (e.getName().equals("Morale_Special")) {
			this.specialModifier = e.getValue();
		} else if (e.getName().equals("Morale_Irrational")) {
			this.isIrrational = true;
		} else if (e.getName().equals("Morale_Insecure")) {
			this.isInsecure = true;
		} else if (e.getName().equals("Morale_Determined")) {
			this.isDetermined = true;
		} else if (e.getName().equals("Morale_Starting")) {
			this.startingMorale = e.getValue();
		}
	}

	protected void removeEffect(Effect e) {
		if (e.getName().equals("Morale_Optimist")) {
			this.optimistModifier = 0;
		} else if (e.getName().equals("Morale_Pessimist")) {
			this.pessimistModifier = 0;
		} else if (e.getName().equals("Morale_Special")) {
			this.specialModifier = 0;
		} else if (e.getName().equals("Morale_Irrational")) {
			this.isIrrational = false;
		} else if (e.getName().equals("Morale_Insecure")) {
			this.isInsecure = false;
		} else if (e.getName().equals("Morale_Determined")) {
			this.isDetermined = false;
		} else if (e.getName().equals("Morale_Starting")) {
			this.startingMorale = DEFAULTMORALE;
		}
	}

	protected void changeState(MoraleState state) {
		try {
			this.notifyAbilityListeners(new AbilityEvent(AbilityEvent.Task.REMOVE,
					GlobalManager.morale.getMoraleAbility(this.currentMorale), this));
		} catch (NullPointerException nu) {

		}
		try {
			this.notifyAbilityListeners(new AbilityEvent(AbilityEvent.Task.ADD,
					GlobalManager.morale.getMoraleAbility(this.currentMorale), this));
		} catch (NullPointerException nu) {
		}

		this.currentMorale = state;
	}

	public void display() {
		System.out.println("This character's mood is currently " + this.mood.getCurrentMood().toString());
		System.out.println("This character is currently: " + this.currentMorale);
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
	public void onEffectEvent(EffectEvent e) {
		switch (e.getTask()) {
		case ADD:
			this.setEffect(e.getInformation());
			break;
		case REMOVE:
			this.removeEffect(e.getInformation());
			break;
		}
	}

	@Override
	public void setUpListeners() {
		this.abilityListeners = new ArrayList<AbilityListener>();
		this.battleControlListeners = new ArrayList<BattleControlListener>();
		this.retrievalListeners = new ArrayList<RetrievalListener>();
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
		this.battleControlListeners.forEach(l -> l.onBattleControlEvent(e));
	}

	@Override
	public void onPostDataEvent(PostDataEvent p) {
		switch (p.getTask()) {
		case GOT:
			if (p.getRequestedInfo().equals("resolve"))
				this.setResolve((double) p.getInformation());
			break;
		case GOT_OTHER:
			break;
		}
	}

	@Override
	public void onBattleControlEvent(BattleControlEvent b) {
		switch (b.getTask()) {
		case END_BATTLE:
			this.removeMorale();
			break;
		case START_BATTLE:
			this.setMorale();
			break;
		}
	}

	@Override
	public void onMoraleRollEvent(MoraleRollEvent m) {
		switch (m.getTask()) {
		case ROLL_NEGATIVE:
			break;
		case ROLL_POSITIVE:
			break;
		case ROLL_SPECIAL:
			break;
		}
	}
}
