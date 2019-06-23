package character;

import java.util.ArrayList;

import common_classes.Attribute;
import common_classes.Modifier;

import global_generators.BackgroundGenerator;
import global_managers.GlobalManager;

/**
 * A class that manages all the attributes and makes sure the operate correctly
 */
public class AttributeManager {
	private HitpointAttribute hitpointManager;
	private FatigueAttribute fatigueManager;
	private StarAttribute resolveManager;
	private StarAttribute initiativeManager;
	private StarAttribute meleeSkillManager;
	private StarAttribute rangedSkillManager;
	private StarAttribute meleeDefenseManager;
	private StarAttribute rangedDefenseManager;

	private Attribute wageManager;
	private Attribute xpRateManager;
	private Attribute levelManager;
	private Attribute actionPointsManager;
	private Attribute headShotManager;
	private Attribute fatigueRegManager;
	private Attribute visionManager;

	public AttributeManager(BackgroundGenerator bg) {
		this.assignAttributes(bg);
		this.assignStars();
	}

	/**
	 * Assigns the attributes their randomly generated base values from the relevant
	 * background
	 */
	private void assignAttributes(BackgroundGenerator bg) {

		this.hitpointManager = new HitpointAttribute((double) bg.getHp().getRand(), 2);
		this.fatigueManager = new FatigueAttribute((double) bg.getFat().getRand(), 2);
		this.resolveManager = new StarAttribute((double) bg.getRes().getRand(), 2);
		this.initiativeManager = new StarAttribute((double) bg.getIni().getRand(), 3);
		this.meleeSkillManager = new StarAttribute((double) bg.getmSk().getRand(), 1);
		this.rangedSkillManager = new StarAttribute((double) bg.getrSk().getRand(), 2);
		this.meleeDefenseManager = new StarAttribute((double) bg.getmDef().getRand(), 1);
		this.rangedDefenseManager = new StarAttribute((double) bg.getrDef().getRand(), 1);

		this.wageManager = new Attribute((double) bg.getBaseWage());
		this.xpRateManager = new Attribute((double) bg.getXpRate());
		this.levelManager = new Attribute((double) bg.getLev().getRand());
		this.actionPointsManager = new Attribute((double) bg.getActionPoints());
		this.headShotManager = new Attribute((double) bg.getHeadShot());
		this.fatigueRegManager = new Attribute((double) bg.getFatRegain());
		this.visionManager = new Attribute((double) bg.getVision());
	}

	/** Randomly assigns stars/talents towards up to 3 attributes */
	private void assignStars() {
		ArrayList<StarAttribute> managers = new ArrayList<StarAttribute>();

		managers.add(hitpointManager);
		managers.add(fatigueManager);
		managers.add(resolveManager);
		managers.add(initiativeManager);
		managers.add(meleeSkillManager);
		managers.add(rangedSkillManager);
		managers.add(meleeDefenseManager);
		managers.add(rangedDefenseManager);

		for (int i = 0; i < 3; i++) {
			int j = GlobalManager.rng.nextInt(managers.size());
			managers.get(j).setNumStars();
			managers.remove(j);
		}
	}

	/** Designed to get the relevant attribute */
	public Attribute getAttribute(String attributeName) {
		Object o = attributeName + "manager";
		return (Attribute) o;
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
		this.getAttribute(levelUp.getName()).newModifier(new Modifier("Level Up", levelUp.getValue(), false));
	}

	/** Displays stuff, mainly for testing */
	public void display() {
		System.out.println("Level is " + this.levelManager.getAlteredValue());

		System.out.println("This character has:");

		System.out.println("Hitpoints: " + this.hitpointManager.toString());
		System.out.println("Fatigue: " + this.fatigueManager.toString());
		System.out.println("Resolve: " + this.resolveManager.toString());
		System.out.println("Initiative: " + this.initiativeManager.toString());
		System.out.println("Melee Skill: " + this.meleeSkillManager.toString());
		System.out.println("Ranged Skill: " + this.rangedSkillManager.toString());
		System.out.println("Melee Defense: " + this.meleeDefenseManager.toString());
		System.out.println("Ranged Defense: " + this.rangedDefenseManager.toString());
		System.out.println();
		System.out.println("Wage of " + this.wageManager.toString() + " crowns per day");
		System.out.println(this.actionPointsManager.toString() + " Action points per turn");
		System.out.println(this.headShotManager.toString() + "% Chance to hit head");
		System.out.println(this.fatigueRegManager.toString() + " Points of Fatigue Regained each Turn");
		System.out.println("Can see " + this.visionManager.toString() + " Tiles of Vision");
		System.out.println("Experience Rate is " + this.xpRateManager.toString() + "%");

		System.out.println();
	}
}
