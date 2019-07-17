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

		char1.display();
		
		char1.getMm().makePositiveCheck();
		char1.display();
		
		char1.getMm().makeNegativeCheck();
		char1.display();
		
		char1.getMm().makeSpecialCheck();
		char1.display();
		in.close();
	}
}
