package items;

public class Armor extends EquipItem {
	String type;

	public Armor(String name, double value, String desc, double dura, double fatRed, String type) {
		super(name, value, desc, dura, fatRed);
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

	public void display() {
		System.out.println(this.name);
		System.out.println(this.durability.getAlteredCurrentValue() + "/" + this.durability.getAlteredValue());
		System.out.println(this.desc);
		System.out.println("Reduces Max Fatigue by " + this.fatigueRed.getAlteredValue());
		System.out.println("");
	}

}
