/** War Siblings
 * Effect_Storage
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

public class Effect_Storage {

	protected String effectName;
	protected String effectType;

	public Effect_Storage(String eName, String eType) {
		this.effectName = eName;
		this.effectType = eType;
	}

	public String getEffectName() {
		return this.effectName;
	}

	public String getEffectType() {
		return this.effectType;
	}

	public void display() {
		System.out.println(this.effectName + ": " + this.effectType);
	}

}
