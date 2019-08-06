/** War Siblings
 * AttackAttribute class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

/** Special Attribute used in generating chances to hit for attacks */
public class AttackAttribute extends Attribute {
	private final static int MAXCAP = 95;
	private final static int MINCAP = 5;

	public AttackAttribute(double value) {
		super(value, null);
	}

	protected void updateAltered() {
		super.updateAltered();

		if (this.alteredMaxValue > MAXCAP) {
			this.alteredMaxValue = MAXCAP;
		} else if (this.alteredMaxValue < MINCAP) {
			this.alteredMaxValue = MINCAP;
		}
	}
}
