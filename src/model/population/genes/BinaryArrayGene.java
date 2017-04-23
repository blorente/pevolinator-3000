package model.population.genes;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class BinaryArrayGene implements Gene {
	
	private int size;
	private boolean[] bits;
	private double xmax;
	private double xmin;
	
	public BinaryArrayGene(double xmax, double xmin, double tolerance) {
		this.xmax = xmax;
		this.xmin = xmin;
		size = calculateLength(tolerance);
		bits = new boolean[size];
		bitsToZero();
	}

	// Private copy constructor for createCopy()
	private BinaryArrayGene(int size, double xmax, double xmin) {
	    this.xmax = xmax;
	    this.xmin = xmin;
	    this.size = size;
        bits = new boolean[size];
        bitsToZero();
    }

    private void bitsToZero() {
        for (int i = 0; i < size; i++) {
            bits[i] = false;
        }
    }

    private int calculateLength(double tolerance) {
		double value = 1 + ((xmax - xmin) / tolerance);
		return (int)Math.ceil(Math.log10(value) / Math.log10(2.));
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void mutateSelfGranular(double mutationPercent) {
		for (int i = 0; i < size; i++) {
			if(Math.random() < mutationPercent)
				bits[i] = !bits[i];
        }
	}

    @Override
    public void mutateSelfGranular(double mutationPercent, Random seed) {
        for (int i = 0; i < size; i++) {
            bits[i] = seed.nextDouble() < mutationPercent;
        }
    }

    @Override
    public void copyFrom(Gene source, int startIndex) {
        if (startIndex >= size) {
            throw new ArrayIndexOutOfBoundsException("You fucked up.");
        }
        for (int i = startIndex; i < size; i++) {
            bits[i] = source.binaryValue()[i];
        }
    }

    @Override
	public int intValue() {
		return (int)doubleValue();
	}

	@Override
	public double doubleValue() {
		return xmin + (binToDec() * (xmax - xmin) / (Math.pow(2, size) - 1));
	}

    @Override
    public boolean[] binaryValue() {
        return bits;
    }

    private double binToDec() {
		double result = 0;
		for (boolean bit : bits) {
		    result = result * 2 + (bit ? 1 : 0);
		}
		return result;
	}

	@Override
    public Gene createCopy(Gene gene) {
	    BinaryArrayGene ret = new BinaryArrayGene(gene.size(), gene.getXMax(), gene.getXMin());
	    ret.copyFrom(gene, 0);
	    return ret;
    }

    @Override
    public double getXMax() {
        return xmax;
    }

    @Override
    public double getXMin() {
        return xmin;
    }

    @Override
    public String toString() {
	    StringBuilder res = new StringBuilder();
	    /*
	    for (int i = 0; i < size; i++) {
	        res.append(bits[i] ? "1" : "0");
        }
        res.append("(").append(doubleValue()).append(")");
        */
	    DecimalFormat df = new DecimalFormat("#.###"); 
	    res.append(df.format(doubleValue()));
        return res.toString();
    }
}
