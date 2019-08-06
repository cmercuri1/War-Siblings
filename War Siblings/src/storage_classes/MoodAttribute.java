/** War Siblings
 * MoodAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.Observer;
import global_managers.GlobalManager;

/** Special Attribute used in helping manage a character's mood */
public class MoodAttribute extends Attribute {
	private final static int MOODMAX = 100;
	private final static int MOODMIN = 0;

	private Mood currentMood;

	public MoodAttribute(double value, Observer o) {
		super(value, o);
		this.setMoodName();
	}

	protected void updateAltered() {
		super.updateAltered();

		if (this.alteredMaxValue > MOODMAX) {
			this.alteredMaxValue = MOODMAX;
		} else if (this.alteredMaxValue < MOODMIN) {
			this.alteredMaxValue = MOODMIN;
		}

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
