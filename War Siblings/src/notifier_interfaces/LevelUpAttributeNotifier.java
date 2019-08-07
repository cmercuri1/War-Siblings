/** War Siblings
 * LevelUpAttributeNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.LevelUpAttributeEvent;
import listener_interfaces.LevelUpAttributeListener;

public interface LevelUpAttributeNotifier {
	void addLevelUpAttributeListener(LevelUpAttributeListener l);
	void removeLevelUpAttributeListener(LevelUpAttributeListener l);
	void notifyLevelUpAttributeListeners(LevelUpAttributeEvent l);
}
