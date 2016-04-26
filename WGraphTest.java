import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import java.awt.Dimension;

/** 
 * Set of Junit tests for our WGraph implementations.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11 hi
 * P4
 * 600.226
 */
public class WGraphTest {
    WGraph<Character> g;
    GVertex<Character> v, u, x, y;
    WEdge<Character> e, f;

    @Before
    public void setupWGraph() {
        g = new WGraphP4<>();
        v = new GVertex<>('v' , g.nextID());
        u = new GVertex<>('u', g.nextID());
        x = new GVertex<>('x', g.nextID());
        y = new GVertex<>('y', g.nextID());
        e = new WEdge<>(v, u, 1);
        f = new WEdge<>(v, x, 1);
    }
    
    @Test
    public void testEmpty() {
        assertEquals(0, g.numEdges());
        assertEquals(0, g.numVerts());
    }
    
    @Test
    public void testAddVertex() {
        assertEquals(true, g.addVertex(v));
        assertEquals(true, g.addVertex(u));
        assertEquals(false, g.addVertex(v));
    }
    
    @Test
    public void testAddEdge() {
        assertEquals(true, g.addEdge(v, x, 2));
        assertEquals("[(0,2,2.0)]", g.incidentEdges(v).toString());
        assertEquals(true, g.addEdge(e));
        // Testing the Collections.sort()
        assertEquals("[(0,1,1.0), (0,2,2.0)]", g.incidentEdges(v).toString());
        assertEquals(false, g.addEdge(v, u, 2));
        assertEquals(false, g.addEdge(f));
    }
    
    @Test
    public void testAdjacency() {
        g.addVertex(v);
        g.addVertex(u);
        g.addVertex(x);
        g.addVertex(y);
        assertEquals(false, g.areAdjacent(u, v));
        g.addEdge(e);
        g.addEdge(f);
        assertEquals(true, g.areAdjacent(u, v));
        assertEquals(true, g.areAdjacent(v, u));
        assertEquals(true, g.areAdjacent(v, x));
        assertEquals(false, g.areAdjacent(x, u));
        assertEquals(false, g.areAdjacent(v, y));
    }

    @Test
    public void testIncidence() {
        g.addVertex(v);
        g.addVertex(u);
        g.addVertex(x);
        g.addVertex(y);
        g.addEdge(e);
        assertEquals(false, g.areIncident(e, x));
        assertEquals(false, g.areIncident(e, y));
        assertEquals(true, g.areIncident(e, v));
        assertEquals(true, g.areIncident(e, u));
        g.addEdge(f);
        assertEquals(true, g.areIncident(f, x));
        assertEquals(false, g.areIncident(f, u));
        assertEquals(4, g.numVerts());
        assertEquals(2, g.numEdges());
    }
    @Test
    public void testDegree() {
        g.addVertex(v);
        g.addVertex(u);
        g.addVertex(x);
        g.addVertex(y);
        assertEquals(0, g.degree(v));
        g.addEdge(e);
        assertEquals(1, g.degree(v));
        g.addEdge(f);
        assertEquals(2, g.degree(v));
        assertEquals(1, g.degree(x));
        assertEquals(0, g.degree(y));
    }


    @Test
    public void testNeighbors() {
        g.addVertex(v);
        g.addVertex(u);
        g.addVertex(x);
        g.addVertex(y);
        assertEquals("[]", g.neighbors(v).toString());
        g.addEdge(e);
        assertEquals("[1]", g.neighbors(v).toString());
        assertEquals("[0]", g.neighbors(u).toString());
        g.addEdge(f);
        assertEquals("[1, 2]", g.neighbors(v).toString());
        assertEquals("[0]", g.neighbors(u).toString());
        assertEquals("[0]", g.neighbors(x).toString());
        assertEquals("[]", g.neighbors(y).toString());
    }
    
    @Test
    public void testAllEdges() {
        assertEquals("[]", g.allEdges().toString());
        assertEquals(0, g.numEdges());
        g.addVertex(v);
        g.addVertex(u);
        assertEquals("[]", g.allEdges().toString());
        assertEquals(0, g.numEdges());
        g.addEdge(e);
        assertEquals("[(0,1,1.0)]", g.allEdges().toString());
        assertEquals(1, g.numEdges());
        g.addEdge(f);
        assertEquals("[(0,1,1.0), (0,2,1.0)]", g.allEdges().toString());
        assertEquals(2, g.numEdges());
    }
    
    @Test
    public void testAllVertices() {
        assertEquals("[]", g.allVertices().toString());
        g.addVertex(v);
        assertEquals("[0]", g.allVertices().toString());
        g.addVertex(u);
        assertEquals("[0, 1]", g.allVertices().toString());
        g.addVertex(x);
        assertEquals("[0, 1, 2]", g.allVertices().toString());
        g.addVertex(y);
        assertEquals("[0, 1, 2, 3]", g.allVertices().toString());
    }
    
    @Test
    public void testDepthFirst() {
        g.addVertex(v);
        assertEquals("[0]", g.depthFirst(v).toString());
        g.addVertex(u);
        assertEquals("[0]", g.depthFirst(v).toString());
        assertEquals("[1]", g.depthFirst(u).toString());
        g.addVertex(x);
        g.addVertex(y);
        g.addEdge(e);
        assertEquals("[0, 1]", g.depthFirst(v).toString());
        assertEquals("[1, 0]", g.depthFirst(u).toString());
        g.addEdge(f);
        // if degree > 1, opposite order of neighbors()
        assertEquals("[0, 2, 1]", g.depthFirst(v).toString());
        assertEquals("[1, 0, 2]", g.depthFirst(u).toString());
    }
    
    @Test
    public void testDeleteEdge() {
        g.addVertex(v);
        g.addVertex(u);
        g.addVertex(x);
        g.addVertex(y);
        g.addEdge(e);
        assertEquals("[(0,1,1.0)]", g.allEdges().toString());
        assertEquals(1, g.numEdges());
        assertEquals(true, g.areAdjacent(v, u));
        g.addEdge(f);
        assertEquals("[(0,1,1.0), (0,2,1.0)]", g.allEdges().toString());
        assertEquals(2, g.numEdges());
        assertEquals(4, g.numVerts());
        g.deleteEdge(v, u);
        assertEquals(1, g.numEdges());
        assertEquals(4, g.numVerts());
        assertEquals("[(0,2,1.0)]", g.allEdges().toString());
        assertEquals(false, g.areAdjacent(v, u));
    }
    
    @Test
    public void testKruskals() {
        GVertex<Character> a = new GVertex<>('a' , g.nextID());
        GVertex<Character> b = new GVertex<>('b' , g.nextID());
        GVertex<Character> c = new GVertex<>('c' , g.nextID());
        GVertex<Character> d = new GVertex<>('d' , g.nextID());
        GVertex<Character> e = new GVertex<>('e' , g.nextID());
        g.addVertex(v);
        g.addVertex(u);
        g.addVertex(x);
        g.addVertex(y);
        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);
        g.addVertex(d);
        g.addVertex(e); // Singleton vertex
        g.addEdge(new WEdge<>(v, u, 1));
        g.addEdge(new WEdge<>(u, x, 2));
        g.addEdge(new WEdge<>(u, y, 4));
        g.addEdge(new WEdge<>(v, x, 1));
        g.addEdge(new WEdge<>(v, y, 4));
        g.addEdge(new WEdge<>(x, y, 3));
        g.addEdge(new WEdge<>(y, a, 7));
        g.addEdge(new WEdge<>(b, c, 2)); // Disconnected from other tree (5,6,2.0)
        g.addEdge(new WEdge<>(d, a, 6));
        g.addEdge(new WEdge<>(d, v, 1));
        g.addEdge(new WEdge<>(y, y, 1));
        assertEquals("[(0,1,1.0), (0,2,1.0), (0,7,1.0), (5,6,2.0), (2,3,3.0), (4,7,6.0)]", g.kruskals().toString());
        
        WGraph<Character> empty = new WGraphP4<Character>();
        assertEquals("[]", empty.kruskals().toString()); //test empty
        empty.addVertex(v);
        empty.addVertex(u);
        empty.addVertex(y);
        empty.addVertex(a);
        empty.addVertex(b);
        empty.addVertex(c);
        empty.addVertex(d);
        empty.addVertex(e);
        assertEquals("[]", empty.kruskals().toString()); //test all singleton vertices
    }
}
