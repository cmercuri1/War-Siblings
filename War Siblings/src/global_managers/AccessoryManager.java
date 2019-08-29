/** War Siblings
 * AccessoryManager
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package global_managers;

import org.json.simple.JSONObject;

import items.ResolveNecklace;
import storage_classes.ArrayList;

public class AccessoryManager extends TwoListGlobalManager {
	protected ArrayList<ResolveNecklace> resolveNecklaces;

	public AccessoryManager() {
		super("res/game_data/RegularGearData.json", "Accessory", "Resolve Necklaces",
				"res/game_data/RegularGearData.json", "Accessory", "Resolve Necklaces");
	}

	@Override
	protected void addItem(JSONObject o) {
		this.resolveNecklaces.add(new ResolveNecklace((String) o.get("Name"), (Long) o.get("Value"),
				(String) o.get("Description"), (Long) o.get("Resolve Boost")));
	}

	@Override
	protected void instantiate() {
		if (this.resolveNecklaces == null) {
			this.resolveNecklaces = new ArrayList<ResolveNecklace>();
		}
	}

	@Override
	protected void addItem2(JSONObject o) {
		// TODO Auto-generated method stub

	}

}
