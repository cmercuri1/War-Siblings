/** War Siblings
 * RetrievalNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.RetrieveEvent;
import listener_interfaces.RetrievalListener;

public interface RetrievalNotifier {
	void addRetrievalListener(RetrievalListener r);
	void removeRetrievalListener(RetrievalListener r);
	void notifyRetrievalListeners(RetrieveEvent r);
}
