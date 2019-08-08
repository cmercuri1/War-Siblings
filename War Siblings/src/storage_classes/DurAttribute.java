/** War Siblings
 * DurAttribute Class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import event_classes.ItemEvent;
import listener_interfaces.ItemListener;
import notifier_interfaces.ItemNotifier;

/** Special Attribute handling item durability */
public class DurAttribute extends BarAttribute implements ItemNotifier {
	protected ArrayList<ItemListener> itemListeners;
	
	public DurAttribute(double value) {
		super(value);
	}
	
	protected void setUpNotificationSystem() {
		super.setUpNotificationSystem();
		this.itemListeners = new ArrayList<ItemListener>();
	}
	
	public void alterCurrent(double value) {
		super.alterCurrent(value);
		this.notifyItemListeners(new ItemEvent(ItemEvent.Task.MODIFY_VALUE, this.getPercentage(),this));
	}
	
	protected void currentChecker() {
		if (this.alteredCurrentValue < MINIMUM) {
			this.alteredCurrentValue = MINIMUM;
			this.notifyItemListeners(new ItemEvent(ItemEvent.Task.BROKEN, this.getPercentage(),this));
		} else if (this.alteredCurrentValue > this.alteredMaxValue) {
			this.alteredCurrentValue = this.alteredMaxValue;
			this.notifyItemListeners(new ItemEvent(ItemEvent.Task.REPAIRED, this.getPercentage(),this));
		}
	}
	
	protected double getPercentage() {
		return this.alteredCurrentValue/this.alteredMaxValue;
	}

	@Override
	public void addItemListener(ItemListener i) {
		this.itemListeners.add(i);
	}

	@Override
	public void removeItemListener(ItemListener i) {
		this.itemListeners.remove(i);
	}

	@Override
	public void notifyItemListeners(ItemEvent i) {
		this.itemListeners.forEach(l -> l.onItemEvent(i));
	}

	@Override
	public void notifyItemListener(ItemListener i, ItemEvent e) {
		this.itemListeners.get(i).onItemEvent(e);		
	}
}
