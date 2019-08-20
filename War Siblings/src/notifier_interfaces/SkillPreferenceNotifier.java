/** War Siblings
 * SkillPreferenceNotifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package notifier_interfaces;

import event_classes.SkillPreferenceEvent;
import listener_interfaces.SkillPreferenceListener;

public interface SkillPreferenceNotifier {
	void addSkillPreferenceListener(SkillPreferenceListener s);

	void removeSkillPreferenceListener(SkillPreferenceListener s);

	void notifySkillPreferenceListeners(SkillPreferenceEvent s);

	void notifySkillPreferenceListener(SkillPreferenceListener s, SkillPreferenceEvent e);
}
