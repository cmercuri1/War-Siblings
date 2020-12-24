/** War Siblings
 * Effect_Stage_2
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

import abilities.TemporaryInjury;
import event_classes.TriggeredEffectEvent;
import global_managers.GlobalManager;

public class Effect_Stage_2 extends Effect_Battle_Triggered {
	protected TemporaryInjury stage2;

	public Effect_Stage_2(String injuryName) {
		this.retrieveStage2(injuryName);
	}

	protected void retrieveStage2(String injuryName) {
		this.stage2 = GlobalManager.tempInjury.getInjury(injuryName + "_Stage_2");
	}

	@Override
	protected void triggerStart() {
	}

	@Override
	protected void triggerEnd() {
		this.notifyTriggeredEffectListeners(
				new TriggeredEffectEvent(TriggeredEffectEvent.Task.ADD_ABILITY, stage2, this));
		// this.notifyTriggeredEffectListeners(new
		// TriggeredEffectEvent(TriggeredEffectEvent.Task.REMOVE_ABILITY, stage2,
		// this));
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

}
