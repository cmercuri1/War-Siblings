/** War Siblings
 * MoraleChangeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.MoraleChangeNotifier;
import storage_classes.MoraleState;

public class MoraleChangeEvent extends InfoEvent {
	public enum Task {
		INITIAL, CHANGE, RESET, OVERRIDE, SET
	};

	protected Task task;
	protected MoraleState information;
	protected double bestMoraleState;
	protected MoraleChangeNotifier source;

	public MoraleChangeEvent(double info, MoraleChangeNotifier src) {
		super(info, src);
		this.task = Task.INITIAL;
		this.bestMoraleState = info;
		this.source = src;
	}

	public MoraleChangeEvent(Task t, MoraleState info, MoraleChangeNotifier src) {
		super(info, src);
		this.task = t;
		this.information = info;
		this.source = src;
	}

	public Task getTask() {
		return this.task;
	}

	public Double getBestMoraleState() {
		return this.bestMoraleState;
	}

	public MoraleState getInformation() {
		return this.information;
	}

	public MoraleChangeNotifier getSource() {
		return this.source;
	}

}
