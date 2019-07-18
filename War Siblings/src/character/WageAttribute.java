package character;

import common_classes.Attribute;
import common_classes.Modifier;
import global_managers.GlobalManager;

public class WageAttribute extends Attribute {
	private final int VETERAN = 11;

	public WageAttribute(double value) {
		super(value);
		
		int temp = GlobalManager.rollBetween(0, 10);
		if (temp != 5) {
			this.addModifier(new Modifier("Random Wage mod", (temp - 5), true, false, true));
		}
	}
	
	public void levelWage(int Level) {
		if (Level > this.VETERAN) {
			this.highLevelUp(Level);
		} else {
			this.levelUp(Level);
		}
	}
	
	private void levelUp(int level) {
		this.addModifier(new Modifier("wage"+level, this.originalMaxValue*0.1, false, false, true));
	}
	
	private void highLevelUp(int level) {
		this.addModifier(new Modifier("wage"+level, 3, true, false, true));
	}

}
