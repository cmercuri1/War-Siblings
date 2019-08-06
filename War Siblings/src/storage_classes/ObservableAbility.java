/** War Siblings
 * ObservableAbility class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

import storage_classes.ArrayList;

import event_classes.EventObject;
import event_classes.Observee;
import event_classes.Observer;

/** Extends ability so that it can use be observed */
public class ObservableAbility extends Ability implements Observee {
	protected ArrayList<Observer> observerList;

	public ObservableAbility(String name, String desc, ArrayList<Effect> effects, Observer o) {
		super(name, desc, effects);
		this.observerList = new ArrayList<Observer>();
		this.registerObserver(o);
	}

	public ObservableAbility(String name, String desc, ArrayList<Effect> effects) {
		super(name, desc, effects);
		this.observerList = new ArrayList<Observer>();
	}

	public ObservableAbility(String name, ArrayList<Effect> effects, Observer o) {
		super(name, effects);
		this.observerList = new ArrayList<Observer>();
		this.registerObserver(o);
	}

	public ObservableAbility(String name, ArrayList<Effect> effects) {
		super(name, effects);
		this.observerList = new ArrayList<Observer>();
	}

	@Override
	public void registerObserver(Observer o) {
		this.observerList.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		this.observerList.remove(o);
	}

	@Override
	public void notifyObservers(EventObject event) {
		this.observerList.forEach(o -> o.onEventHappening(event));
	}

	@Override
	public void notifyObserver(Observer o, EventObject event) {
		this.observerList.get(o).onEventHappening(event);
	}

}
