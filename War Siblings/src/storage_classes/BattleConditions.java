/** War Siblings
 * BattleConditions
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

public class BattleConditions {

	public enum TimeOfDay {
		DAY, DUSK, NIGHT, DAWN
	};
	
	public enum Battlefield {
		SWAMP, FOREST, SNOW, GRASSLAND
	};
	
	public enum Foes {
		HUMAN, BEASTS, GREENSKINS, UNDEAD
	}

	protected ArrayList<Foes> enemies; // TODO IMPLEMENT ENEMIES CLASS??
	protected Battlefield terrain;
	protected TimeOfDay time;

	public BattleConditions(TimeOfDay tod, Battlefield terrain, Foes...enemy) {
		this.time = tod;
		this.terrain = terrain;
		for (int i = 0; i < enemy.length; i++) {
			this.enemies.add(enemy[i]);
		}
	}

	public ArrayList<Foes> getEnemies() {
		return this.enemies;
	}

	public Battlefield getTerrain() {
		return this.terrain;
	}

	public TimeOfDay getTime() {
		return this.time;
	}

}
