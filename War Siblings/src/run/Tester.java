package run;

import java.util.Scanner;
import global_managers.GlobalManager;
import character.Character;

public class Tester {

	public Tester() {
	}

	public void run() {
		Scanner in = new Scanner(System.in);
		String s;

		/*
		 * System.out.println("Enter Weapon name:"); s = in.nextLine();
		 * GeneratorManager.weapons.display(s);
		 * 
		 * System.out.println("Enter Shield name:"); s = in.nextLine();
		 * GeneratorManager.shields.display(s);
		 * 
		 * System.out.println("Enter Helm name:"); s = in.nextLine();
		 * GeneratorManager.headgears.display(s);
		 * 
		 * System.out.println("Enter Body Armor name:"); s = in.nextLine();
		 * GeneratorManager.bodyArmors.display(s);
		 */

		/*
		 * System.out.println("Enter Background name:"); s = in.nextLine(); Character
		 * char1 = new Character(s); char1.display();
		 */

		System.out.println("Random Background:");
		Character char1 = new Character();
		char1.display();

		in.close();
	}
}
