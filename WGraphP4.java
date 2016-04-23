import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of a Weighted Graph.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 * @param <VT> is the type
 */
public abstract class WGraphP4<VT> implements WGraph<VT> {
    
    /** Used to generate Vertex IDs. */
    private int nextID;
    
    /** the number of edges. */
    private int numEdges;
    /** Hashmap that holds edges. */
    private HashMap<GVertex<VT>, ArrayList<GVertex<VT>>> edges;
    
    /** Array List that holds vertexes. */
    private ArrayList<GVertex<VT>> verts;
    /** Our Adjacency Matrix, ints for weight. */
    private int[][] matrix;
    
    /**
     * Constructor for WGraphP4.
     * @param maxVerts the maximum number of vertexes.
     */
    public WGraphP4(int maxVerts) {
        this.nextID = 0;
        this.numEdges = 0;
        this.edges = new HashMap<GVertex<VT>, ArrayList<GVertex<VT>>>();
        this.verts = new ArrayList<GVertex<VT>>(maxVerts);
        this.matrix = new int[maxVerts][maxVerts];
    }

    @Override
    public int numEdges() {
        return this.numEdges;
    }

    @Override
    public int numVerts() {
        return this.verts.size();
    }

    @Override
    public int nextID() {
        return this.nextID++;
    }

    @Override
    public boolean addVertex(GVertex<VT> v) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addEdge(WEdge<VT> e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addEdge(GVertex<VT> v, GVertex<VT> u, double weight) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteEdge(GVertex<VT> v, GVertex<VT> u) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean areAdjacent(GVertex<VT> v, GVertex<VT> u) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List neighbors(GVertex<VT> v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int degree(GVertex v) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean areIncident(WEdge<VT> e, GVertex<VT> v) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List allEdges() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List allVertices() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List depthFirst(GVertex<VT> v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List incidentEdges(GVertex<VT> v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List kruskals() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addVertex(VT d) {
        // TODO Auto-generated method stub
        return false;
    }
    
}