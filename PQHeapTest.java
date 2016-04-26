import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.lang.Math;
import java.util.Arrays;
import java.util.List;

public class PQHeapTest {
	static PQHeap<Integer> empty;
	static PQHeap<Integer> smalltolarge;
	static PQHeap<Integer> largetosmall;
	static PQHeap<Integer> allsame;
	static PQHeap<Integer> randomgen;
	static PQHeap<Integer>[] differentsizes;
	static MaxComparator<Integer> comp;

    @Before
    public void initialize() {
    	comp = new MaxComparator<Integer>();
    	/* To test empty heap. */
        empty = new PQHeap<Integer>(comp);

        /* More than 1 to make sure heap order is maintained regardless
         * of what order the heap is built in, and tests when repeated
         * priority values are present. */
        smalltolarge = new PQHeap<Integer>(comp);
        largetosmall = new PQHeap<Integer>(comp);
        allsame = new PQHeap<Integer>(comp);
        randomgen = new PQHeap<Integer>(comp);
        differentsizes = new PQHeap[100];

        for (int i = 0; i < 100; i++) {
            smalltolarge.insert((Integer) i);
            largetosmall.insert((Integer) (99 - i));
            allsame.insert((Integer) 1);
        }

        int random = 99;
        for (int i = 0; i < 100; i++) {
        	randomgen.insert((Integer) random);
        	// Ensures repeats and that 99 is the largest value
        	random = (Integer)(int)(Math.random() * 50 + 1);
        }

        for (int i = 0; i < differentsizes.length; i++) {
        	differentsizes[i] = new PQHeap<Integer>(comp);
        	for (int j = 0; j <= i; j++) {
        		differentsizes[i].insert((Integer) j);
        	}
        }
    }

    /* Test size() on empty PQ */
    @Test
    public void testSizeEmpty() {
        assertEquals("Empty PQ should have size of 0", 0, empty.size());
    }

    /* Test size() on non-empty PQ */
    @Test
    public void testSizeNonEmpty() {
    	// Correct size regardless of how heap is built
    	assertTrue("PQ not the correct size", smalltolarge.size() == 100);
    	assertTrue("PQ not the correct size", largetosmall.size() == 100);

    	// Correct size regardless of presence of equal priorities
    	assertTrue("PQ not the correct size", allsame.size() == 100);
    	assertTrue("PQ not the correct size", randomgen.size() == 100);

    	// Check all sizes 0 - 99, covers cases where heap is different # of levels
    	for (int i = 0; i < differentsizes.length; i++) {
    		assertTrue(differentsizes[i].size() == i);
    	}
    }
    /* Test isEmpty() on empty PQ */
    @Test
    public void testIsEmptyWithEmpty() {
        assertTrue("isEmpty() returns not empty when is empty", empty.isEmpty());
    }

    /* Test isEmpty() on non-empty PQ */
    @Test
    public void testIsEmptyNonEmpty() {
    	// Correct result regardless of how heap is built    	
    	assertTrue("isEmpty() return empty when is non-empty", !smalltolarge.isEmpty());
    	assertTrue("isEmpty() return empty when is non-empty", !largetosmall.isEmpty());
    	// Correct result regardless of how heap is built
     	assertTrue("isEmpty() return empty when is non-empty", !allsame.isEmpty());
    	assertTrue("isEmpty() return empty when is non-empty", !randomgen.isEmpty());

    	// Checks all sizes of PQ 1 - 99, size 1 is boundary condition
    	for (int i = 0; i < differentsizes.length; i++) {
    		assertTrue("isEmpty() returns empty when PQ is not empty", !differentsizes[i].isEmpty());
    	}
    }

    /* Test clear() on empty PQ */
    @Test
    public void testClearEmpty() {
        empty.clear();
        assertEquals("Size not updated correctly on clear() for empty list", 0, empty.size());
		assertTrue("PQ is not empty after clear()", empty.isEmpty());
    }

    /* Test clear() on non-empty PQ */
    @Test
    public void testClearNonEmpty() {
    	smalltolarge.clear();
    	assertTrue("Size not updated correctly on clear()", smalltolarge.size() == 0);
		assertTrue("PQ is not empty after clear()", smalltolarge.isEmpty());

    	largetosmall.clear();
    	assertTrue("Size not updated correctly on clear()", largetosmall.size() == 0);
		assertTrue("PQ is not empty after clear()", largetosmall.isEmpty());

    	allsame.clear();
    	assertTrue("Size not updated correctly on clear()", allsame.size() == 0);
		assertTrue("PQ is not empty after clear()", allsame.isEmpty());

    	randomgen.clear();
    	assertTrue("Size not updated correctly on clear()", randomgen.size() == 0);
		assertTrue("PQ is not empty after clear()", randomgen.isEmpty());

		// Checks clear on a range of different sized PQs, size 1 is boundary condition
		for (int i = 0; i < differentsizes.length; i++) {
	    	differentsizes[i].clear();
	    	assertTrue("Size not updated correctly on clear()", differentsizes[i].size() == 0);
			assertTrue("PQ is not empty after clear()", differentsizes[i].isEmpty());			
		}
    }

    /* Test peek() on empty PQ (check that error is thrown) */
    @Test
    public void testPeekEmpty() {
        Integer i = null;
        try {
            i = empty.peek();
            fail("Expected a QueueEmptyException to be thrown");
        } catch (QueueEmptyException q) {
            assertEquals("peek() returns a value with empty list", 
                    null, i);
        }
    }

    /* Test peek() on non-empty MaxPQ */
    @Test
    public void testPeekNonEmpty() {
    	assertTrue("Max element is incorrect for heap built from small to large values",
    					smalltolarge.peek() == (Integer) 99);
    	assertTrue("Max element is incorrect for heap built from large to small values",
    					largetosmall.peek() == (Integer) 99);
    	assertTrue("Max element is incorrect for heap built with all identical values",
    					allsame.peek() == (Integer) 99);
    	assertTrue("Max element is incorrect for heap built with largest value put in first",
    					randomgen.peek() == (Integer) 99);
    	// Insert another value on random to check largest-inserted-last condition
    	randomgen.insert((Integer) 100); 
    	assertTrue("Max element is incorrect for heap built with largest value put in last",
    					randomgen.peek() == (Integer) 100);

    	// Now check that the max value is true over a range of values
    	for (int i = 0; i < differentsizes.length; i++) {
    		assertTrue("Max element was incorrect when values vary.", differentsizes[i].peek() == (Integer) i);
    	}
    }

    /* Test removeMax() on empty PQ (check that error is thrown) */
    @Test
    public void testRemoveEmpty() {
        Integer i = null;
        try {
            i = empty.remove();
            fail("Expected a QueueEmptyException to be thrown");
        } catch (QueueEmptyException q) {
            assertEquals("remove() returns a value with empty list", 
                    null, i);
            assertEquals("remove() changes queue size with empty list", 0, empty.size());
            assertTrue("remove() makes empty queue no longer empty", empty.isEmpty());
        }
    }

    /* Test removeMax() on non-empty PQ */
    @Test
    public void removeSmallToLarge() {
    	// Note the removed value should also be the size of the remaining PQ
    	// This construction means we don't have to rely on peek() to know what size to check for
    	// This construction allows us to not rely on size() to know the size of the remaining PQ
    	// but still requires us to use size() to check that the size is as expected.
    	// Tests the range of values up to but not including empty PQ
    	Integer removed = smalltolarge.remove();
    	assertTrue("Remove didn't return correct value.", removed == (99));
		assertTrue("Remove didn't update size.", smalltolarge.size() == (99));
    	while(!smalltolarge.isEmpty()) {
    		removed--;
    		assertTrue("Remove didn't return correct value.", smalltolarge.remove() == (removed));
    		assertTrue("Remove didn't update size.", smalltolarge.size() == (removed));
    	}
    }

    @Test
    public void removeLargeToSmall() {
    	// Note the removed value should also be the size of the remaining PQ
    	// This construction means we don't have to rely on peek() to know what size to check for
    	// This construction allows us to not rely on size() to know the size of the remaining PQ
    	// but still requires us to use size() to check that the size is as expected.
    	// Tests the range of values up to but not including empty PQ
    	Integer removed = largetosmall.remove();
    	assertTrue("Remove didn't return correct value.", removed == (99));
		assertTrue("Remove didn't update size.", largetosmall.size() == (99));
    	while(!largetosmall.isEmpty()) {
    		removed--;
    		assertTrue("Remove didn't return correct value.", largetosmall.remove() == (removed));
    		assertTrue("Remove didn't update size.", largetosmall.size() == (removed));
    	}
    }

    @Test
    public void removeAllSame() {
    	int size = 100;
    	while(!allsame.isEmpty()) {
    		size--;
    		assertTrue("Remove didn't return correct value.", allsame.remove() == (1));
    		assertTrue("Remove didn't update size.", largetosmall.size() == (size));
    	}
    }

    @Test
    public void removeRemovesSameAspeek() {
    	// Removes from the random list. This test is quite interdependent.
    	int random, removed;
    	while(!randomgen.isEmpty()) {
    		random = randomgen.peek();
	    	removed = randomgen.remove();
	    	assertTrue("The value returned by removeMax() was not the same as that returned by peek()",
	    					random == (removed));	
    	}
    }

    /* test insert from empty list */
    @Test
    public void testInsertEmpty() {
    	empty.insert(1);
        assertFalse("Previously empty is still empty after insert", 
                empty.isEmpty());
        assertEquals("Size does not update with insert of empty size", 
                0, empty.size());
        try {
            assertEquals("MaxVal not updated after insert on empty list", (Integer) 1, empty.peek());
        } catch (QueueEmptyException q) {
            fail("MaxVal was not updated and QueueEmptyException was thrown");
        }
    }

    /* Test insert() on non-empty lists */

    /* Inserts values smaller than the max value */
    @Test
    public void testInsertSmallerValsNonEmpty() {
    	for (int i = 1; i <= 20; i++) {
    		smalltolarge.insert(i - 11);
    		largetosmall.insert(i - 11);
    		randomgen.insert(i - 11);

    		// Check that size gets updated
    		assertTrue("Size not updated on insert", smalltolarge.size() == (100 + i));
    		assertTrue("Size not updated on insert", largetosmall.size() == (100 + i));
    		assertTrue("Size not updated on insert", randomgen.size() == (100 + i));

    		// Check that maxVal is unchanged
    		assertTrue("MaxVal changed on insert of value < max", smalltolarge.peek() == (99));
    		assertTrue("MaxVal changed on insert of value < max", largetosmall.peek() == (99));
    		assertTrue("MaxVal changed on insert of value < max", randomgen.peek() == (99));

    	}
    }

    /* Inserts values larger than/ equal to the max value */
    @Test
    public void testInsertLargerValsNonEmpty() {
    	for (int i = 1; i <= 11; i++) {
    		smalltolarge.insert(i + 99);
    		largetosmall.insert(i + 99);
    		randomgen.insert(i + 99);

    		// Check that size gets updated
    		assertTrue("Size not updated on insert", smalltolarge.size() == (100 + i));
    		assertTrue("Size not updated on insert", largetosmall.size() == (100 + i));
    		assertTrue("Size not updated on insert", randomgen.size() == (100 + i));

    		// Check that maxVal is correct
    		assertTrue("MaxVal not updated when insert value >= max", smalltolarge.peek() == (99 + i));
    		assertTrue("MaxVal not updated when insert value >= max", largetosmall.peek() == (99 + i));
    		assertTrue("MaxVal not updated when insert value >= max", randomgen.peek() == (99 + i));

    	}
    }

    @Test
    public void testInertionOfDuplicates() {
    	// Repeatedly insert 1's to allsame
    	for (int i = 1; i <= 11; i++) {
    		allsame.insert(1);
    		assertTrue("Size not updated when inserting duplicates", allsame.size() == (100 + i));
    		assertTrue("MaxVal changed when inserting a duplicate value", allsame.peek() == 1);
    	}

    	//Repeatedly insert duplicate random values to randomgen
        int random;
        for (int i = 1; i < 100; i++) {
            // Ensures repeats and that 99 is the largest value
        	random = (Integer)(int)(Math.random() * 50 + 1);
        	randomgen.insert((Integer) random);

    		assertTrue("Size not updated when inserting duplicates", allsame.size() == (100 + i));
    		assertTrue("MaxVal changed when inserting submaximal duplicate value", allsame.peek() == 99);
        }
    }
    
    @Test
    public void testInit() {
        List<Integer> list = Arrays.asList(6, 3, 9, 2, 3, 8, 5, 2, 9, 1, 10);
        List<Integer> order = Arrays.asList(10, 9, 9, 8, 6, 5, 3, 3, 2, 2, 1);
        List<Integer> smallBig = Arrays.asList(1, 2, 2, 3, 3, 5, 6, 8, 9, 9, 10);
        List<Integer> same = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        System.out.println("Here");
        empty.init(list);
        System.out.println(empty);
        smalltolarge.init(smallBig);
        largetosmall.init(order);
        allsame.init(same);
        assertTrue("Empty queue is still empty after init()", !empty.isEmpty());
        assertEquals("Size of queue does not match size of Collection in init()", 11, empty.size());
        System.out.println(empty.peek());
        assertEquals("MaxVal is incorrect after init to empty list", (Integer) 10, empty.peek());
        assertTrue("Non-empty queue is empty after init()", !smalltolarge.isEmpty());
        assertEquals("Size of queue does not match size of Collection in init()", 11, smalltolarge.size());
        assertEquals("MaxVal is incorrect after init to non-empty list", (Integer) 10, smalltolarge.peek());
        assertTrue("Non-empty queue is empty after init()", !largetosmall.isEmpty());
        assertEquals("Size of queue does not match size of Collection in init()", 11, largetosmall.size());
        assertEquals("MaxVal is incorrect after init to non-empty list", (Integer) 10, largetosmall.peek());
        assertTrue("Non-empty queue is empty after init()", !allsame.isEmpty());
        assertEquals("Size of queue does not match size of Collection in init()", 11, allsame.size());
        assertEquals("MaxVal is incorrect after init to non-empty list", (Integer) 10, allsame.peek());

        for (int i = 0; i < 11; i++) {
            assertEquals("Empty queue's init with random ordered values does not match up with expected values", order.get(i), empty.remove());
            assertEquals("Queue with increasing values' init does not match up with expected values", order.get(i), smalltolarge.remove());
            assertEquals("Queue with decreasing values' init does not match up with expected values", order.get(i), largetosmall.remove());
            assertEquals("Queue with same values' init does not match up with expected values", order.get(i), allsame.remove());
        }
    }
}
