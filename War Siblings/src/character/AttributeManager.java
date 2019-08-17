/** War Siblings
 * AttributeManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import java.lang.reflect.Field;

import event_classes.AttributeEvent;
import event_classes.CharacterInventoryEvent;
import event_classes.EffectEvent;
import event_classes.LevelUpAttributeEvent;
import event_classes.MultiValueAttributeEvent;
import event_classes.PostDataEvent;
import event_classes.RetrieveEvent;
import event_classes.StarAttributeEvent;
import event_classes.TurnControlEvent;
import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;
import listener_interfaces.AttributeListener;
import listener_interfaces.CharacterInventoryListener;
import listener_interfaces.EffectListener;
import listener_interfaces.LevelUpAttributeListener;
import listener_interfaces.MultiValueAttributeListener;
import listener_interfaces.PostDataListener;
import listener_interfaces.RetrievalListener;
import listener_interfaces.TurnControlListener;
import listener_interfaces.StarAttributeListener;
import notifier_interfaces.PostDataNotifier;
import notifier_interfaces.CharacterInventoryNotifier;
import notifier_interfaces.MultiNotifier;
import storage_classes.ArrayList;
import storage_classes.Attribute;
import storage_classes.BarAttribute;
import storage_classes.DefenseAttribute;
import storage_classes.Effect;
import storage_classes.FatigueAttribute;
import storage_classes.HitpointAttribute;
import storage_classes.LevelAttribute;
import storage_classes.LevelUp;
import storage_classes.Modifier;
import storage_classes.StarAttribute;
import storage_classes.VisionAttribute;
import storage_classes.WageAttribute;

/**
 * A class that manages all the attributes and makes sure they operate correctly
 */
public class AttributeManager implements MultiNotifier, AttributeListener, EffectListener, LevelUpAttributeListener,
		MultiValueAttributeListener, RetrievalListener, TurnControlListener, StarAttributeListener, PostDataNotifier,
		CharacterInventoryNotifier {
	protected HitpointAttribute hitpoint;
	protected FatigueAttribute fatigue;
	protected StarAttribute resolve;
	protected StarAttribute initiative;
	protected StarAttribute melee_skill;
	protected StarAttribute ranged_skill;
	protected DefenseAttribute melee_defense;
	protected DefenseAttribute ranged_defense;

	protected WageAttribute wage;
	protected Attribute food_consumption;
	protected Attribute xp_rate;
	protected LevelAttribute level;
	protected BarAttribute action_points;
	protected Attribute headshot_chance;
	protected Attribute fatigue_recovery;
	protected VisionAttribute vision;

	protected Attribute action_points_on_movement;
	protected Attribute fatigue_on_movement;
	protected Attribute damage;
	protected Attribute armor_damage;
	protected Attribute ignore_armor;
	protected Attribute damage_headshot;

	protected ArrayList<ArrayList<LevelUp>> levelUps;

	protected ArrayList<PostDataListener> postDataListeners;
	protected ArrayList<CharacterInventoryListener> charInventoryListeners;

	protected int pref; // Only used in determining starting equipment

	public AttributeManager() {
		this.levelUps = new ArrayList<ArrayList<LevelUp>>();
		this.setUpListeners();
	}

	@Override
	public void setUpListeners() {
		this.postDataListeners = new ArrayList<PostDataListener>();
		this.charInventoryListeners = new ArrayList<CharacterInventoryListener>();
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
		this.hitpoint = new HitpointAttribute((double) bg.getHp().getRand(), 2);
		this.fatigue = new FatigueAttribute((double) bg.getFat().getRand(), 2);
		this.resolve = new StarAttribute((double) bg.getRes().getRand(), 2);
		this.initiative = new StarAttribute((double) bg.getIni().getRand(), 3);
		this.melee_skill = new StarAttribute((double) bg.getmSk().getRand(), 1);
		this.ranged_skill = new StarAttribute((double) bg.getrSk().getRand(), 2);
		this.melee_defense = new DefenseAttribute((double) bg.getmDef().getRand(), 1);
		this.ranged_defense = new DefenseAttribute((double) bg.getrDef().getRand(), 1);

		this.wage = new WageAttribute((double) bg.getBaseWage());
		this.food_consumption = new Attribute((double) bg.getDailyFood());
		this.xp_rate = new Attribute((double) bg.getXpRate());
		this.level = new LevelAttribute((double) bg.getLev().getRand());
		this.action_points = new BarAttribute((double) bg.getActionPoints());
		this.headshot_chance = new Attribute((double) bg.getHeadShot());
		this.fatigue_recovery = new Attribute((double) bg.getFatRegain());
		this.vision = new VisionAttribute((double) bg.getVision());

		this.action_points_on_movement = new Attribute(2);
		this.fatigue_on_movement = new Attribute(2);
		this.damage = new Attribute(0);
		this.armor_damage = new Attribute(0);
		this.damage_headshot = new Attribute(150);
	}

	/** Randomly assigns stars/talents towards up to 3 attributes */
	protected void assignStars(ArrayList<String> excludedTalents) {
		ArrayList<StarAttribute> managers = new ArrayList<StarAttribute>();
		if (!excludedTalents.contains("hitpoints")) {
			managers.add(this.hitpoint);
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
			managers.add(this.melee_skill);
		}
		if (!excludedTalents.contains("rangedSkill")) {
			managers.add(this.ranged_skill);
		}
		if (!excludedTalents.contains("meleeDefense")) {
			managers.add(this.melee_defense);
		}
		if (!excludedTalents.contains("rangedDefense")) {
			managers.add(this.ranged_defense);
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

	protected void addModifier(Effect t) {
		this.getAttribute(t.getName().toLowerCase()).addModifier(t.getModifier());
	}

	protected void removeModifier(Effect t) {
		this.getAttribute(t.getAffectedSubManager()).removeModifier(t.getModifier());
	}

	public Attribute[] getAttributes() {
		return new Attribute[] { this.hitpoint, this.action_points, this.fatigue, this.resolve, this.initiative,
				this.melee_skill, this.ranged_skill, this.melee_defense, this.ranged_defense, this.headshot_chance, this.vision };
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
		levelUpArray.add(new LevelUp("hitpoint", this.hitpoint.getLevelup()));
		levelUpArray.add(new LevelUp("fatigue", this.fatigue.getLevelup()));
		levelUpArray.add(new LevelUp("resolve", this.resolve.getLevelup()));
		levelUpArray.add(new LevelUp("initiative", this.initiative.getLevelup()));
		levelUpArray.add(new LevelUp("meleeSkill", this.melee_skill.getLevelup()));
		levelUpArray.add(new LevelUp("rangedSkill", this.ranged_skill.getLevelup()));
		levelUpArray.add(new LevelUp("meleeDefense", this.melee_defense.getLevelup()));
		levelUpArray.add(new LevelUp("rangedDefense", this.ranged_defense.getLevelup()));

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

		System.out.println("Hitpoints: " + this.hitpoint.toStringFull());
		System.out.println("Fatigue: " + this.fatigue.toStringFull());
		System.out.println("Resolve: " + this.resolve.toStringFull());
		System.out.println("Initiative: " + this.initiative.toStringFull());
		System.out.println("Melee Skill: " + this.melee_skill.toStringFull());
		System.out.println("Ranged Skill: " + this.ranged_skill.toStringFull());
		System.out.println("Melee Defense: " + this.melee_defense.toStringFull());
		System.out.println("Ranged Defense: " + this.ranged_defense.toStringFull());
		System.out.println();
		System.out.println("Wage of " + this.wage.toStringFull());
		System.out.println("Consumes " + this.food_consumption.toStringFull());
		System.out.println("Action points: " + this.action_points.toStringFull());
		System.out.println("% Chance to hit head: " + this.headshot_chance.toStringFull());
		System.out.println("Points of Fatigue Regained each Turn: " + this.fatigue_recovery.toStringFull());
		System.out.println("Tiles of Vision: " + this.vision.toStringFull());
		System.out.println("Experience Rate is " + this.xp_rate.toStringFull());

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
			if (s.getSource() == this.melee_skill)
				pref -= s.getInformation();
			if (s.getSource() == this.ranged_skill)
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
	public void onEffectEvent(EffectEvent e) {
		if (e.getInformation().getAffectedManager().equals("Attribute")) {
			switch (e.getTask()) {
			case ADD:
				this.addModifier(e.getInformation());
				break;
			case REMOVE:
				this.removeModifier(e.getInformation());
				break;
			}
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
			this.fatigue.alterCurrent(-this.fatigue_recovery.getAlteredValue());
			break;
		}

	}
}
