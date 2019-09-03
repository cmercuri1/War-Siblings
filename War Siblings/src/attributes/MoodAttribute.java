/** War Siblings
 * MoodAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package attributes;

import effect_classes.Modifier;
import event_classes.AttributeEvent;
import global_managers.GlobalManager;
import storage_classes.Mood;

/** Special Attribute used in helping manage a character's mood */
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
		this.notifyAttributeListeners(new AttributeEvent(AttributeEvent.Task.UPDATE, this.alteredMaxValue, this));

		this.setMoodName();
	}

	/** Intended to be called on a "Daily" basis */
	public void moodTrending() {
		this.modifiers.forEach(m -> {
			if (m.getValue() > 0)
				m.alterValue(-1);
			else
				m.alterValue(1);
		});
		this.modifiers.removeIf(m -> m.getValue() == 0);
	}

	public int getMoraleState() {
		return this.currentMood.rollMoraleState();
	}

	// Getters

	public Mood getCurrentMood() {
		return this.currentMood;
	}

	private void setMoodName() {
		this.currentMood = GlobalManager.moods.getAMood(alteredMaxValue);
	}

	public String toString() {
		return this.currentMood.getName() + " (" + this.alteredMaxValue + "): " + this.currentMood.getDesc();
	}

}
