/** War Siblings
 * Effect_Modifier
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package effect_classes;

public class Effect_Modifier extends Effect {

	protected Modifier mod;

	public Effect_Modifier(String modName, double modValue) {
		this.mod = new Modifier(modName, modValue);
	}

	public Effect_Modifier(Modifier mod) {
		this.mod = mod;
	}

	public Modifier getMod() {
		return this.mod;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}
}
