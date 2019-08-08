/** War Siblings
 * PostDataListener
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package listener_interfaces;

import event_classes.PostDataEvent;

public interface PostDataListener {
	void onPostDataEvent(PostDataEvent p);
}
