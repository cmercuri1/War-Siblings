/** War Siblings
 * Tester Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */

package run;

import java.util.Scanner;
import character.Character;
import event_classes.EventObject;
import event_classes.EventType;
import event_classes.Target;
import global_managers.GlobalManager;

/** A Class for testing elements of the War Siblings project */
public class Tester {

	/**
	 * Constructor: Currently does nothing else
	 */
	public Tester() {
	}

	/** run(): Starts everything running when called, is altered during each test */
	public void run() {
		Scanner in = new Scanner(System.in);
		Character char1;

		System.out.println("");
		char1 = new Character("Apprentice");

		char1.display();

		char1.notifyObservers(new EventObject(Target.BATTLE, EventType.START_BATTLE, null, null));
		char1.display();
		char1.notifyObservers(new EventObject(Target.BATTLE, EventType.ROLL_NEGATIVE, ((double)0), null));
		char1.display();
		char1.notifyObservers(new EventObject(Target.BATTLE, EventType.ROLL_POSITIVE, ((double)50), null));
		char1.display();
		char1.notifyObservers(new EventObject(Target.BATTLE, EventType.END_BATTLE, null, null));
		char1.display();

		in.close();
	}
}
