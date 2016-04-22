import java.util.List;

/**
 * Implementation of a Weighted Graph.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */
public abstract class WGraphP4 implements WGraph<VT> {

    @Override
    public int numEdges() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int numVerts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int nextID() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean addVertex(GVertex v) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addEdge(WEdge e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addEdge(GVertex v, GVertex u, double weight) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteEdge(GVertex v, GVertex u) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean areAdjacent(GVertex v, GVertex u) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List neighbors(GVertex v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int degree(GVertex v) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean areIncident(WEdge e, GVertex v) {
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
    public List depthFirst(GVertex v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List incidentEdges(GVertex v) {
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