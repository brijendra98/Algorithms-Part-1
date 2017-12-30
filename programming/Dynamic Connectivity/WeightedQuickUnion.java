import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class WeightedQuickUnion {

    // A site class which is a pair of parent node
    // and size of tree associated with it.
    private class Site {
        public int parent;
        public int size;

        // Constructor for a Site
        public Site(int p) {
            parent = p;
            size = 1;
        }
    }

    private Site[] arr;  // Array of Site data structure
    private int count;   // number of components

    // Initializes an array of n independent components with different IDs
    public WeightedQuickUnion(int n) {
        count=n;
        arr = new Site[n];
        for(int i=0; i<n; i++) {
            arr[i] = new Site(i);
        }
    }
    
    // Returns the number of components
    public int count() {
        return count;
    }
    // Returns the root for pth site
    public int find(int p) {
        return root(p);
    }

    // Returns true if pth and qth site are in the same component
    // i.e. they have the same roots
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // Determines the larger of p's and q's root, makes the
    // smaller a child of the larger hence putting them in 
    // the same component while having a shallower tree
    public void union(int p, int q) {
        if(connected(p, q)) {
            return;
        }

        if(arr[root(p)].size > arr[root(q)].size) {
            arr[root(p)].size += arr[root(q)].size;
            arr[root(q)].parent = root(p);
        }
        else {
            arr[root(q)].size += arr[root(p)].size;
            arr[root(p)].parent = root(q);
        }
        count--;
    }

    // private function to find the root of s by repeatedly finding parents
    private int root(int s) {
        int par = arr[s].parent;
        while(arr[par].parent != par) {
            par = arr[par].parent;
        }
        return par;
    }

    // Function which prints all internal details of our Data Structure
    public void print() {
        StdOut.println("---------------------------------------------------------------");
        for(int i=0; i<arr.length; i++) {
            StdOut.println("Index: "+i+"; Parent: "+arr[i].parent+"; Size: "+arr[i].size);
        }
        StdOut.println("---------------------------------------------------------------");
    }


    // Reads in a sequence of pairs of integers (between 0 and n-1)
    // where each integer represents some site. If the sites are in
    // different components, merge the two components and print the pair
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnion uf = new WeightedQuickUnion(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
            //uf.print();
        }
        StdOut.println(uf.count() + " components");
    }
}

