/** War Siblings
 * Effect_Battle_Situational
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import event_classes.BattleControlEvent;
import storage_classes.BattleConditions.Battlefield;
import storage_classes.BattleConditions.Foes;
import storage_classes.BattleConditions.TimeOfDay;

public abstract class Effect_Battle_Situational extends Effect_Battle_Triggered {
	protected Battlefield battlefield;
	protected Foes foes;
	protected TimeOfDay time;

	public Effect_Battle_Situational(Battlefield battlefield, Foes foes, TimeOfDay time) {
		this.battlefield = battlefield;
		this.foes = foes;
		this.time = time;
	}

	public Battlefield getBattlefield() {
		return this.battlefield;
	}

	public Foes getFoes() {
		return this.foes;
	}

	public TimeOfDay getTime() {
		return this.time;
	}

	@Override
	public void onBattleControlEvent(BattleControlEvent b) {
		if (((this.battlefield != null) && b.getInformation().getTerrain().equals(battlefield))
				|| ((this.foes != null) && b.getInformation().getEnemies().contains(this.foes))
				|| ((this.time != null) && b.getInformation().getTime().equals(this.time))) {
			switch (b.getTask()) {
			case END_BATTLE:
				this.triggerEnd();
				break;
			case START_BATTLE:
				this.triggerStart();
				break;
			}
		}
	}

}
