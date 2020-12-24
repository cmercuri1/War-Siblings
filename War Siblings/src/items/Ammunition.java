/** War Siblings
 * Ammunition
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import javax.swing.ImageIcon;

import attributes.BarAttribute;
import effect_classes.Modifier;
import event_classes.MultiValueAttributeEvent;
import listener_interfaces.MultiValueAttributeListener;
import storage_classes.ArrayList;

public class Ammunition extends Item implements MultiValueAttributeListener, Equipable {
	protected BarAttribute capacity;

	public Ammunition(String name, double value, String desc, double capacity) {
		super(name, value, desc);
		this.capacity = new BarAttribute(capacity);
		this.capacity.addAttributeListener(this);
		this.capacity.addMultiValueAttributeListener(this);
	}

	protected void setIcon() {
		this.image = new ImageIcon("res/Images/Items/Ammunition/" + this.name + ".png");
	}
	
	@Override
	public void onMultiValueAttributeEvent(MultiValueAttributeEvent m) {
		switch (m.getTask()) {
		case UPDATE_CURRENT:
			if (m.getInformation() == 0.0)
				// TODO
				break;
		}
	}

	public void drawAmmunition() {
		this.capacity.alterCurrent(-1);
	}

	public void drawAmmunition(double value) {
		this.capacity.alterCurrent(value);
	}

	@Override
	public ArrayList<Modifier> onEquipSituation() {
		ArrayList<Modifier> modifiers = new ArrayList<Modifier>();

		return modifiers;
	}

	@Override
	public ArrayList<Modifier> onBagSituation() {
		ArrayList<Modifier> modifiers = new ArrayList<Modifier>();

		return modifiers;
	}

}
