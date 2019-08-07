/** War Siblings
 * ItemEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

public class ItemEvent extends InfoEvent {
	public enum Task {
		BROKEN, MODIFY_VALUE, REPAIRED};
	
	protected Task task;
	protected double information;
	
	public ItemEvent(Task t, double info, Object src) {
		super(info, src);
		this.task = t;
	}
	
	public Task getTask() {
		return this.task;
	}
	
	public Double getInformation() {
		return this.information;
	}

}
