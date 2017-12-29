import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class QuickUnion {
    private int[] id;    // id[i] = parent of i
    private int count;   // number of components

    // Initializes an array of n independent components with different IDs
    public QuickUnion(int n) {
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
    // Returns the parent for pth site
    public int find(int p) {
        return root(p);
    }

    // Returns true if pth and qth site are in the same component
    // i.e. they have the same roots
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // Makes q's root, a child of p's root hence putting them
    // in the same component 
    public void union(int p, int q) {
        id[root(q)] = root(p);
        count--;
    }

    // private function to find the root of s by repeatedly finding parents
    private int root(int s) {
        int parent = id[s];
        while(id[parent] != parent) {
            parent = id[parent];
        }
        return parent;
    }

    // private function to print the current state of the ID array
    private void print() {
        for(int i=0; i<id.length; i++) {
            System.out.print(id[i] + " ");
        }
        System.out.println();
    }

    // Reads in a sequence of pairs of integers (between 0 and n-1)
    // where each integer represents some site. If the sites are in
    // different components, merge the two components and print the pair
    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnion uf = new QuickUnion(n);
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

