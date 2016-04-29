import java.util.HashMap;
import java.util.List;

/**
 * Implementation of tree-based set partitions with fast union/find. Adapted
 * from Shaffer/OpenDSA text.
 */
public class GraphPartition {

    /** Number of values to keeps min/max. */
    private static final int LENGTH = 3;

    /** Tuning parameter. */
    private double kvalue;

    /** The map holding the parents for each node. */
    private HashMap<Integer, MinMax> parMap;

    /** The array keeping the parents for each node. */
    private int[] parent;

    /** The array holding the weights (size) of the tree for each node. */
    private int[] weight;

    /**
     * Create a partition of singleton sets of the given size.
     * 
     * @param verts list of the graph's vertices
     * @param k kvalue
     */
    public GraphPartition(List<GVertex<Pixel>> verts, double k) {
        int num = verts.size();
        this.kvalue = k;
        this.weight = new int[num];
        this.parent = new int[num];
        this.parMap = new HashMap<Integer, MinMax>(num + 1, 1);
        int i = 0;
        for (GVertex<Pixel> pix : verts) {
            this.parent[i] = -1;
            this.weight[i] = 1;
            this.parMap.put(pix.id(), new MinMax(pix.data()));
            i++;
        }
    }

    /**
     * Weighted union of the sets containing two nodes, if different.
     * 
     * @param a the first node
     * @param b the second node
     * @return boolean weather or not they unioned
     */
    boolean union(int a, int b) {
        int root1 = this.find(a); // Find root of node a
        int root2 = this.find(b); // Find root of node b
        if (root1 != root2) { // Merge with weighted union
            MinMax temp1 = this.parMap.get(root1);
            MinMax temp2 = this.parMap.get(root2);
            if (this.diffCond(temp1, temp2, this.weight[root1],
                    this.weight[root2])) {
                MinMax comb = new MinMax(temp1, temp2);
                if (this.weight[root2] > this.weight[root1]) {
                    this.parent[root1] = root2;
                    this.weight[root2] += this.weight[root1];
                    this.parMap.put(root2, comb);
                } else {
                    this.parent[root2] = root1;
                    this.weight[root1] += this.weight[root2];
                    this.parMap.put(root1, comb);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Second condition.
     * 
     * @param a first MinMax
     * @param b second MinMax
     * @param aWeight a's weight
     * @param bWeight b's weight
     * @return whether to union
     */
    boolean diffCond(MinMax a, MinMax b, int aWeight, int bWeight) {
        int[] diffA = a.diff();
        int[] diffB = b.diff();
        MinMax comb = new MinMax(a, b);
        int[] diffAB = comb.diff();
        int weightSum = aWeight + bWeight;
        for (int i = 0; i < LENGTH; i++) {
            int min = diffA[i];
            if (diffB[i] < min) {
                min = diffB[i];
            }
            if ((double) diffAB[i] > (min + (this.kvalue / weightSum))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Find the (root of the) set containing a node, with path compression.
     * 
     * @param curr the node to find
     * @return the root node
     */
    int find(int curr) {
        if (this.parent[curr] == -1) {
            return curr; // At root
        }
        this.parent[curr] = this.find(this.parent[curr]);
        return this.parent[curr];
    }
}
