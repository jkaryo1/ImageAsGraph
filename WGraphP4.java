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
    
    /** number of edges. */
    int numEdges;
    /** number of vertices. */
    int numVerts;

    @Override
    public int numEdges() {
        return this.numEdges;
    }

    @Override
    public int numVerts() {
        return this.numVerts;
    }

    @Override
    public int nextID() {
        // TODO Auto-generated method stub
        return 0;
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