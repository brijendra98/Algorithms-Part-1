import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private final double size;
  private final int numTrials;
  private final double mean;
  private final double stddev;
  private static final double CONFIDENCE_95 = 1.96;

  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if(trials<=0 || n<=0) {
      throw new IllegalArgumentException();
    }
    size = n;
    numTrials = trials;
    double tresholds[] = new double[trials];
    for(int i=0; i<trials; i++) {
      Percolation pr = new Percolation(n);
      // Keep opening random points until the grid percolates
      while(!pr.percolates()) {
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
    return mean - ((CONFIDENCE_95*stddev)/(Math.sqrt(numTrials)));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean + ((CONFIDENCE_95*stddev)/(Math.sqrt(numTrials)));
  }

  // test client (described below)
  public static void main(String[] args) {
    PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println("mean                    = " + stats.mean());
    System.out.println("stddev                  = " + stats.stddev());
    System.out.println("95% confidence interval = ["+ stats.confidenceLo()+", "+stats.confidenceHi()+"]");
  }
}
