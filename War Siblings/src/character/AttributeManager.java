package character;

import java.util.ArrayList;
import java.util.Random;

import common_classes.Attribute;
import common_classes.Modifier;

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

		this.wageManager = new Attribute((double) bg.baseWage);
		this.xpRateManager = new Attribute((double) bg.xpRate);
		this.levelManager = new Attribute((double) bg.lev.getRand());
		this.actionPointsManager = new Attribute((double) bg.getActionPoints());
		this.headShotManager = new Attribute((double) bg.getHeadShot());
		this.fatigueRegManager = new Attribute((double) bg.getFatRegain());
		this.visionManager = new Attribute((double) bg.getVision());
	}

	/** Randomly assigns stars/talents towards up to 3 attributes */
	private void assignStars() {
		Random rand = new Random();
		ArrayList<StarAttribute> rng = new ArrayList<StarAttribute>();

		rng.add(hitpointManager);
		rng.add(fatigueManager);
		rng.add(resolveManager);
		rng.add(initiativeManager);
		rng.add(meleeSkillManager);
		rng.add(rangedSkillManager);
		rng.add(meleeDefenseManager);
		rng.add(rangedDefenseManager);

		for (int i = 0; i < 3; i++) {
			int j = rand.nextInt(rng.size());
			rng.get(j).setNumStars();
			rng.remove(j);
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
		System.out.println("");

		System.out.print(this.hitpointManager.getAlteredCurrentValue() + "/" + this.hitpointManager.getAlteredValue()
				+ " Hitpoints");
		if (this.hitpointManager.getNumStars() > 0) {
			System.out.print(" and has " + this.hitpointManager.getNumStars() + " stars");
		}
		System.out.println("");
		System.out.print(this.fatigueManager.getAlteredCurrentValue() + "/" + this.fatigueManager.getAlteredValue()
				+ " Fatigue");
		if (this.fatigueManager.getNumStars() > 0) {
			System.out.print(" and has " + this.fatigueManager.getNumStars() + " stars");
		}
		System.out.println("");
		System.out.print(this.resolveManager.getAlteredValue() + " Resolve");
		if (this.resolveManager.getNumStars() > 0) {
			System.out.print(" and has " + this.resolveManager.getNumStars() + " stars");
		}
		System.out.println("");
		System.out.print(this.initiativeManager.getAlteredValue() + " Initiative");
		if (this.initiativeManager.getNumStars() > 0) {
			System.out.print(" and has " + this.initiativeManager.getNumStars() + " stars");
		}
		System.out.println("");
		System.out.print(this.meleeSkillManager.getAlteredValue() + " Melee Skill");
		if (this.meleeSkillManager.getNumStars() > 0) {
			System.out.print(" and has " + this.meleeSkillManager.getNumStars() + " stars");
		}
		System.out.println("");
		System.out.print(this.rangedSkillManager.getAlteredValue() + " Ranged Skill");
		if (this.rangedSkillManager.getNumStars() > 0) {
			System.out.print(" and has " + this.rangedSkillManager.getNumStars() + " stars");
		}
		System.out.println("");
		System.out.print(this.meleeDefenseManager.getAlteredValue() + " Melee Defense");
		if (this.meleeDefenseManager.getNumStars() > 0) {
			System.out.print(" and has " + this.meleeDefenseManager.getNumStars() + " stars");
		}
		System.out.println("");
		System.out.print(this.rangedDefenseManager.getAlteredValue() + " Ranged Defense");
		if (this.rangedDefenseManager.getNumStars() > 0) {
			System.out.print(" and has " + this.rangedDefenseManager.getNumStars() + " stars");
		}
		System.out.println("\n");

		System.out.println("Wage of " + this.wageManager.getAlteredValue() + " crowns per day");
		System.out.println(this.actionPointsManager.getAlteredValue() + " Action points per turn");
		System.out.println(this.headShotManager.getAlteredValue() + "% Chance to hit head");
		System.out.println(this.fatigueRegManager.getAlteredValue() + " Points of Fatigue Regained each Turn");
		System.out.println("Can see " + this.visionManager.getAlteredValue() + " Tiles of Vision");
		System.out.println("Experience Rate is " + this.xpRateManager.getAlteredValue() / 100);

		System.out.println("");
	}
}
