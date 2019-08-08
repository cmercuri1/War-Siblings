/** War Siblings
 * MoraleEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class MoraleEvent extends InfoEvent {
	public enum Task {ROLL_POSITIVE, ROLL_NEGATIVE, ROLL_SPECIAL};
	
	protected Task task;
	protected double infomation;
	
	public MoraleEvent(Task t, double info, Object src) {
		super(info, src);
		this.task = t;
	}

	public Task getTask() {
		return this.task;
	}
	
	public Double getInformation() {
		return this.infomation;
	}
}
