package run;

import java.util.Scanner;

import character.Character;
import generators.GeneratorManager;


public class Tester {

	public Tester() {
	}

	public void run() {
		Scanner in = new Scanner(System.in);

		Character char1 = new Character();
		char1.display();

		String s;

		System.out.println("");
		System.out.println("Enter weapon name:");
		s = in.nextLine();

		GeneratorManager.weapons.getWeapon(s).display();
		in.close();
	}
}
