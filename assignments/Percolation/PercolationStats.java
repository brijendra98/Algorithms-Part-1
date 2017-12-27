import java.lang.*;
import java.io.*;
import edu.princeton.cs.algs4.*;

public class PercolationStats {
  private double size;
  private int numTrials;
  private double tresholds[];
  private double mean;
  private double stddev;

  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if(trials<=0 || n<=0) {
      return new IllegalArgumentException();
    }
    size=n;
    numTrials=trials;
    tresholds = new double[trials];
    for(int i=0; i<trials; i++) {
      Percolation pr = new Percolation(n);
      while(!pr.percolates()) {
        // Open a random point
        int pt_r = StdRandom.uniform(1, n+1);
        int pt_c = StdRandom.uniform(1, n+1);
        pr.open(pt_r, pt_c);
      }
      tresholds[i] = pr.numberOfOpenSites()/(size*size);
    }
    mean = StdStats.mean(tresholds);
    stddev = StdStats.stddev(tresholds);
  }

  // sample mean of percolation threshold
  public double mean() {
    return mean;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return stddev;
  }

  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean - ((1.96*stddev)/(Math.sqrt(numTrials)));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean + ((1.96*stddev)/(Math.sqrt(numTrials)));
  }

  // test client (described below)
  public static void main(String[] args) {
    PercolationStats stats = new PercolationStats(200, 100);
    System.out.println("mean = " + stats.mean());
    System.out.println("stddev = " + stats.stddev());
    System.out.println("95% confidence interval = ["+ stats.confidenceLo()+", "+stats1.confidenceHi()+"]");
  }
}
