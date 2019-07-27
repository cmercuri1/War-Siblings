/** War Siblings
 * Tester Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */

package run;

import java.util.Scanner;
import character.Character;

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
		
		char1 = new Character();

		char1.display();

		in.close();
	}
}
