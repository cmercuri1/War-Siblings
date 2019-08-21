/** War Siblings
 * MoraleChangeEvent
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package event_classes;

import notifier_interfaces.MoraleChangeNotifier;
import storage_classes.MoraleState;

public class MoraleChangeEvent extends InfoEvent {

	protected MoraleState information;
	protected MoraleChangeNotifier source;
	
	public MoraleChangeEvent(MoraleState info, MoraleChangeNotifier src) {
		super(info, src);
		this.information = info;
		this.source = src;
	}

	public MoraleState getInformation() {
		return this.information;
	}

	public MoraleChangeNotifier getSource() {
		return this.source;
	}

}
