/**
 * Implementation of a weighted edge class.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */
public class WEdge<VT> implements Comparable<WEdge<VT>> {

	/** Weight of the edge. */
	private double weight;
	
    /** Starting VT of an edge. */
    private VT source;
    /** Ending VT of an edge. */
    private VT end;
    /** Whether or not the edge is directed. */
    private boolean directed;

    /** Create an undirected edge.
     *  @param u the start
     *  @param v the end
     */
    public WEdge(VT u, VT v, double w) {
        this.source = u;
        this.end = v;
        this.directed = false;
        this.weight = w;
    }

    /** Create an edge.
     *  @param u the start
     *  @param v the end
     *  @param dir true if directed, false otherwise
     */
    public WEdge(VT u, VT v, boolean dir, double w) {
        this.source = u;
        this.end = v;
        this.directed = dir;
        this.weight = w;
    }

    /** Is the edge directed.
     *  @return true if yes, false otherwise
     */
    public boolean isDirected() {
        return this.directed;
    }

    /** Is a VT incident to this edge.
     *  @param v the VT
     *  @return true if source or end, false otherwise
     */
    public boolean isIncident(VT v) {
        return this.source.equals(v) || this.end.equals(v);
    }

    /** Get the starting endpoint VT.
     *  @return the VT
     */
    public VT source() {
        return this.source;
    }
    
    /** 
     * Weight accessor.
     * @return weight the weight of the edge
     */
    public double weight() {
    	return this.weight;
    }

    /** Get the ending endpoint VT.
     *  @return the VT
     */
    public VT end() {
        return this.end;
    }

    /** Create a string representation of the edge.
     *  @return the string as (source,end)
     */
    public String toString() {
        return "(" + this.source + "," + this.end + "," + this.weight + ")";
    }

    /** Check if two edges are the same.
     *  @param other the edge to compare to this
     *  @return true if directedness and endpoints match, false otherwise
     */
    public boolean equals(Object other) {
        if (other instanceof WEdge<?>) {
            WEdge<VT> e = (WEdge<VT>) other;
            if (this.directed != e.directed) {
                return false;
            }
            if (this.directed) {
                return this.source.equals(e.source)
                    && this.end.equals(e.end);
            } else {
                return this.source.equals(e.source)
                    && this.end.equals(e.end)
                    || this.source.equals(e.end)
                    && this.end.equals(e.source);
            }
        }
        return false;
    }

    /** Make a hashCode based on the toString.
     *  @return the hashCode
     */
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public int compareTo(final WEdge<VT> e) {
    	return Double.compare(this.weight, e.weight);
    }
}
