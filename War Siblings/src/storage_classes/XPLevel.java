/** War Siblings
 * XPLevel class
 * Author: Christopher Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

/**
 * A storage class used for storing information about Experience point amounts
 * and requirements for each Level
 */
public class XPLevel {
	protected int level;
	protected double xpReq;
	protected double totalXp;

	public XPLevel(int level, double xpReq, double totalXp) {
		this.level = level;
		this.xpReq = xpReq;
		this.totalXp = totalXp;
	}

	/* Getters */

	public int getLevel() {
		return this.level;
	}

	public double getXpReq() {
		return this.xpReq;
	}

	public double getTotalXp() {
		return this.totalXp;
	}
}
