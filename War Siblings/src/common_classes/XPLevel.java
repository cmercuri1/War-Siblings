package common_classes;

public class XPLevel {
	protected int level;
	protected double xpReq;
	protected double totalXp;

	public XPLevel (int level, double xpReq, double totalXp) {
		this.level = level;
		this.xpReq = xpReq;
		this.totalXp = totalXp;
	}

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
