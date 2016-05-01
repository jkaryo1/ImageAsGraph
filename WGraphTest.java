import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Set of Junit tests for our WGraph implementations.
 * 
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer jkaryo1,
 *         cknowlt3, dfeldma9, dfisch11 hi P4 600.226
 */
public class WGraphTest {
    /** The graph. */
    WGraph<Character> g;
    /** Vertices. */
    GVertex<Character> v, u, x, y;
    /** Edges. */
    WEdge<Character> e, f;

    /** Initialization. */
    @Before
    public void setupWGraph() {
        this.g = new WGraphP4<>();
        this.v = new GVertex<>('v', this.g.nextID());
        this.u = new GVertex<>('u', this.g.nextID());
        this.x = new GVertex<>('x', this.g.nextID());
        this.y = new GVertex<>('y', this.g.nextID());
        this.e = new WEdge<>(this.v, this.u, 1);
        this.f = new WEdge<>(this.v, this.x, 1);
    }

    /** Tests empty graph. */
    @Test
    public void testEmpty() {
        assertEquals(0, this.g.numEdges());
        assertEquals(0, this.g.numVerts());
    }

    /** Test adding vertices. */
    @Test
    public void testAddVertex() {
        assertEquals(true, this.g.addVertex(this.v));
        assertEquals(true, this.g.addVertex(this.u));
        assertEquals(false, this.g.addVertex(this.v));
    }

    /** Test adding edges. */
    @Test
    public void testAddEdge() {
        assertEquals(true, this.g.addEdge(this.v, this.x, 2));
        assertEquals("[(0,2,2.0)]", this.g.incidentEdges(this.v).toString());
        assertEquals(true, this.g.addEdge(this.e));
        // Testing the Collections.sort()
        assertEquals("[(0,1,1.0), (0,2,2.0)]",
                this.g.incidentEdges(this.v).toString());
        assertEquals(false, this.g.addEdge(this.v, this.u, 2));
        assertEquals(false, this.g.addEdge(this.f));
    }

    /** Adjacency test. */
    @Test
    public void testAdjacency() {
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        assertEquals(false, this.g.areAdjacent(this.u, this.v));
        this.g.addEdge(this.e);
        this.g.addEdge(this.f);
        assertEquals(true, this.g.areAdjacent(this.u, this.v));
        assertEquals(true, this.g.areAdjacent(this.v, this.u));
        assertEquals(true, this.g.areAdjacent(this.v, this.x));
        assertEquals(false, this.g.areAdjacent(this.x, this.u));
        assertEquals(false, this.g.areAdjacent(this.v, this.y));
    }

    /** Incidence test. */
    @Test
    public void testIncidence() {
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        this.g.addEdge(this.e);
        assertEquals(false, this.g.areIncident(this.e, this.x));
        assertEquals(false, this.g.areIncident(this.e, this.y));
        assertEquals(true, this.g.areIncident(this.e, this.v));
        assertEquals(true, this.g.areIncident(this.e, this.u));
        this.g.addEdge(this.f);
        assertEquals(true, this.g.areIncident(this.f, this.x));
        assertEquals(false, this.g.areIncident(this.f, this.u));
        assertEquals(4, this.g.numVerts());
        assertEquals(2, this.g.numEdges());
    }

    /** Degree test. */
    @Test
    public void testDegree() {
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        assertEquals(0, this.g.degree(this.v));
        this.g.addEdge(this.e);
        assertEquals(1, this.g.degree(this.v));
        this.g.addEdge(this.f);
        assertEquals(2, this.g.degree(this.v));
        assertEquals(1, this.g.degree(this.x));
        assertEquals(0, this.g.degree(this.y));
    }

    /** Neighbor test. */
    @Test
    public void testNeighbors() {
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        assertEquals("[]", this.g.neighbors(this.v).toString());
        this.g.addEdge(this.e);
        assertEquals("[1]", this.g.neighbors(this.v).toString());
        assertEquals("[0]", this.g.neighbors(this.u).toString());
        this.g.addEdge(this.f);
        assertEquals("[1, 2]", this.g.neighbors(this.v).toString());
        assertEquals("[0]", this.g.neighbors(this.u).toString());
        assertEquals("[0]", this.g.neighbors(this.x).toString());
        assertEquals("[]", this.g.neighbors(this.y).toString());
    }

    /** All edges test. */
    @Test
    public void testAllEdges() {
        assertEquals("[]", this.g.allEdges().toString());
        assertEquals(0, this.g.numEdges());
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        assertEquals("[]", this.g.allEdges().toString());
        assertEquals(0, this.g.numEdges());
        this.g.addEdge(this.e);
        assertEquals("[(0,1,1.0)]", this.g.allEdges().toString());
        assertEquals(1, this.g.numEdges());
        this.g.addEdge(this.f);
        assertEquals("[(0,1,1.0), (0,2,1.0)]", this.g.allEdges().toString());
        assertEquals(2, this.g.numEdges());
    }

    /** All vertices test. */
    @Test
    public void testAllVertices() {
        assertEquals("[]", this.g.allVertices().toString());
        this.g.addVertex(this.v);
        assertEquals("[0]", this.g.allVertices().toString());
        this.g.addVertex(this.u);
        assertEquals("[0, 1]", this.g.allVertices().toString());
        this.g.addVertex(this.x);
        assertEquals("[0, 1, 2]", this.g.allVertices().toString());
        this.g.addVertex(this.y);
        assertEquals("[0, 1, 2, 3]", this.g.allVertices().toString());
    }

    /** Depth first test. */
    @Test
    public void testDepthFirst() {
        this.g.addVertex(this.v);
        assertEquals("[0]", this.g.depthFirst(this.v).toString());
        this.g.addVertex(this.u);
        assertEquals("[0]", this.g.depthFirst(this.v).toString());
        assertEquals("[1]", this.g.depthFirst(this.u).toString());
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        this.g.addEdge(this.e);
        assertEquals("[0, 1]", this.g.depthFirst(this.v).toString());
        assertEquals("[1, 0]", this.g.depthFirst(this.u).toString());
        this.g.addEdge(this.f);
        // if degree > 1, opposite order of neighbors()
        assertEquals("[0, 2, 1]", this.g.depthFirst(this.v).toString());
        assertEquals("[1, 0, 2]", this.g.depthFirst(this.u).toString());
    }

    /** Delete edge test. */
    @Test
    public void testDeleteEdge() {
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        this.g.addEdge(this.e);
        assertEquals("[(0,1,1.0)]", this.g.allEdges().toString());
        assertEquals(1, this.g.numEdges());
        assertEquals(true, this.g.areAdjacent(this.v, this.u));
        this.g.addEdge(this.f);
        assertEquals("[(0,1,1.0), (0,2,1.0)]", this.g.allEdges().toString());
        assertEquals(2, this.g.numEdges());
        assertEquals(4, this.g.numVerts());
        this.g.deleteEdge(this.v, this.u);
        assertEquals(1, this.g.numEdges());
        assertEquals(4, this.g.numVerts());
        assertEquals("[(0,2,1.0)]", this.g.allEdges().toString());
        assertEquals(false, this.g.areAdjacent(this.v, this.u));
    }

    /** Kruskal's test. */
    @Test
    public void testKruskals() {
        GVertex<Character> a = new GVertex<>('a', this.g.nextID());
        GVertex<Character> b = new GVertex<>('b', this.g.nextID());
        GVertex<Character> c = new GVertex<>('c', this.g.nextID());
        GVertex<Character> d = new GVertex<>('d', this.g.nextID());
        GVertex<Character> h = new GVertex<>('h', this.g.nextID());
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        this.g.addVertex(a);
        this.g.addVertex(b);
        this.g.addVertex(c);
        this.g.addVertex(d);
        this.g.addVertex(h); // Singleton vertex
        this.g.addEdge(new WEdge<>(this.v, this.u, 1));
        this.g.addEdge(new WEdge<>(this.u, this.x, 2));
        this.g.addEdge(new WEdge<>(this.u, this.y, 4));
        this.g.addEdge(new WEdge<>(this.v, this.x, 1));
        this.g.addEdge(new WEdge<>(this.v, this.y, 4));
        this.g.addEdge(new WEdge<>(this.x, this.y, 3));
        this.g.addEdge(new WEdge<>(this.y, a, 7));
        this.g.addEdge(new WEdge<>(b, c, 2)); // Disconnected from other tree
        // (5,6,2.0)
        this.g.addEdge(new WEdge<>(d, a, 6));
        this.g.addEdge(new WEdge<>(d, this.v, 1));
        this.g.addEdge(new WEdge<>(this.y, this.y, 1));
        String firstPart = this.g.kruskals().toString().substring(0, 33);
        assertTrue("Not sorted correctly", firstPart.contains("(0,1,1.0)"));
        assertTrue("Not sorted correctly", firstPart.contains("(0,2,1.0)"));
        assertTrue("Not sorted correctly", firstPart.contains("(0,7,1.0)"));
        assertEquals("Not sorted correctly", "(5,6,2.0), (2,3,3.0), (4,7,6.0)]",
                this.g.kruskals().toString().substring(34));

        WGraph<Character> empty = new WGraphP4<Character>();
        assertEquals("[]", empty.kruskals().toString()); // test empty
        empty.addVertex(this.v);
        empty.addVertex(this.u);
        empty.addVertex(this.y);
        empty.addVertex(a);
        empty.addVertex(b);
        empty.addVertex(c);
        empty.addVertex(d);
        empty.addVertex(h);
        assertEquals("[]", empty.kruskals().toString()); // test all singleton
                                                         // vertices
    }
}
