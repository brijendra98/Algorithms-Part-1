import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class QuickFind {
    private int[] id;    // id[i] = component identifier of i
    private int count;   // number of components

    // Initializes an array of n independent components with different IDs
    public QuickFind(int n) {
        count=n;
        id = new int[n];
        for(int i=0; i<n; i++) {
            id[i] = i;
        }
    }
    
    // Returns the number of components
    public int count() {
        return count;
    }
    // Returns the identifier for pth site
    public int find(int p) {
        return id[p];
    }

    // Returns true if pth and qth site are in the same component
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // Changes the IDs of all sites having id[p] to id[q]
    // hence putting all of them in a single component
    public void union(int p, int q) {
        int len = id.length;
        int old_id = id[p];
        for(int i=0; i<len; i++) {
            if(id[i] == old_id) {
                id[i] = id[q];
            }
        }
        count--;
    }

    // Reads in a sequence of pairs of integers (between 0 and n-1)
    // where each integer represents some site. If the sites are in
    // different components, merge the two components and print the pair
    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFind uf = new QuickFind(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}

