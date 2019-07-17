package common_classes;

import java.util.ArrayList;

public class PerminentInjury extends Ability {
	protected boolean contentInReserve;	

	public PerminentInjury(String name, String desc, ArrayList<Effect> effects, boolean contentInReserve) {
		super(name, effects);
		this.desc = desc;
		this.contentInReserve = contentInReserve;
	}

	public boolean isContentInReserve() {
		return this.contentInReserve;
	}

}
