package character;

import common_classes.Attribute;
import common_classes.Modifier;

public class WageAttribute extends Attribute {
	private final int VETERAN = 11;

	public WageAttribute(double value) {
		super(value);
	}
	
	public void levelWage(int Level) {
		if (Level > this.VETERAN) {
			this.highLevelUp(Level);
		} else {
			this.levelUp(Level);
		}
	}
	
	private void levelUp(int level) {
		this.newModifier(new Modifier("wage"+level, this.originalMaxValue*0.1, false, false));
	}
	
	private void highLevelUp(int level) {
		this.newModifier(new Modifier("wage"+level, 3, true, false));
	}

}
