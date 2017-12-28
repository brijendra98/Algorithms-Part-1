import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final int size;
  private boolean[][] grid;  // false is closed, true is open
  // UF2 has only virtual top while UF 2 has both top
  // and bottom. This is done to solve the backlash problem
  private final WeightedQuickUnionUF UF;
  private final WeightedQuickUnionUF UF2;
  private final int top;
  private final int bottom;
  private int numOpen;

  // create n-by-n grid, with all sites blocked
  public Percolation(int n) {
    if(n<=0) {
      throw new IllegalArgumentException();
    }
    size = n;
    numOpen = 0;
    // 0th position is unused in grid arrays, UF, UF2
    grid = new boolean[n+1][n+1];
    UF = new WeightedQuickUnionUF(n*n + 3);
    UF2 = new WeightedQuickUnionUF(n*n + 2);
    top = n*n+1;
    bottom = n*n+2;
  }

  // open site (row, col) if it is not open already
  public void open(int row, int col) {
    if(!inRange(row, col)) {
      throw new IllegalArgumentException();
    }
    if(!isOpen(row, col)) {
      numOpen++;
      grid[row][col] = true;
      if(row==1) {
        // Opening a site on top connects it to the virtual top
        UF.union(((row-1)*size + col), top);
        UF2.union(((row-1)*size + col), top);
      }
      if(row==size) {
        // Opening a site on bottom connects it to the virtual bottom
        UF.union(((row-1)*size + col), bottom);
      }
      // Connect the site being opened to all its open neighbours
      if(row-1>=1 && grid[row-1][col]) {
        UF.union(((row-1)*size + col), (((row-1)-1)*size + col));
        UF2.union(((row-1)*size + col), (((row-1)-1)*size + col));
      }
      if(row+1<=size && grid[row+1][col]) {
        UF.union(((row-1)*size + col), (((row+1)-1)*size + col));
        UF2.union(((row-1)*size + col), (((row+1)-1)*size + col));
      }
      if(col-1>=1 && grid[row][col-1]) {
        UF.union(((row-1)*size + col), ((row-1)*size + (col-1)));
        UF2.union(((row-1)*size + col), ((row-1)*size + (col-1)));
      }
      if(col+1<=size && grid[row][col+1]) {
        UF.union(((row-1)*size + col), ((row-1)*size + (col+1)));
        UF2.union(((row-1)*size + col), ((row-1)*size + (col+1)));
      }
    }
  }

  // is site (row, col) open?
  public boolean isOpen(int row, int col) {
    if(!inRange(row, col)) {
      throw new IllegalArgumentException();
    }
    return grid[row][col];
  }

  // is site (row, col) full?
  public boolean isFull(int row, int col) {
    if(!inRange(row, col)) {
      throw new IllegalArgumentException();
    }
    // A site i full if it is connected to the top
    return UF2.connected(((row-1)*size + col), top);
  }

  // number of open sites
  public int numberOfOpenSites() {
    return numOpen;
  }

  // does the system percolate?
  public boolean percolates() {
    // The system percolates if top and bottom are connected
    return UF.connected(top, bottom);
  }

  private boolean inRange(int row, int col) {
    return (row<=size && col<=size && row>=1 && col>=1);
  }
}
