/** War Siblings
 * Durable
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package items;

import listener_interfaces.ItemListener;
import listener_interfaces.MultiValueAttributeListener;
import storage_classes.DurAttribute;

public interface Durable extends ItemListener, MultiValueAttributeListener {
	DurAttribute getDurability();
	
	/** damageRepair: method for damaging/repairing an item */
	void damageRepair(double value);
}
