import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import java.awt.Dimension;

/** Set of Junit tests for our WGraph implementations.
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
        assertEquals(true, g.addEdge(e));
        assertEquals(true, g.addEdge(v, x, 2));
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
}