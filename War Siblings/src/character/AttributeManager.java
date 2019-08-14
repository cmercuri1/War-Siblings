/** War Siblings
 * AttributeManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

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
import storage_classes.WageAttribute;

/**
 * A class that manages all the attributes and makes sure they operate correctly
 */
public class AttributeManager implements MultiNotifier, AttributeListener, EffectListener, LevelUpAttributeListener,
		MultiValueAttributeListener, RetrievalListener, TurnControlListener, StarAttributeListener, PostDataNotifier,
		CharacterInventoryNotifier {
	protected HitpointAttribute hitpointManager;
	protected FatigueAttribute fatigueManager;
	protected StarAttribute resolveManager;
	protected StarAttribute initiativeManager;
	protected StarAttribute meleeSkillManager;
	protected StarAttribute rangedSkillManager;
	protected DefenseAttribute meleeDefenseManager;
	protected DefenseAttribute rangedDefenseManager;

	protected WageAttribute wageManager;
	protected Attribute foodManager;
	protected Attribute xpRateManager;
	protected LevelAttribute levelManager;
	protected BarAttribute actionPointsManager;
	protected Attribute headshotManager;
	protected Attribute fatigueRegManager;
	protected Attribute visionManager;

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
		this.hitpointManager = new HitpointAttribute((double) bg.getHp().getRand(), 2);
		this.fatigueManager = new FatigueAttribute((double) bg.getFat().getRand(), 2);
		this.resolveManager = new StarAttribute((double) bg.getRes().getRand(), 2);
		this.initiativeManager = new StarAttribute((double) bg.getIni().getRand(), 3);
		this.meleeSkillManager = new StarAttribute((double) bg.getmSk().getRand(), 1);
		this.rangedSkillManager = new StarAttribute((double) bg.getrSk().getRand(), 2);
		this.meleeDefenseManager = new DefenseAttribute((double) bg.getmDef().getRand(), 1);
		this.rangedDefenseManager = new DefenseAttribute((double) bg.getrDef().getRand(), 1);

		this.wageManager = new WageAttribute((double) bg.getBaseWage());
		this.foodManager = new Attribute((double) bg.getDailyFood());
		this.xpRateManager = new Attribute((double) bg.getXpRate());
		this.levelManager = new LevelAttribute((double) bg.getLev().getRand());
		this.actionPointsManager = new BarAttribute((double) bg.getActionPoints());
		this.headshotManager = new Attribute((double) bg.getHeadShot());
		this.fatigueRegManager = new Attribute((double) bg.getFatRegain());
		this.visionManager = new Attribute((double) bg.getVision());
	}

	/** Randomly assigns stars/talents towards up to 3 attributes */
	protected void assignStars(ArrayList<String> excludedTalents) {
		ArrayList<StarAttribute> managers = new ArrayList<StarAttribute>();
		if (!excludedTalents.contains("hitpoints")) {
			managers.add(this.hitpointManager);
		}
		if (!excludedTalents.contains("fatigue")) {
			managers.add(this.fatigueManager);
		}
		if (!excludedTalents.contains("resolve")) {
			managers.add(this.resolveManager);
		}
		if (!excludedTalents.contains("initiative")) {
			managers.add(this.initiativeManager);
		}
		if (!excludedTalents.contains("meleeSkill")) {
			managers.add(this.meleeSkillManager);
		}
		if (!excludedTalents.contains("rangedSkill")) {
			managers.add(this.rangedSkillManager);
		}
		if (!excludedTalents.contains("meleeDefense")) {
			managers.add(this.meleeDefenseManager);
		}
		if (!excludedTalents.contains("rangedDefense")) {
			managers.add(this.rangedDefenseManager);
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
		this.getAttribute(t.getAffectedSubManager()).addModifier(t.getModifier());
	}

	protected void removeModifier(Effect t) {
		this.getAttribute(t.getAffectedSubManager()).removeModifier(t.getModifier());
	}

	public Attribute[] getAttributes() {
		return new Attribute[] { this.hitpointManager, this.actionPointsManager, this.fatigueManager,
				this.resolveManager, this.initiativeManager, this.meleeSkillManager, this.rangedSkillManager,
				this.meleeDefenseManager, this.rangedDefenseManager, this.headshotManager, this.visionManager };
	}

	/** Designed to get the relevant attribute */
	protected Attribute getAttribute(String attributeName) {
		if (attributeName.equals("hitpoint")) {
			return this.hitpointManager;
		} else if (attributeName.equals("fatigue")) {
			return this.fatigueManager;
		} else if (attributeName.equals("resolve")) {
			return this.fatigueManager;
		} else if (attributeName.equals("initiative")) {
			return this.initiativeManager;
		} else if (attributeName.equals("meleeSkill")) {
			return this.meleeSkillManager;
		} else if (attributeName.equals("rangedSkill")) {
			return this.rangedSkillManager;
		} else if (attributeName.equals("meleeDefense")) {
			return this.meleeDefenseManager;
		} else if (attributeName.equals("rangedDefense")) {
			return this.rangedDefenseManager;
		} else if (attributeName.equals("wage")) {
			return this.wageManager;
		} else if (attributeName.equals("food")) {
			return this.foodManager;
		} else if (attributeName.equals("xpRate")) {
			return this.xpRateManager;
		} else if (attributeName.equals("level")) {
			return this.levelManager;
		} else if (attributeName.equals("actionPoints")) {
			return this.actionPointsManager;
		} else if (attributeName.equals("headshot")) {
			return this.headshotManager;
		} else if (attributeName.equals("fatigueReg")) {
			return this.fatigueRegManager;
		} else if (attributeName.equals("vision")) {
			return this.visionManager;
		}
		return null;
	}

	public ArrayList<ArrayList<LevelUp>> getLevelUps() {
		return this.levelUps;
	}

	/** Gets the level up value from the relevant attributes */
	protected void setUpLevelUp() {
		ArrayList<LevelUp> levelUpArray = new ArrayList<LevelUp>();
		levelUpArray.add(new LevelUp("hitpoint", this.hitpointManager.getLevelup()));
		levelUpArray.add(new LevelUp("fatigue", this.fatigueManager.getLevelup()));
		levelUpArray.add(new LevelUp("resolve", this.resolveManager.getLevelup()));
		levelUpArray.add(new LevelUp("initiative", this.initiativeManager.getLevelup()));
		levelUpArray.add(new LevelUp("meleeSkill", this.meleeSkillManager.getLevelup()));
		levelUpArray.add(new LevelUp("rangedSkill", this.rangedSkillManager.getLevelup()));
		levelUpArray.add(new LevelUp("meleeDefense", this.meleeDefenseManager.getLevelup()));
		levelUpArray.add(new LevelUp("rangedDefense", this.rangedDefenseManager.getLevelup()));

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
		System.out.println("Level is " + this.levelManager.toString());

		System.out.println("This character has:");

		System.out.println("Hitpoints: " + this.hitpointManager.toStringFull());
		System.out.println("Fatigue: " + this.fatigueManager.toStringFull());
		System.out.println("Resolve: " + this.resolveManager.toStringFull());
		System.out.println("Initiative: " + this.initiativeManager.toStringFull());
		System.out.println("Melee Skill: " + this.meleeSkillManager.toStringFull());
		System.out.println("Ranged Skill: " + this.rangedSkillManager.toStringFull());
		System.out.println("Melee Defense: " + this.meleeDefenseManager.toStringFull());
		System.out.println("Ranged Defense: " + this.rangedDefenseManager.toStringFull());
		System.out.println();
		System.out.println("Wage of " + this.wageManager.toStringFull());
		System.out.println("Consumes " + this.foodManager.toStringFull());
		System.out.println("Action points: " + this.actionPointsManager.toStringFull());
		System.out.println("% Chance to hit head: " + this.headshotManager.toStringFull());
		System.out.println("Points of Fatigue Regained each Turn: " + this.fatigueRegManager.toStringFull());
		System.out.println("Tiles of Vision: " + this.visionManager.toStringFull());
		System.out.println("Experience Rate is " + this.xpRateManager.toStringFull());

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
			if (s.getSource() == this.meleeSkillManager)
				pref -= s.getInformation();
			if (s.getSource() == this.rangedSkillManager)
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
			if (m.getSource() == this.fatigueManager)
				this.initiativeManager
						.addModifier(new Modifier("Fatigue_Penalty", -m.getInformation(), false, true, true));
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
			this.fatigueManager.alterCurrent(-this.fatigueRegManager.getAlteredValue());
			break;
		}

	}
}
