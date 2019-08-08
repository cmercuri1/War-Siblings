/** War Siblings
 * PostDataNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.PostDataEvent;
import listener_interfaces.PostDataListener;

public interface PostDataNotifier {
	void addPostDataListener(PostDataListener p);

	void removePostDataListener(PostDataListener p);

	void notifyPostDataListeners(PostDataEvent p);

	void notifyPostDataListener(PostDataListener p, PostDataEvent e);
}
