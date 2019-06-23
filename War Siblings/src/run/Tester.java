package run;

import java.util.Scanner;
import global_managers.GeneratorManager;


public class Tester {

	public Tester() {
	}

	public void run() {
		Scanner in = new Scanner(System.in);

		String s;
		System.out.println("Enter weapon name:");
		s = in.nextLine();

		GeneratorManager.weapons.getWeapon(s).display();
		in.close();
	}
}
