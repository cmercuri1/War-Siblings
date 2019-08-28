/** War Siblings
 * AttributeManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import java.lang.reflect.Field;

import effect_classes.Modifier;
import event_classes.AttributeEvent;
import event_classes.SkillPreferenceEvent;
import event_classes.LevelUpAttributeEvent;
import event_classes.ModifierEvent;
import event_classes.MoraleChangeEvent;
import event_classes.MoraleRollOutcomeEvent;
import event_classes.MoraleRollEvent;
import event_classes.MultiValueAttributeEvent;
import event_classes.PostDataEvent;
import event_classes.RetrieveEvent;
import event_classes.RoundControlEvent;
import event_classes.StarAttributeEvent;
import event_classes.TurnControlEvent;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;
import listener_interfaces.AttributeListener;
import listener_interfaces.SkillPreferenceListener;
import listener_interfaces.LevelUpAttributeListener;
import listener_interfaces.ModifierListener;
import listener_interfaces.MoraleChangeListener;
import listener_interfaces.MoraleRollOutcomeListener;
import listener_interfaces.MoraleRollListener;
import listener_interfaces.MultiValueAttributeListener;
import listener_interfaces.PostDataListener;
import listener_interfaces.RetrievalListener;
import listener_interfaces.RoundControlListener;
import listener_interfaces.TurnControlListener;
import listener_interfaces.StarAttributeListener;
import notifier_interfaces.PostDataNotifier;
import notifier_interfaces.SkillPreferenceNotifier;
import notifier_interfaces.MoraleChangeNotifier;
import notifier_interfaces.MoraleRollNotifier;
import notifier_interfaces.MoraleRollOutcomeNotifier;
import notifier_interfaces.MultiNotifier;
import storage_classes.ArrayList;
import storage_classes.Attribute;
import storage_classes.BarAttribute;
import storage_classes.DefenseAttribute;
import storage_classes.FatigueAttribute;
import storage_classes.HitpointAttribute;
import storage_classes.LevelAttribute;
import storage_classes.LevelUp;
import storage_classes.MoodAttribute;
import storage_classes.MoraleState;
import storage_classes.StarAttribute;
import storage_classes.VisionAttribute;
import storage_classes.WageAttribute;

/**
 * A class that manages all the attributes and makes sure they operate correctly
 */
public class AttributeManager implements MultiNotifier, AttributeListener, ModifierListener, LevelUpAttributeListener,
		MultiValueAttributeListener, RetrievalListener, TurnControlListener, StarAttributeListener, MoraleRollListener,
		MoraleChangeListener, RoundControlListener, PostDataNotifier, SkillPreferenceNotifier,
		MoraleRollOutcomeNotifier, MoraleChangeNotifier, MoraleRollNotifier {
	// Visible Character Attributes
	protected HitpointAttribute hitpoints;
	protected FatigueAttribute fatigue;
	protected StarAttribute resolve;
	protected StarAttribute initiative;
	protected StarAttribute meleeSkill;
	protected StarAttribute rangedSkill;
	protected DefenseAttribute meleeDefense;
	protected DefenseAttribute rangedDefense;

	protected MoodAttribute mood;
	protected MoraleState currentMorale;
	protected VisionAttribute vision;
	protected BarAttribute actionPoints;
	protected Attribute headshotChance;

	// "Hidden" Character Attributes
	protected WageAttribute wage;
	protected Attribute foodConsumption;
	protected Attribute xpRate;
	protected LevelAttribute level;
	protected Attribute fatigueRecovery;
	protected Attribute damage;
	protected Attribute armorDamage;
	protected Attribute ignoreArmor;
	protected Attribute damageHeadshot;
	protected Attribute survivalChance;
	protected Attribute inflictInjury;
	protected Attribute injuryThreshold;

	protected ArrayList<ArrayList<LevelUp>> levelUps;

	protected ArrayList<PostDataListener> postDataListeners;
	protected ArrayList<SkillPreferenceListener> charInventoryListeners;
	protected ArrayList<MoraleRollOutcomeListener> moraleRollOutcomeListeners;
	protected ArrayList<MoraleChangeListener> moraleChangeListeners;
	protected ArrayList<MoraleRollListener> moraleRollListeners;

	public AttributeManager() {
		this.levelUps = new ArrayList<ArrayList<LevelUp>>();
		this.setUpListeners();
	}

	@Override
	public void setUpListeners() {
		this.postDataListeners = new ArrayList<PostDataListener>();
		this.charInventoryListeners = new ArrayList<SkillPreferenceListener>();
		this.moraleRollOutcomeListeners = new ArrayList<MoraleRollOutcomeListener>();
		this.moraleChangeListeners = new ArrayList<MoraleChangeListener>();
		this.moraleRollListeners = new ArrayList<MoraleRollListener>();
	}

	protected void setUpAttributes(BackgroundGenerator bg) {
		this.assignAttributes(bg);
		this.assignStars(bg.getExcludedTalents());
		this.resetMorale();
	}

	/**
	 * Assigns the attributes their randomly generated base values from the relevant
	 * background
	 */
	protected void assignAttributes(BackgroundGenerator bg) {
		this.hitpoints = new HitpointAttribute((double) bg.getHp().getRand(), 2);
		this.fatigue = new FatigueAttribute((double) bg.getFat().getRand(), 2);
		this.resolve = new StarAttribute((double) bg.getRes().getRand(), 2);
		this.initiative = new StarAttribute((double) bg.getIni().getRand(), 3);
		this.meleeSkill = new StarAttribute((double) bg.getmSk().getRand(), 1);
		this.rangedSkill = new StarAttribute((double) bg.getrSk().getRand(), 2);
		this.meleeDefense = new DefenseAttribute((double) bg.getmDef().getRand(), 1);
		this.rangedDefense = new DefenseAttribute((double) bg.getrDef().getRand(), 1);

		this.mood = new MoodAttribute(50);
		this.actionPoints = new BarAttribute((double) bg.getActionPoints());
		this.headshotChance = new Attribute((double) bg.getHeadShot());
		this.vision = new VisionAttribute((double) bg.getVision());

		this.wage = new WageAttribute((double) bg.getBaseWage());
		this.foodConsumption = new Attribute((double) bg.getDailyFood());
		this.xpRate = new Attribute((double) bg.getXpRate());
		this.level = new LevelAttribute((double) bg.getLev().getRand());
		this.fatigueRecovery = new Attribute((double) bg.getFatRegain());
		this.damage = new Attribute(0);
		this.armorDamage = new Attribute(0);
		this.ignoreArmor = new Attribute(0);
		this.damageHeadshot = new Attribute(150);
		this.survivalChance = new Attribute(33);
		this.inflictInjury = new Attribute(0);
		this.injuryThreshold = new Attribute(0);
	}

	/** Randomly assigns stars/talents towards up to 3 attributes */
	protected void assignStars(ArrayList<String> excludedTalents) {
		ArrayList<StarAttribute> managers = new ArrayList<StarAttribute>();
		if (!excludedTalents.contains("hitpoints")) {
			managers.add(this.hitpoints);
		}
		if (!excludedTalents.contains("fatigue")) {
			managers.add(this.fatigue);
		}
		if (!excludedTalents.contains("resolve")) {
			managers.add(this.resolve);
		}
		if (!excludedTalents.contains("initiative")) {
			managers.add(this.initiative);
		}
		if (!excludedTalents.contains("meleeSkill")) {
			managers.add(this.meleeSkill);
		}
		if (!excludedTalents.contains("rangedSkill")) {
			managers.add(this.rangedSkill);
		}
		if (!excludedTalents.contains("meleeDefense")) {
			managers.add(this.meleeDefense);
		}
		if (!excludedTalents.contains("rangedDefense")) {
			managers.add(this.rangedDefense);
		}

		for (int i = 0; i < 3; i++) {
			int j = GlobalManager.rng.nextInt(managers.size());
			managers.get(j).setNumStars();
			managers.remove(j);
		}
	}

	/** Needs to be called after abilities are set up */
	protected void setMorale() {
		this.notifyMoraleChangeListeners(new MoraleChangeEvent(this.mood.getCurrentMood().getBestMoraleState(), this));
	}

	protected void setMoraleWithoutOverride() {
		double temp;
		double chanceRoll;

		chanceRoll = this.mood.getCurrentMood().getBestMoralechance();

		if (GlobalManager.d100Roll() > chanceRoll) {
			temp = this.mood.getCurrentMood().getBestMoraleState() - 1;
		} else {
			temp = this.mood.getCurrentMood().getBestMoraleState();
		}

		this.currentMorale = MoraleState.valueOfMoraleValue((int) temp);
		this.notifyMoraleChangeListeners(new MoraleChangeEvent(MoraleChangeEvent.Task.SET, this.currentMorale, this));
	}

	protected void resetMorale() {
		this.currentMorale = MoraleState.STEADY;
		this.notifyMoraleChangeListeners(new MoraleChangeEvent(MoraleChangeEvent.Task.RESET, this.currentMorale, this));
	}

	protected void addModifier(Modifier t) {
		this.getAttribute(t.getName()).addModifier(t);
	}

	protected void removeModifier(Modifier t) {
		this.getAttribute(t.getName()).removeModifier(t);
	}

	public Attribute[] getAttributes() {
		return new Attribute[] { this.hitpoints, this.actionPoints, this.fatigue, this.resolve, this.initiative,
				this.meleeSkill, this.rangedSkill, this.meleeDefense, this.rangedDefense, this.damage, this.armorDamage,
				this.headshotChance, this.vision };
	}

	/** Designed to get the relevant attribute */
	protected Attribute getAttribute(String attributeName) {
		try {
			Field temp = this.getClass().getDeclaredField(attributeName);
			Attribute t = (Attribute) temp.get(this);
			return t;
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public MoraleState getCurrentState() {
		return this.currentMorale;
	}

	public ArrayList<ArrayList<LevelUp>> getLevelUps() {
		return this.levelUps;
	}

	/** Gets the level up value from the relevant attributes */
	protected void setUpLevelUp() {
		ArrayList<LevelUp> levelUpArray = new ArrayList<LevelUp>();
		levelUpArray.add(new LevelUp("hitpoint", this.hitpoints.getLevelup()));
		levelUpArray.add(new LevelUp("fatigue", this.fatigue.getLevelup()));
		levelUpArray.add(new LevelUp("resolve", this.resolve.getLevelup()));
		levelUpArray.add(new LevelUp("initiative", this.initiative.getLevelup()));
		levelUpArray.add(new LevelUp("melee_skill", this.meleeSkill.getLevelup()));
		levelUpArray.add(new LevelUp("ranged_skill", this.rangedSkill.getLevelup()));
		levelUpArray.add(new LevelUp("melee_defense", this.meleeDefense.getLevelup()));
		levelUpArray.add(new LevelUp("ranged_defense", this.rangedDefense.getLevelup()));

		this.levelUps.add(levelUpArray);
	}

	/**
	 * Takes in an array of the chosen level ups and applies them to the relevant
	 * Attribute using applyLevelUp Method
	 */
	public void applyLevelUps(ArrayList<LevelUp> levelUpArray) {
		for (int i = 0; i == levelUpArray.size();) {
			this.applyLevelUp(levelUpArray.remove(i));
		}
	}

	protected void applyLevelUp(LevelUp levelUp) {
		this.getAttribute(levelUp.getName())
				.addModifier(new Modifier("Level Up", levelUp.getValue(), false, false, false));
	}

	protected void rollPositive(double modifier) {
		double outcome = this.roll(modifier);
		if (outcome >= 0) {
			this.notifyMoraleRollOutcomeListeners(
					new MoraleRollOutcomeEvent(MoraleRollOutcomeEvent.Task.POSITIVE_ROLL_SUCCESS, outcome, this));
			if (this.currentMorale.getValue() < MoraleState.CONFIDENT.getValue()) {
				this.currentMorale = MoraleState.valueOfMoraleValue(this.currentMorale.getValue() + 1);
			}
		} else
			this.notifyMoraleRollOutcomeListeners(
					new MoraleRollOutcomeEvent(MoraleRollOutcomeEvent.Task.POSITIVE_ROLL_FAIL, outcome, this));
	}

	protected void rollNegative(double modifier) {
		double outcome = this.roll(modifier);
		if (outcome >= 0)
			this.notifyMoraleRollOutcomeListeners(
					new MoraleRollOutcomeEvent(MoraleRollOutcomeEvent.Task.NEGATIVE_ROLL_SUCCESS, outcome, this));
		else {
			this.notifyMoraleRollOutcomeListeners(
					new MoraleRollOutcomeEvent(MoraleRollOutcomeEvent.Task.NEGATIVE_ROLL_FAIL, outcome, this));
			if (this.currentMorale.getValue() > MoraleState.FLEEING.getValue()) {
				this.currentMorale = MoraleState.valueOfMoraleValue(this.currentMorale.getValue() - 1);
			}
		}
	}

	protected void rollSpecial(double modifier) {
		double outcome = this.roll(modifier);
		if (outcome >= 0) {
			this.notifyMoraleRollOutcomeListeners(
					new MoraleRollOutcomeEvent(MoraleRollOutcomeEvent.Task.SPECIAL_ROLL_SUCCESS, outcome, this));
			this.notifyMoraleRollOutcomeListeners(
					new MoraleRollOutcomeEvent(MoraleRollOutcomeEvent.Task.NEGATIVE_ROLL_SUCCESS, outcome, this));
		} else {
			this.notifyMoraleRollOutcomeListeners(
					new MoraleRollOutcomeEvent(MoraleRollOutcomeEvent.Task.SPECIAL_ROLL_FAIL, outcome, this));
			this.notifyMoraleRollOutcomeListeners(
					new MoraleRollOutcomeEvent(MoraleRollOutcomeEvent.Task.NEGATIVE_ROLL_FAIL, outcome, this));
		}

	}

	protected Double roll(double modifier) {
		return this.resolve.getAlteredValue() + modifier - GlobalManager.d100Roll();
	}

	/** Displays stuff, mainly for testing */
	public void display() {
		System.out.println("Level is " + this.level.toString());

		System.out.println("This character has:");

		System.out.println("Hitpoints: " + this.hitpoints.toStringFull());
		System.out.println("Fatigue: " + this.fatigue.toStringFull());
		System.out.println("Morale State: " + this.currentMorale.toString());
		System.out.println("Resolve: " + this.resolve.toStringFull());
		System.out.println("Initiative: " + this.initiative.toStringFull());
		System.out.println("Melee Skill: " + this.meleeSkill.toStringFull());
		System.out.println("Ranged Skill: " + this.rangedSkill.toStringFull());
		System.out.println("Melee Defense: " + this.meleeDefense.toStringFull());
		System.out.println("Ranged Defense: " + this.rangedDefense.toStringFull());
		System.out.println();
		System.out.println("Wage of " + this.wage.toStringFull());
		System.out.println("Consumes " + this.foodConsumption.toStringFull());
		System.out.println("Action points: " + this.actionPoints.toStringFull());
		System.out.println("% Chance to hit head: " + this.headshotChance.toStringFull());
		System.out.println("Points of Fatigue Regained each Turn: " + this.fatigueRecovery.toStringFull());
		System.out.println("Tiles of Vision: " + this.vision.toStringFull());
		System.out.println("Experience Rate is " + this.xpRate.toStringFull());
		System.out.println("Mood: " + this.mood.toStringFull());
		System.out.println("Damage: " + this.damage.toStringFull());
		System.out.println("Armor Damage: " + this.armorDamage.toStringFull());
		System.out.println("Ignore Armor: " + this.ignoreArmor.toStringFull());

		System.out.println();
	}

	@Override
	public void onLevelUpAttributeEvent(LevelUpAttributeEvent l) {
		switch (l.getTask()) {
		case LEVEL_UP:
			this.setUpLevelUp();
			break;
		}
	}

	@Override
	public void onAttributeEvent(AttributeEvent a) {
		switch (a.getTask()) {
		case UPDATE:
			break;
		}
	}

	@Override
	public void onStarAttributeEvent(StarAttributeEvent s) {
		switch (s.getTask()) {
		case STAR_ASSIGNED:
			if (s.getSource() == this.meleeSkill)
				this.notifySkillPreferenceListeners(
						new SkillPreferenceEvent(SkillPreferenceEvent.Task.MELEE_PREF, s.getInformation(), this));
			if (s.getSource() == this.rangedSkill)
				this.notifySkillPreferenceListeners(
						new SkillPreferenceEvent(SkillPreferenceEvent.Task.RANGED_PREF, s.getInformation(), this));
			break;
		}
	}

	@Override
	public void onRetrieveEvent(RetrieveEvent r) {
		this.notifyPostDataListener(r.getTarget(), new PostDataEvent(PostDataEvent.Task.GOT, r.getInformation(),
				this.getAttribute(r.getInformation()).getAlteredValue(), this));
	}

	@Override
	public void onModifierEvent(ModifierEvent m) {
		switch (m.getTask()) {
		case ADD:
			this.addModifier(m.getInformation());
			break;
		case REMOVE:
			this.removeModifier(m.getInformation());
			break;
		}
	}

	@Override
	public void onMultiValueAttributeEvent(MultiValueAttributeEvent m) {
		switch (m.getTask()) {
		case UPDATE_CURRENT:
			if (m.getSource() == this.fatigue)
				this.initiative.addModifier(new Modifier("Fatigue_Penalty", -m.getInformation(), false, true, true));
			break;
		}
	}

	@Override
	public void onTurnControlEvent(TurnControlEvent t) {
		switch (t.getTask()) {
		case END_TURN:
			break;
		case START_TURN:
			this.fatigue.alterCurrent(-this.fatigueRecovery.getAlteredValue());
			break;
		}
	}

	@Override
	public void onMoraleRollEvent(MoraleRollEvent m) {
		this.notifyMoraleRollListeners(m);
		switch (m.getTask()) {
		case ROLL_NEGATIVE:
			this.rollNegative(m.getInformation());
			break;
		case ROLL_POSITIVE:
			this.rollPositive(m.getInformation());
			break;
		case ROLL_SPECIAL:
			this.notifyMoraleRollListeners(
					new MoraleRollEvent(MoraleRollEvent.Task.ROLL_NEGATIVE, m.getInformation(), this));
			this.rollSpecial(m.getInformation());
			break;
		}
	}

	@Override
	public void onMoraleChangeEvent(MoraleChangeEvent m) {
		switch (m.getTask()) {
		case INITIAL:
			break;
		case OVERRIDE:
			break;
		case CHANGE:
			break;
		case RESET:
			break;
		case SET:
			break;
		}
	}

	@Override
	public void onRoundControlEvent(RoundControlEvent r) {
		switch (r.getTask()) {
		case END_ROUND:
			break;
		case START_ROUND:
			break;
		}
	}

	@Override
	public void addPostDataListener(PostDataListener a) {
		this.postDataListeners.add(a);
	}

	@Override
	public void removePostDataListener(PostDataListener a) {
		this.postDataListeners.remove(a);
	}

	@Override
	public void notifyPostDataListeners(PostDataEvent a) {
		this.postDataListeners.forEach(l -> l.onPostDataEvent(a));
	}

	@Override
	public void notifyPostDataListener(PostDataListener a, PostDataEvent e) {
		this.postDataListeners.get(a).onPostDataEvent(e);
	}

	@Override
	public void addSkillPreferenceListener(SkillPreferenceListener i) {
		this.charInventoryListeners.add(i);
	}

	@Override
	public void removeSkillPreferenceListener(SkillPreferenceListener i) {
		this.charInventoryListeners.remove(i);
	}

	@Override
	public void notifySkillPreferenceListeners(SkillPreferenceEvent i) {
		this.charInventoryListeners.forEach(l -> l.onSkillPreferenceEvent(i));
	}

	@Override
	public void notifySkillPreferenceListener(SkillPreferenceListener i, SkillPreferenceEvent e) {
		this.charInventoryListeners.get(i).onSkillPreferenceEvent(e);
	}

	@Override
	public void addMoraleRollOutcomeListener(MoraleRollOutcomeListener m) {
		this.moraleRollOutcomeListeners.add(m);
	}

	@Override
	public void removeMoraleRollOutcomeListener(MoraleRollOutcomeListener m) {
		this.moraleRollOutcomeListeners.remove(m);
	}

	@Override
	public void notifyMoraleRollOutcomeListeners(MoraleRollOutcomeEvent m) {
		this.moraleRollOutcomeListeners.forEach(l -> l.onMoraleRollOutcomeEvent(m));
	}

	@Override
	public void notifyMoraleRollOutcomeListener(MoraleRollOutcomeListener m, MoraleRollOutcomeEvent e) {
		this.moraleRollOutcomeListeners.get(m).onMoraleRollOutcomeEvent(e);
	}

	@Override
	public void addMoraleChangeListener(MoraleChangeListener m) {
		this.moraleChangeListeners.add(m);
	}

	@Override
	public void removeMoraleChangeListener(MoraleChangeListener m) {
		this.moraleChangeListeners.remove(m);
	}

	@Override
	public void notifyMoraleChangeListeners(MoraleChangeEvent m) {
		this.moraleChangeListeners.forEach(l -> l.onMoraleChangeEvent(m));
	}

	@Override
	public void notifyMoraleChangeListener(MoraleChangeListener m, MoraleChangeEvent e) {
		this.moraleChangeListeners.get(m).onMoraleChangeEvent(e);
	}

	@Override
	public void addMoraleRollListener(MoraleRollListener m) {
		this.moraleRollListeners.add(m);
	}

	@Override
	public void removeMoraleRollListener(MoraleRollListener m) {
		this.moraleRollListeners.remove(m);
	}

	@Override
	public void notifyMoraleRollListeners(MoraleRollEvent m) {
		this.moraleRollListeners.forEach(l -> l.onMoraleRollEvent(m));
	}

	@Override
	public void notifyMoraleRollListener(MoraleRollListener m, MoraleRollEvent e) {
		this.moraleRollListeners.get(m).onMoraleRollEvent(e);
	}
}
