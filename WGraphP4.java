import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a Weighted Graph.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 * @param <VT> is the type
 */
public class WGraphP4<VT> implements WGraph<VT> {
    
    /** Used to generate Vertex IDs. */
    private int nextID;
    
    /** the number of edges. */
    private int numEdges;
    /** 
     * Adjacency/Incidence Hashmap.
     * Stores a vertex and list of incident edges.
     */
    private HashMap<GVertex<VT>, ArrayList<WEdge<VT>>> map;
    
    /**
     * Constructor for WGraphP4.
     */
    public WGraphP4() {
        this.nextID = 0;
        this.numEdges = 0;
        this.map = new HashMap<GVertex<VT>, ArrayList<WEdge<VT>>>();
    }

    @Override
    public int numEdges() {
        return this.numEdges;
    }

    @Override
    public int numVerts() {
        return this.map.size();
    }

    @Override
    public int nextID() {
        return this.nextID++;
    }
    
    @Override
    public boolean addVertex(VT d) {
        ArrayList<WEdge<VT>> list = new ArrayList<WEdge<VT>>();
        this.map.put(new GVertex<VT>(d, this.nextID++), list);
        return true;
    }

    @Override
    public boolean addVertex(GVertex<VT> v) {
        ArrayList<WEdge<VT>> list = new ArrayList<WEdge<VT>>();
        if (this.map.containsKey(v)) { // already in our map
            return false;
        } else {
            this.map.put(v, list);
            return true;
        }
    }

    @Override
    public boolean addEdge(WEdge<VT> e) {
        boolean added = false;
        added = this.addEdge(e.source(), e.end(), e.weight());
        return added;
    }

    @Override
    public boolean addEdge(GVertex<VT> v, GVertex<VT> u, double weight) {
        WEdge<VT> edge = new WEdge<VT>(v, u, weight);
        WEdge<VT> edgeR = new WEdge<VT>(u, v, weight);
 
        boolean success = true;
        if (!this.map.containsKey(v)) {
            success = this.addVertex(v);
        }
        if (success && !this.map.containsKey(u)) {
            success = this.addVertex(u);
        }
        if (!success) {
            return false;
        }
        // put the edge in, if not already there
        if (!this.map.get(v).contains(edge)) {
            boolean added = false;
            for (int i = 0; i < this.map.get(v).size(); i++) {
                if (edge.compareTo(this.map.get(v).get(i)) < 0) {
                    this.map.get(v).add(i, edge);
                    added = true;
                    break;
                }
            }
            if (!added) {
                this.map.get(v).add(edge);
            }
            added = false;
            for (int i = 0; i < this.map.get(u).size(); i++) {
                if (edge.compareTo(this.map.get(u).get(i)) < 0) {
                    this.map.get(u).add(i, edgeR);
                    added = true;
                    break;
                }
            }
            if (!added) {
                this.map.get(u).add(edgeR);
            }
            this.numEdges++;
            return true;
        }
        return false; 
    }

    @Override
    public boolean deleteEdge(GVertex<VT> v, GVertex<VT> u) {
        boolean firstDel = false;
        boolean secDel = false;
        if (this.map.containsKey(v) && this.map.containsKey(u)) {
            for (WEdge<VT> edge : this.map.get(v)) {
                if (edge.source().equals(v) && edge.end().equals(u)) {
                    this.map.remove(edge);
                    firstDel = true;
                }
            }
            for (WEdge<VT> edge : this.map.get(u)) {
                if (edge.source().equals(u) && edge.end().equals(v)) {
                    this.map.remove(edge);
                    secDel = true;
                }
            }
        }
        return firstDel && secDel;
    }

    @Override
    public boolean areAdjacent(GVertex<VT> v, GVertex<VT> u) {
        if (this.map.containsKey(v) && this.map.containsKey(u)) {
            for (WEdge<VT> edge : this.map.get(v)) {
                if (edge.source().equals(v) && edge.end().equals(u)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<GVertex<VT>> neighbors(GVertex<VT> v) {
        ArrayList<GVertex<VT>> nbs = new ArrayList<GVertex<VT>>();
        if (this.map.containsKey(v)) {
            for (WEdge<VT> edge : this.map.get(v)) {
                nbs.add(edge.end());
            }
        }
        return nbs;
    }

    @Override
    public int degree(GVertex<VT> v) {
        return this.neighbors(v).size();
    }

    @Override
    public boolean areIncident(WEdge<VT> e, GVertex<VT> v) {
        return e.source().equals(v) || e.end().equals(v);
    }

    @Override
    public List<WEdge<VT>> allEdges() {
        ArrayList<WEdge<VT>> edges = new ArrayList<WEdge<VT>>();
        for (ArrayList<WEdge<VT>> element : this.map.values()) {
            for (WEdge<VT> edge : element) {
                edges.add(edge);
            }
        }
        return edges;
    }

    @Override
    public List<GVertex<VT>> allVertices() {
        ArrayList<GVertex<VT>> verts = new ArrayList<GVertex<VT>>();
        for (GVertex<VT> vertex : this.map.keySet()) {
            verts.add(vertex);
        }
        return verts;
    }

    @Override
    public List<GVertex<VT>> depthFirst(GVertex<VT> v) {
        ArrayList<GVertex<VT>> reaches = new ArrayList<GVertex<VT>>();
        // using LinkedList<Vertex> as a Stack
        LinkedList<GVertex<VT>> stack = new LinkedList<GVertex<VT>>();
        boolean[] visited = new boolean[this.numVerts()];  // inits to false
        stack.addFirst(v);
        visited[v.id()] = true;
        while (!stack.isEmpty()) {
            v = stack.removeFirst();
            reaches.add(v);
            for (GVertex<VT> u: this.neighbors(v)) {
                if (!visited[u.id()]) {
                    visited[u.id()] = true;
                    stack.addFirst(u);
                }
            }
        }
        return reaches;
    }

    @Override
    public List<WEdge<VT>> incidentEdges(GVertex<VT> v) {
        if (this.map.containsKey(v)) {
            return this.map.get(v);
        }
        return null;
    }

    @Override
    public List<WEdge<VT>> kruskals() {
        // TODO Auto-generated method stub
        return null;
    }
}
