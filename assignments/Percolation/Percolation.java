import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {
  private int n;
  private int grid[][]; //0 is closed, 1 is open
  private QuickFindUF QF;

   public Percolation(int n) {              // create n-by-n grid, with all sites blocked
     if(n<=0) {
       throw java.lang.IllegalArgumentException();
     }
     this.n = n;
     grid = new int[n+1][n+1];
     //int counter = 1;
     for(int i=1; i<=n; i++) {
       for(int j=1; j<=n; j++) {
         grid[i][j] = 0;
         //grid[i][j] = counter;
         //counter++;
       }
     }
     QF = new QuickFindUF(n*n);
   }

   public void open(int row, int col) {     // open site (row, col) if it is not open already
     if(grid[row][col] == 0) {
       grid[row][col] = 1;
       if(row-1>=1 && grid[row-1][col]==1) {
         QF.union(((row-1)*n + col), (((row-1)-1)*n + col));
       }
       if(row+1<=n && grid[row+1][col]==1) {
         QF.union(((row-1)*n + col), (((row+1)-1)*n + col));
       }
       if(col-1>=1 && grid[row][col-1]==1) {
         QF.union(((row-1)*n + col), ((row-1)*n + (col-1)));
       }
       if(col+1<=n && grid[row][col+1]==1) {
         QF.union(((row-1)*n + col), ((row-1)*n + (col+1)));
       }
     }
   }
   public boolean isOpen(int row, int col) {// is site (row, col) open?
     return grid[row][col] == 1;
   }
   public boolean isFull(int row, int col) {// is site (row, col) full?

   }
   public int numberOfOpenSites() {         // number of open sites
   }
   public boolean percolates()              // does the system percolate?

   public static void main(String[] args)   // test client (optional)
}

// (1,1) (1,2)....
// (2,1)
// .
// .
// .
// (r-1)*n+c
