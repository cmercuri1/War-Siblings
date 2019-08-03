/** War Siblings
 * AttributeManager class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package character;

import event_classes.EventObject;
import event_classes.EventType;
import event_classes.GenericObservee;
import event_classes.Observer;
import event_classes.Target;

import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;

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
 * A class that manages all the attributes and makes sure the operate correctly
 */
public class AttributeManager extends GenericObservee implements Observer {
	private HitpointAttribute hitpointManager;
	private FatigueAttribute fatigueManager;
	private StarAttribute resolveManager;
	private StarAttribute initiativeManager;
	private StarAttribute meleeSkillManager;
	private StarAttribute rangedSkillManager;
	private DefenseAttribute meleeDefenseManager;
	private DefenseAttribute rangedDefenseManager;

	private WageAttribute wageManager;
	private Attribute foodManager;
	private Attribute xpRateManager;
	private LevelAttribute levelManager;
	private BarAttribute actionPointsManager;
	private Attribute headshotManager;
	private Attribute fatigueRegManager;
	private Attribute visionManager;

	private int pref; // Only used in determining starting equipment

	public AttributeManager(BackgroundGenerator bg, Observer o) {
		this.setUpObservers();
		this.registerObserver(o);
		this.assignAttributes(bg);
		this.assignStars(bg.getExcludedTalents());
	}

	/**
	 * Assigns the attributes their randomly generated base values from the relevant
	 * background
	 */
	private void assignAttributes(BackgroundGenerator bg) {
		this.hitpointManager = new HitpointAttribute((double) bg.getHp().getRand(), 2, this);
		this.fatigueManager = new FatigueAttribute((double) bg.getFat().getRand(), 2, this);
		this.resolveManager = new StarAttribute((double) bg.getRes().getRand(), 2, this);
		this.initiativeManager = new StarAttribute((double) bg.getIni().getRand(), 3, this);
		this.meleeSkillManager = new StarAttribute((double) bg.getmSk().getRand(), 1, this);
		this.rangedSkillManager = new StarAttribute((double) bg.getrSk().getRand(), 2, this);
		this.meleeDefenseManager = new DefenseAttribute((double) bg.getmDef().getRand(), 1, this);
		this.rangedDefenseManager = new DefenseAttribute((double) bg.getrDef().getRand(), 1, this);

		this.wageManager = new WageAttribute((double) bg.getBaseWage(), this);
		this.foodManager = new Attribute((double) bg.getDailyFood(), this);
		this.xpRateManager = new Attribute((double) bg.getXpRate(), this);
		this.levelManager = new LevelAttribute((double) bg.getLev().getRand(), this);
		this.actionPointsManager = new BarAttribute((double) bg.getActionPoints(), this);
		this.headshotManager = new Attribute((double) bg.getHeadShot(), this);
		this.fatigueRegManager = new Attribute((double) bg.getFatRegain(), this);
		this.visionManager = new Attribute((double) bg.getVision(), this);
	}

	/** Randomly assigns stars/talents towards up to 3 attributes */
	private void assignStars(ArrayList<String> excludedTalents) {
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
			this.notifyObservers(new EventObject(Target.CHARACTER, EventType.RANGED_PREF, null, null));
		}
	}

	public void addModifier(Effect t) {
		this.getAttribute(t.getAffectedSubManager()).addModifier(t.getModifier());
	}

	public void removeModifier(Effect t) {
		this.getAttribute(t.getAffectedSubManager()).removeModifier(t.getModifier());
	}

	/** Designed to get the relevant attribute */
	public Attribute getAttribute(String attributeName) {
		if (attributeName.equals("hitpoint")) {
			return this.hitpointManager;
		} else if (attributeName.equals("fatigue")) {
			return this.fatigueManager;
		} else if (attributeName.equals("resolve")) {
			return this.resolveManager;
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

	/** Gets the level up value from the relevant attributes */
	public ArrayList<LevelUp> getLevelUps() {
		ArrayList<LevelUp> levelUpArray = new ArrayList<LevelUp>();
		levelUpArray.add(new LevelUp("hitpoint", this.hitpointManager.getLevelup()));
		levelUpArray.add(new LevelUp("fatigue", this.fatigueManager.getLevelup()));
		levelUpArray.add(new LevelUp("resolve", this.resolveManager.getLevelup()));
		levelUpArray.add(new LevelUp("initiative", this.initiativeManager.getLevelup()));
		levelUpArray.add(new LevelUp("meleeSkill", this.meleeSkillManager.getLevelup()));
		levelUpArray.add(new LevelUp("rangedSkill", this.rangedSkillManager.getLevelup()));
		levelUpArray.add(new LevelUp("meleeDefense", this.meleeDefenseManager.getLevelup()));
		levelUpArray.add(new LevelUp("rangedDefense", this.rangedDefenseManager.getLevelup()));

		return levelUpArray;
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

	private void applyLevelUp(LevelUp levelUp) {
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

	@SuppressWarnings("unchecked")
	@Override
	public void onEventHappening(EventObject event) {
		Object[] temp;
		switch (event.getTarget()) {
		case ATTRIBUTE:
			switch (event.getTask()) {
			case ADD:
				this.addModifier((Effect) event.getInformation());
				break;
			case REMOVE:
				this.removeModifier((Effect) event.getInformation());
				break;
			case GET:
				temp = new Object[] { event.getInformation(),
						this.getAttribute((String) event.getInformation()).getAlteredValue() };
				this.notifyObservers(new EventObject(Target.UNDEFINED, EventType.GOT, temp, event.getRequester()));
				break;
			case GET_OTHER:
				this.notifyObservers(new EventObject(Target.UNDEFINED, EventType.GOT_OTHER,
						this.getAttribute((String) event.getInformation()).getAlteredValue(), event.getRequester()));
				break;
			case START_TURN:
				this.fatigueManager.alterCurrent(-this.fatigueRegManager.getAlteredValue());
				break;
			case LEVEL_UP:
				this.notifyObservers(new EventObject(Target.UI, EventType.LEVEL_UP, this.getLevelUps(), null));
				this.wageManager.levelWage((int) event.getInformation());
				break;
			case APPLY_LEVEL_UP:
				this.applyLevelUps((ArrayList<LevelUp>) event.getInformation());
				break;
			case HIT:
				this.fatigueManager.onHit();
				break;
			case MISS:
				this.fatigueManager.onMiss();
				break;
			case UPDATE:
				temp = (Object[]) event.getInformation();
				if (temp[0] instanceof FatigueAttribute) {
					this.initiativeManager
							.addModifier(new Modifier("Fatigue_Penalty", -(double) temp[1], false, true, true));
				}
				break;
			case STAR_ASSIGNED:
				temp = (Object[]) event.getInformation();
				int star = 0;
				if (temp[0] == this.meleeSkillManager) {
					star = (int) temp[1];
					pref -= star;
				}
				if (temp[0] == this.rangedSkillManager) {
					star = (int) temp[1];
					pref += star;
				}
				break;
			default:
				break;
			}
			break;
		default:
			break;

		}
	}
}
