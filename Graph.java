import java.util.List;

/**Graph interface.
 * @author Joanne Selinski
 */
public interface Graph {

    /** Get the number of edges. 
     * @return edge count*/
    int numEdges();

    /** Get the number of vertices. 
     * @return vertex count*/
    int numVerts();

    /** Get the next ID to use in making a vertex. 
     * @return ID*/
    int nextID();

    /** Create and add a vertex to the graph.
     *  @param d the data to store in the vertex
     *  @return true if successful, false otherwise
     */
    boolean addVertex(Object d);

    /** Add a vertex if it doesn't exist yet. 
     * @param v vertex
     * @return whether vertex was added*/
    boolean addVertex(Vertex v);

    /** Add an edge, may also add the incident vertices. 
     * @param e edge to add
     * @return if edge was successfully added*/
    boolean addEdge(Edge e);

    /** Add a (directed) edge, may also add the incident vertices. 
     * @param v vertex
     * @param u vertex
     * @return if edge was successfully added*/
    boolean addEdge(Vertex v, Vertex u);

    /** Remove a (directed) edge if there.  
     * @param v vertex
     * @param u vertex
     * @return if edge was deleted*/
    boolean deleteEdge(Vertex v, Vertex u);

    /** Return true if there is an edge between v and u. 
     * @param v vertex
     * @param u vertex
     * @return if vertices are adjacent*/
    boolean areAdjacent(Vertex v, Vertex u);

    /** Return a list of all the neighbors of vertex v.  
     * @param v vertex
     * @return list of neighbors*/
    List<Vertex> neighbors(Vertex v);

    /** Return the number of edges incident to v.  
     * @param v vertex
     * @return number of vertex's edges*/
    int degree(Vertex v);

    /** Return true if v is an endpoint of edge e.
     * @param e edge
     * @param v vertex
     * @return if edge and vertex are incident*/
    boolean areIncident(Edge e, Vertex v);

    /** Return a list of all the vertices that can be reached from v,
     * in the order in which they would be visited in a depth-first
     * search starting at v.  
     * @param v vertex
     * @return stated above*/
    List<Vertex> depthFirst(Vertex v);

    /** Return a list of all the edges.  
     * @return all edges in graph*/
    List<Edge> allEdges();

    /** Return a list of all the vertices.  
     * @return all vertices in graph*/
    List<Vertex> allVertices();
}
