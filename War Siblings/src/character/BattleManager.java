package character;

import common_classes.Observer;
import common_classes.Observeree;

public class BattleManager extends Observeree {

	public BattleManager(Observer o) {
		this.setUpObservers();
		this.registerObserver(o);
	}

}
