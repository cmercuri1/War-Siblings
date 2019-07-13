package run;

import java.util.Scanner;
import character.Character;

public class Tester {

	public Tester() {
	}

	public void run() {
		Scanner in = new Scanner(System.in);
		Character char1;

		System.out.println("Random Background:");
		char1 = new Character();

		/*
		 * System.out.println("Enter Background:"); char1 = new
		 * Character(in.nextLine());
		 */

		char1.display();

		in.close();
	}
}
