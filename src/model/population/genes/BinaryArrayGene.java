package model.population.genes;

public class BinaryArrayGene implements Gene {
	
	private int size;
	private boolean[] bits;
	private int xmax;
	private int xmin;
	
	public BinaryArrayGene(int xmax, int xmin, int tolerance) {
		this.xmax = xmax;
		this.xmin = xmin;
		size = calculateLength(tolerance);
		bits = new boolean[size];		
	}

	private int calculateLength(int tolerance) {
		double value = 1 + ((xmax - xmin) / (double)tolerance);
		return (int)Math.ceil(Math.log10(value) / Math.log10(2.));
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void mutate(int index) {
		if (index >= size) {
			throw new ArrayIndexOutOfBoundsException("You fucked up.");
		}
		bits[index] = !bits[index];
	}

	@Override
	public int intValue() {
		return (int)doubleValue();
	}

	@Override
	public double doubleValue() {
		return xmin + (binToDec() * (xmax - xmin) / (Math.pow(2, size) - 1));
	}
	
	private double binToDec() {
		double result = 0;
		for (boolean bit : bits) {
		    result = result * 2 + (bit ? 1 : 0);
		}
		return result;
	}

}
