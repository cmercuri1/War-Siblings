package run;

import java.util.Scanner;

import character.Character;
import generators.WeaponGenerator;

public class Tester {

	public Tester() {

	}

	public void run() {
		Scanner in = new Scanner(System.in);

		Character char1 = new Character();
		WeaponGenerator wm = new WeaponGenerator();
		char1.display();

		String s;

		System.out.println("");
		System.out.println("Enter weapon name:");
		s = in.nextLine();

		wm.getWeapon(s).display();
		in.close();
	}
}
