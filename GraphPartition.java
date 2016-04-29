import java.util.HashMap;
import java.util.List;

/** Implementation of tree-based set partitions with fast union/find.
 *  Adapted from Shaffer/OpenDSA text.
 */
public class GraphPartition {

    /** Number of values to keep smin/max.*/
    private static final int LENGTH = 3;
    
    /** The size of the partition.  */
    private int size;

    /** The map holding the parents for each node.  */
    private HashMap<Integer, MinMax> parMap;

    /** The array holding the weights (size) of the tree for each node. */
    private int[] weight;

    /** Create a partition of singleton sets of the given size.
     *  @param verts list of the graph's vertices
     */
    public GraphPartition(List<GVertex<Pixel>> verts) {
        int num = verts.size();
        this.size = num;
        this.weight = new int[num];
        this.parMap = new HashMap<Integer, MinMax>(num + 1, 1);
        int i = 0;
        for (GVertex<Pixel> pix : verts) {
            this.weight[i] = 1;
            this.parMap.put(pix.id(), new MinMax(pix.data()));
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