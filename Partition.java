/** Implementation of tree-based set partitions with fast union/find.
 *  Adapted from Shaffer/OpenDSA text.
 */
public class Partition {

    /** The size of the partition.  */
    private int size;

    /** The array holding the parents for each node.  */
    private int[] parent;
    
    /** The array holding the parents for each node.  */
    private int[][] diff;

    /** The array holding the weights (size) of the tree for each node. */
    private int[] weight;

    /** Create a partition of singleton sets of the given size.
     *  @param num the starting size of the partition
     */
    public Partition(int num) {
        this.size = num;
        this.parent = new int[num];
        this.weight = new int[num];
        this.diff = new int[num][3];
        for (int i = 0; i < this.size; i++) {
            this.parent[i] = -1;
            this.weight[i] = 1;
            this.diff[i][0] = 0;
            this.diff[i][1] = 0;
            this.diff[i][2] = 0;
        }
    }

    /** Weighted union of the sets containing two nodes, if different.
     *  @param a the first node
     *  @param b the second node
     *  @return boolean weather or not they unioned
     */
    boolean union(int a, int b) {
        int root1 = this.find(a);     // Find root of node a
        int root2 = this.find(b);     // Find root of node b
        if (root1 != root2) {    // Merge with weighted union
            if (this.weight[root2] > this.weight[root1]) {
                this.parent[root1] = root2;
                this.weight[root2] += this.weight[root1];
            } else {
                this.parent[root2] = root1;
                this.weight[root1] += this.weight[root2];
            }
            return true;
        }
        return false;
    }
    
    /** Weighted union of the sets containing two nodes, if different. with weight
     *  @param a the first node
     *  @param b the second node
     *  @return boolean weather or not they unioned
     */
    boolean unionWW(int a, int b, double k) {
        int root1 = this.find(a);     // Find root of node a
        int root2 = this.find(b);     // Find root of node b
        boolean suc = false;
        
        if (diff1(root1, root2) <= Math.min(diff[root1][0], diff[root2][0]) + k/(this.weight[root1] + this.weight[root2])
         && diff2(root1, root2) <= Math.min(diff[root1][1], diff[root2][1]) + k/(this.weight[root1] + this.weight[root2]
         && diff3(root1, root2) <= Math.min(diff[root1][2], diff[root2][2]) + k/(this.weight[root1] + this.weight[root2]) {
             suc = true;
         }
         
        if (root1 != root2 && suc) {    // Merge with weighted union
            if (this.weight[root2] > this.weight[root1]) {
                this.parent[root1] = root2;
                this.weight[root2] += this.weight[root1];
            } else {
                this.parent[root2] = root1;
                this.weight[root1] += this.weight[root2];
            }
            return true;
        }
        return false;
    }
    
    double diff1(int root1, int root2) {
        return 0;
    }
    
    double diff2(int root1, int root2) {
        return 0;
    }
    
    double diff3(int root1, int root2) {
        return 0;
    }

    /** Find the (root of the) set containing a node, with path compression.
     *  @param curr the node to find
     *  @return the root node
     */
    int find(int curr) {
        if (this.parent[curr] == -1) {
            return curr; // At root
        }
        this.parent[curr] = this.find(this.parent[curr]);
        return this.parent[curr];
    }
}
