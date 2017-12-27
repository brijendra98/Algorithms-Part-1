import java.lang.*;
import java.io.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {
  private int size;
  //false is closed, true is open
  private boolean grid[][];
  private QuickFindUF UF;
  private QuickFindUF UF2;
  private int top;
  private int bottom;
  private int numOpen;

  // create n-by-n grid, with all sites blocked
  public Percolation(int n) {
    if(n<=0) {
      //throw java.lang.IllegalArgumentException();
    }
    size = n;
    numOpen = 0;
    grid = new boolean[n+1][n+1];
    UF = new QuickFindUF(n*n + 2);
    UF2 = new QuickFindUF(n*n + 1);
    top = n*n;
    bottom = n*n + 1;
  }

  // open site (row, col) if it is not open already
  public void open(int row, int col) {
    if(!isOpen(row, col)) {
      numOpen++;
      grid[row][col] = true;
      if(row==1) {
        UF.union(((row-1)*size + col), top);
        UF2.union(((row-1)*size + col), top);
      }
      if(row==size) {
        UF.union(((row-1)*size + col), bottom);
      }
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
    return grid[row][col];
  }

  // is site (row, col) full?
  public boolean isFull(int row, int col) {
    return UF2.connected(((row-1)*size + col), top);
  }

  // number of open sites
  public int numberOfOpenSites() {
    return numOpen;
  }

  // does the system percolate?
  public boolean percolates() {
    return UF.connected(top, bottom);
  }

  // test client (optional)
  public static void main(String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // Percolation myClass = new Percolation(5);
    // System.out.println(myClass.numberOfOpenSites());
    // System.out.println(myClass.percolates());
    // myClass.open(1,1);
    // System.out.println(myClass.numberOfOpenSites());
    // System.out.println(myClass.percolates());
    // myClass.open(1,3);
    // System.out.println(myClass.numberOfOpenSites());
    // System.out.println(myClass.percolates());
    // myClass.open(1,2);
    // System.out.println(myClass.numberOfOpenSites());
    // System.out.println(myClass.percolates());
    // myClass.open(2,2);
    // myClass.open(3,2);
    // myClass.open(5,2);
    // myClass.open(4,2);
    // System.out.println(myClass.numberOfOpenSites());
    // System.out.println(myClass.percolates());
  }
}
