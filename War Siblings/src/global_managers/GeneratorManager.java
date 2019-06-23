package global_managers;

public class GeneratorManager {
	public static BackgroundManager characters;
	public static BodyArmorManager bodyArmors;
	public static HeadgearManager headgears;
	public static ShieldManager shields;
	public static WeaponManager weapons;
	
	static {
		characters = new BackgroundManager(); 
		bodyArmors = new BodyArmorManager();
		headgears = new HeadgearManager();
		shields = new ShieldManager();
		weapons = new WeaponManager();
	}
	
	public GeneratorManager() {
	}

}
