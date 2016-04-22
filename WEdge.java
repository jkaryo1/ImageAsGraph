/**
 * Implementation of a weighted edge class.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */
public class WEdge extends Edge implements Comparable<WEdge> {
    
    /**Weight of edge.*/
    private double weight;

    /**Undirected constructor.
     * @param u start
     * @param v end
     * @param w weight
     */
    public WEdge(Vertex u, Vertex v, double w) {
        super(u, v);
        this.weight = w;
    }

    /**Directed Constructor.
     * @param u start
     * @param v end
     * @param dir directed
     * @param w weight
     */
    public WEdge(Vertex u, Vertex v, boolean dir, double w) {
        super(u, v, dir);
        this.weight = w;
    }

    /** Weight accessor.
     * @return weight
     */
    public double weight() {
        return this.weight;
    }

    @Override
    public int compareTo(final WEdge e) {
        return Double.compare(this.weight, e.weight());
    }

    /**Create String representation of WEdge.
     * @return string as (source,end,weight)
     */
    public String toString() {
        return "(" + super.source() + "," + super.end() 
            + "," + this.weight + ")";
    }
}
