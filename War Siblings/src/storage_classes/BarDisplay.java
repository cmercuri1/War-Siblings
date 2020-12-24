/** War Siblings
 * BarDisplay
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

public class BarDisplay {

	protected int outputValue;
	protected String outputString;
	protected int numStars;

	public BarDisplay(double cVal, String percent) {
		double maxValue = Math.max(cVal + 1, 100);
		this.outputValue = calcOutputVal(cVal, maxValue);
		this.outputString = ((Double) cVal).intValue() + percent;
	}

	public BarDisplay(double cVal, double mVal) {
		this.outputValue = calcOutputVal(cVal, mVal);
		this.outputString = ((Double) cVal).intValue() + "/" + ((Double) mVal).intValue();
	}
	
	public BarDisplay(double cVal, String damage, double mVal) {
		this.outputValue = calcOutputVal(mVal, 135);
		this.outputString = damage;
	}

	public BarDisplay(double cVal, double mVal, String over) {
		this.outputValue = calcOutputVal(cVal, mVal);
		this.outputString = over;
	}
	
	public BarDisplay(double cVal, String percent, int numStars) {
		double maxValue = Math.max(cVal + 1, 100);
		this.outputValue = calcOutputVal(cVal, maxValue);
		this.outputString = ((Double) cVal).intValue() + percent;
		this.numStars = numStars;
	}

	public BarDisplay(double cVal, double mVal, int numStars) {
		this.outputValue = calcOutputVal(cVal, mVal);
		this.outputString = ((Double) cVal).intValue() + "/" + ((Double) mVal).intValue();
		this.numStars = numStars;
	}
	
	public BarDisplay(double cVal, String damage, double mVal, int numStars) {
		this.outputValue = calcOutputVal(mVal, 135);
		this.outputString = damage;
		this.numStars = numStars;
	}

	public BarDisplay(double cVal, double mVal, String over, int numStars) {
		this.outputValue = calcOutputVal(cVal, mVal);
		this.outputString = over;
		this.numStars = numStars;
	}
	
	protected int calcOutputVal(double cVal, double mVal) {
		if (cVal < 0)
			cVal = 0;
		return ((Double) (cVal / mVal * 100)).intValue();
	}

	public int getOutputVal() {
		return this.outputValue;
	}

	public String getOutputString() {
		return this.outputString;
	}
	
	public int getStars() {
		return this.numStars;
	}

}
