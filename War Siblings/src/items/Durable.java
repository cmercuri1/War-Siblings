/** War Siblings
 * Durable
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import attributes.DurAttribute;
import listener_interfaces.ItemListener;
import listener_interfaces.MultiValueAttributeListener;

public interface Durable extends ItemListener, MultiValueAttributeListener {
	DurAttribute getDurability();
	
	/** damageRepair: method for damaging/repairing an item */
	void damageRepair(double value);
}
