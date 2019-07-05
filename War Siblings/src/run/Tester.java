package run;

import java.util.Scanner;
import character.Character;

public class Tester {

	public Tester() {
	}

	public void run() {
		Scanner in = new Scanner(System.in);

		System.out.println("Random Background:");
		Character char1 = new Character();
		char1.display();

		in.close();
	}
}
