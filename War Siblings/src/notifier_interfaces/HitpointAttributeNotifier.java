/** War Siblings
 * HitpointAttributeNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.HitpointAttributeEvent;
import listener_interfaces.HitpointAttributeListener;

public interface HitpointAttributeNotifier {
	void addHitpointAttributeListener(HitpointAttributeListener h);

	void removeHitpointAttributeListener(HitpointAttributeListener h);

	void notifyHitpointAttributeListeners(HitpointAttributeEvent h);

	void notifyHitpointAttributeListener(HitpointAttributeListener h, HitpointAttributeEvent e);
}
