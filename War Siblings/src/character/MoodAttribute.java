package character;

import common_classes.Attribute;
import common_classes.Modifier;
import common_classes.Mood;
import global_managers.GlobalManager;

public class MoodAttribute extends Attribute {
	private final static int MOODMAX = 100;
	private final static int MOODMIN = 0;

	private Mood currentMood;

	public MoodAttribute(double value) {
		super(value);
		this.setMoodName();
	}

	protected void updateAltered() {
		double multi = 1;
		double add = 0;
		double finalAdd = 0;

		for (Modifier m : modifiers) {
			if (m.getIsMulti()) {
				multi *= (1 + m.getValue() / 100);
			} else {
				if (m.getFinalAdd()) {
					finalAdd += m.getValue();
				} else {
					add += m.getValue();
				}
			}
		}
		this.alteredMaxValue = multi * (this.originalMaxValue + add) + finalAdd;

		if (this.alteredMaxValue > MOODMAX) {
			this.alteredMaxValue = MOODMAX;
		} else if (this.alteredMaxValue < MOODMIN) {
			this.alteredMaxValue = MOODMIN;
		}

		this.setMoodName();
	}
	
	/** Intended to be called on a "Daily" basis */
	public void moodTrending() {
		for (Modifier m: this.modifiers) {
			m.alterValue(-1);
		}
		this.modifiers.removeIf(m->m.getValue()==0);
	}
	
	public Mood getCurrentMood() {
		return this.currentMood;
	}

	private void setMoodName() {
		this.currentMood = GlobalManager.moods.getAMood(alteredMaxValue);
	}

}
