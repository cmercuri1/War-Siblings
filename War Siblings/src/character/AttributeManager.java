/** War Siblings
 * AttributeManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import java.lang.reflect.Field;

import effect_classes.Modifier;
import event_classes.AbilityEvent;
import event_classes.AttributeEvent;
import event_classes.CharacterInventoryEvent;
import event_classes.LevelUpAttributeEvent;
import event_classes.ModifierEvent;
import event_classes.MoraleEvent;
import event_classes.MoraleRollEvent;
import event_classes.MultiValueAttributeEvent;
import event_classes.PostDataEvent;
import event_classes.RetrieveEvent;
import event_classes.StarAttributeEvent;
import event_classes.TurnControlEvent;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;
import listener_interfaces.AbilityListener;
import listener_interfaces.AttributeListener;
import listener_interfaces.CharacterInventoryListener;
import listener_interfaces.LevelUpAttributeListener;
import listener_interfaces.ModifierListener;
import listener_interfaces.MoraleListener;
import listener_interfaces.MoraleRollListener;
import listener_interfaces.MultiValueAttributeListener;
import listener_interfaces.PostDataListener;
import listener_interfaces.RetrievalListener;
import listener_interfaces.TurnControlListener;
import listener_interfaces.StarAttributeListener;
import notifier_interfaces.PostDataNotifier;
import notifier_interfaces.AbilityNotifier;
import notifier_interfaces.CharacterInventoryNotifier;
import notifier_interfaces.MoraleNotifier;
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
		PostDataNotifier, CharacterInventoryNotifier, AbilityNotifier, MoraleNotifier {
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
	protected ArrayList<CharacterInventoryListener> charInventoryListeners;
	protected ArrayList<MoraleListener> moraleListeners;
	protected ArrayList<AbilityListener> abilityListeners;

	protected int pref; // Only used in determining starting equipment

	public AttributeManager() {
		this.levelUps = new ArrayList<ArrayList<LevelUp>>();
		this.setUpListeners();
	}

	@Override
	public void setUpListeners() {
		this.postDataListeners = new ArrayList<PostDataListener>();
		this.charInventoryListeners = new ArrayList<CharacterInventoryListener>();
		this.moraleListeners = new ArrayList<MoraleListener>();
		this.abilityListeners = new ArrayList<AbilityListener>();
	}

	public void setUpAttributes(BackgroundGenerator bg) {
		this.assignAttributes(bg);
		this.assignStars(bg.getExcludedTalents());
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

		if (pref > 0) {
			this.notifyCharacterInventoryListeners(
					new CharacterInventoryEvent(CharacterInventoryEvent.Task.RANGED_PREF, null, this));
		}
	}

	/** Needs to be called after abilities are set up */
	protected void setMorale() {
		double temp;
		double chanceRoll;

		chanceRoll = this.mood.getCurrentMood().getBestMoralechance();

		if (GlobalManager.d100Roll() > chanceRoll) {
			temp = this.mood.getCurrentMood().getBestMoraleState() - 1;
		} else {
			temp = this.mood.getCurrentMood().getBestMoraleState();
		}

		this.changeState(MoraleState.valueOfMoraleValue((int) temp));
	}

	protected void resetMorale() {
		this.changeState(MoraleState.STEADY);
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
		Field temp = null;
		try {
			temp = this.getClass().getDeclaredField(attributeName);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		try {
			return (Attribute) temp.get(this);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
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

	/** Displays stuff, mainly for testing */
	public void display() {
		System.out.println("Level is " + this.level.toString());

		System.out.println("This character has:");

		System.out.println("Hitpoints: " + this.hitpoints.toStringFull());
		System.out.println("Fatigue: " + this.fatigue.toStringFull());
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
				pref -= s.getInformation();
			if (s.getSource() == this.rangedSkill)
				pref += s.getInformation();
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
	public void addCharacterInventoryListener(CharacterInventoryListener c) {
		this.charInventoryListeners.add(c);
	}

	@Override
	public void removeCharacterInventoryListener(CharacterInventoryListener c) {
		this.charInventoryListeners.remove(c);
	}

	@Override
	public void notifyCharacterInventoryListeners(CharacterInventoryEvent c) {
		this.charInventoryListeners.forEach(l -> l.onCharacterInventoryEvent(c));
	}

	@Override
	public void notifyCharacterInventoryListener(CharacterInventoryListener c, CharacterInventoryEvent e) {
		this.charInventoryListeners.get(c).onCharacterInventoryEvent(e);
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
	public void addMoraleListener(MoraleListener m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMoraleListener(MoraleListener m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyMoraleListeners(MoraleEvent m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyMoraleListener(MoraleListener m, MoraleEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAbilityListener(AbilityListener a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAbilityListener(AbilityListener a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyAbilityListeners(AbilityEvent a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyAbilityListener(AbilityListener a, AbilityEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMoraleRollEvent(MoraleRollEvent m) {
		// TODO Auto-generated method stub

	}
}
