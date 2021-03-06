import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;

/**
 * Implementation of a Weighted Graph.
 * 
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer jkaryo1,
 *         cknowlt3, dfeldma9, dfisch11 P4 600.226
 * @param <VT>
 *            is the type
 */
public class WGraphP4<VT> implements WGraph<VT> {

    /** Used to generate Vertex IDs. */
    private int nextID;

    /** the number of edges. */
    private int numEdges;
    /**
     * Adjacency/Incidence Hashmap. Stores a vertex and list of incident edges.
     */
    private HashMap<GVertex<VT>, LinkedList<WEdge<VT>>> map;

    /**
     * Constructor for WGraphP4.
     */
    public WGraphP4() {
        this.nextID = 0;
        this.numEdges = 0;
        this.map = new HashMap<GVertex<VT>, LinkedList<WEdge<VT>>>();
    }

    @Override
    public int numEdges() {
        return this.numEdges;
    }

    @Override
    public int numVerts() {
        return this.map.size();
    }

    /**
     * Returns current ID.
     * 
     * @return current id
     */
    public int id() {
        return this.nextID;
    }

    @Override
    public int nextID() {
        return this.nextID++;
    }

    @Override
    public boolean addVertex(VT d) {
        LinkedList<WEdge<VT>> list = new LinkedList<WEdge<VT>>();
        this.map.put(new GVertex<VT>(d, this.nextID++), list);
        return true;
    }

    @Override
    public boolean addVertex(GVertex<VT> v) {
        LinkedList<WEdge<VT>> list = new LinkedList<WEdge<VT>>();
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

        if (u.equals(v)) {
            return false;
        }

        // put the vertexes in
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
        if (this.map.get(v).contains(edge)) {
            return false;
        }
        boolean added;
        LinkedList<WEdge<VT>> set1 = this.map.get(v);
        added = set1.add(edge);
        Collections.sort(set1);
        if (added) {
            this.map.put(v, set1);
            LinkedList<WEdge<VT>> set2 = this.map.get(u);
            added = set2.add(edgeR);
            Collections.sort(set2);
            this.map.put(u, set2);
        }
        if (added) {
            this.numEdges++;
        }
        return added;
    }

    @Override
    public boolean deleteEdge(GVertex<VT> v, GVertex<VT> u) {
        boolean firstDel = false;
        boolean secDel = false;
        if (this.map.containsKey(v) && this.map.containsKey(u)) {
            for (WEdge<VT> edge : this.map.get(v)) {
                if (edge.source().equals(v) && edge.end().equals(u)) {
                    this.map.get(v).remove(edge);
                    firstDel = true;
                }
            }
            for (WEdge<VT> edge : this.map.get(u)) {
                if (edge.source().equals(u) && edge.end().equals(v)) {
                    this.map.get(u).remove(edge);
                    secDel = true;
                }
            }
        }
        this.numEdges--;
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
        HashSet<WEdge<VT>> all = new HashSet<WEdge<VT>>(this.numEdges * 2);
        LinkedList<WEdge<VT>> edges = new LinkedList<WEdge<VT>>();
        for (LinkedList<WEdge<VT>> element : this.map.values()) {
            for (WEdge<VT> edge : element) {
                WEdge<VT> edgeR = new WEdge<VT>(edge.end(), edge.source(),
                        edge.weight());
                if (!all.contains(edgeR)) {
                    all.add(edge);
                }
            }
        }
        for (WEdge<VT> edge : all) {
            edges.add(edge);
        }
        return edges;
    }

    @Override
    public List<GVertex<VT>> allVertices() {
        LinkedList<GVertex<VT>> verts = new LinkedList<GVertex<VT>>();
        for (GVertex<VT> vertex : this.map.keySet()) {
            verts.add(vertex);
        }
        return verts;
    }

    @Override
    public List<GVertex<VT>> depthFirst(GVertex<VT> v) {
        LinkedList<GVertex<VT>> reaches = new LinkedList<GVertex<VT>>();
        // using LinkedList<Vertex> as a Stack
        LinkedList<GVertex<VT>> stack = new LinkedList<GVertex<VT>>();
        boolean[] visited = new boolean[this.numVerts() * 2]; // inits to false
        stack.addFirst(v);
        visited[v.id()] = true;
        while (!stack.isEmpty()) {
            v = stack.removeFirst();
            reaches.add(v);
            for (GVertex<VT> u : this.neighbors(v)) {
                // System.out.println(u.id());
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
            return new LinkedList<WEdge<VT>>(this.map.get(v));
        }
        return null;
    }

    @Override
    public List<WEdge<VT>> kruskals() {
        List<WEdge<VT>> edges = new ArrayList<WEdge<VT>>();
        PriorityQueue<WEdge<VT>> queue = new PQHeap<WEdge<VT>>();
        Partition part = new Partition(this.numVerts());
        WEdge<VT> temp;
        int sourceID;
        int endID;
        boolean suc;

        queue.init(this.allEdges());

        for (int i = queue.size(); i > 0; i--) {
            temp = queue.remove();
            sourceID = temp.source().id();
            endID = temp.end().id();

            suc = part.union(endID, sourceID);

            if (suc) {
                edges.add(temp);
            }
        }
        return edges;
    }
}
