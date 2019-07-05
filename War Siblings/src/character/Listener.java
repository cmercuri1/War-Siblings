package character;

import common_classes.Modifier;

public class Listener {
	private AttributeManager target;

	public Listener(AttributeManager target) {
		this.target = target;
	}

	public void applyAbilityAttibute(String manager, Modifier mod) {
		try {
			this.target.getAttribute(manager).newModifier(mod);
		} catch (NullPointerException nu) {
			System.out.println(manager);
		}
		
	}

	public void removeAbilityAttribute(String manager, Modifier mod) {
		this.target.getAttribute(manager).removeModifier(mod);
	}
}
