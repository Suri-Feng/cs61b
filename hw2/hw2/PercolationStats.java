package hw2;

import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {

    private int N;
    private int T;
    private double[] fractions;
    private double fractSum;
    private double miu;
    private double sigma;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N == 0 || T == 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        int row;
        int col;
        int size;
        double fract;
        fractSum = 0;
        fractions = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()){
                row = StdRandom.uniform(N);
                col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            size = p.numberOfOpenSites();
            fract = Double.valueOf(size)/(N*N);
            fractSum += fract;
            fractions[i] = fract;
        }
        miu = fractSum/T;
        double tempSum = 0;
        for (double f : fractions) {
            tempSum += Math.pow(f - miu, 2);
        }
        sigma = Math.sqrt(tempSum/ (T-1));
    }


    // sample mean of percolation threshold
    public double mean() {
        return miu;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return sigma;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return miu - 1.96*sigma/Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return miu + 1.96*sigma/Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(100,50, pf);
        System.out.println("mean: " + ps.mean());
        System.out.println("stddev: " + ps.stddev());
        System.out.println("confidenceLow: " + ps.confidenceLow());
        System.out.println("confidenceHigh: " + ps.confidenceHigh());
    }
}