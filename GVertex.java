/**
 * Implementation of a generic vertex class.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */

/**
 * GVertex class.
 * @param <T> the generic data.
 */
public class GVertex<T> 
        implements Comparable<GVertex<T>> {

    /** Vertex unique ID number. */
    private int num;

    /** Data stored in the vertex. */
    private T data;

    /** Create a new vertex.
     *  @param d the data to store in the node
     *  @param id the unique id of the node
     */
    public GVertex(T d, int id) {
        this.data = d;
        this.num = id;
    }

    /** Get the id of this vertex.
     *  @return the id
     */
    public int id() {
        return this.num;
    }

    /** Get a string representation of the vertex.
     *  @return the string 
     */
    public String toString() {
        return this.num + "";
    }

    /** Check if two vertices are the same based on ID.
     *  @param other the vertex to compare to this
     *  @return true if the same, false otherwise
     */
    public boolean equals(Object other) {
        if (other instanceof GVertex) {
            GVertex<T> v = (GVertex<T>) other;
            return this.num == v.num;  // want these to be unique
        }
        return false;
    }

    /** Get the hashcode of a vertex based on its ID.
     *  @return the hashcode
     */
    public int hashCode() {
        return (new Integer(this.num)).hashCode();
    }

    /** Compare two vertices based on their IDs.
     *  @param other the vertex to compare to this
     *  @return negative if this < other, 0 if equal, positive if this > other
     */
    public int compareTo(GVertex<T> other) {
        return this.num - other.num;
    }

    /**
     * get data method.
     * @return the data
     */
    public T data() {
        return this.data;
    }
}
