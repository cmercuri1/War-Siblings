/** War Siblings
 * MoraleTriggerNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.MoraleTriggerEvent;
import listener_interfaces.MoraleTriggerListener;

public interface MoraleTriggerNotifier {
	void addMoraleListener(MoraleTriggerListener m);

	void removeMoraleListener(MoraleTriggerListener m);

	void notifyMoraleListeners(MoraleTriggerEvent m);

	void notifyMoraleListener(MoraleTriggerListener m, MoraleTriggerEvent e);
}
