/** War Siblings
 * StarAttributeNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.StarAttributeEvent;
import listener_interfaces.StarAttributeListener;

public interface StarAttributeNotifier {
	void addStarAttributeListener(StarAttributeListener s);

	void removeStarAttributeListener(StarAttributeListener s);

	void notifyStarAttributeListeners(StarAttributeEvent s);

	void notifyStarAttributeListener(StarAttributeListener s, StarAttributeEvent e);
}
