/** War Siblings
 * BarDisplay
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package storage_classes;

public class BarDisplay {

	protected int outputValue;
	protected String outputString;

	public BarDisplay(double cVal, String percent) {
		double maxValue = Math.max(cVal + 1, 100);
		this.outputValue = calcOutputVal(cVal, maxValue);
		this.outputString = cVal + percent;
	}

	public BarDisplay(double cVal, double mVal) {
		this.outputValue = calcOutputVal(cVal, mVal);
		this.outputString = cVal + "/" + mVal;
	}
	
	public BarDisplay(double cVal, String damage, double mVal) {
		this.outputValue = calcOutputVal(mVal, 150);
		this.outputString = cVal + " - " + mVal;
	}

	public BarDisplay(double cVal, double mVal, String over) {
		this.outputValue = calcOutputVal(cVal, mVal);
		this.outputString = over;
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

}
