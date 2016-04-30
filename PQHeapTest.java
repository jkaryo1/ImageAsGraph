import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

/**JUnit test file.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */
public class PQHeapTest {
    
    /**Heap size.*/
    public static final int HEAP_SIZE = 20;
    /**List for init. */
    public static final List<Integer> LIST = 
            Arrays.asList(6, 3, 9, 2, 3, 8, 5, 2, 9, 1, 10);
    /**List for init. */
    public static final List<Integer> ORDER = 
            Arrays.asList(10, 9, 9, 8, 6, 5, 3, 3, 2, 2, 1);
    /**List for init. */
    public static final List<Integer> SMALL_BIG = 
            Arrays.asList(1, 2, 2, 3, 3, 5, 6, 8, 9, 9, 10);
    /**List for init. */
    public static final List<Integer> SAME = 
            Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    /**Empty PQHeap.*/
    static PQHeap<Integer> empty;
    /**Small to large PQHeap.*/
    static PQHeap<Integer> smalltolarge;
    /**Large to small PQHeap.*/
    static PQHeap<Integer> largetosmall;
    /**All same PQHeap.*/
    static PQHeap<Integer> allsame;
    /**Random PQHeap.*/
    static PQHeap<Integer> randomgen;
    /**Array of PQHeaps.*/
    static PQHeap<Integer>[] differentsizes;

    /**Before test.*/
    @Before
    public void initialize() {
        /**To test empty heap. */
        empty = new PQHeap<Integer>();

        /**More than 1 to make sure heap order is maintained regardless
         * of what order the heap is built in, and tests when repeated
         * priority values are present. */
        smalltolarge = new PQHeap<Integer>();
        largetosmall = new PQHeap<Integer>();
        allsame = new PQHeap<Integer>();
        randomgen = new PQHeap<Integer>();
        differentsizes = new PQHeap[HEAP_SIZE];

        for (int i = 0; i < HEAP_SIZE; i++) {
            smalltolarge.insert((Integer) i);
            largetosmall.insert((Integer) ((HEAP_SIZE - 1) - i));
            allsame.insert((Integer) 0);
        }

        int randZero = (int) (Math.random() * HEAP_SIZE);
        for (int i = 0; i < HEAP_SIZE; i++) {
            if (i == randZero) {
                randomgen.insert((Integer) 0);
            } else {
                randomgen.insert((Integer) (int) (Math.random() * HEAP_SIZE));
            }
        }

        for (int i = 0; i < differentsizes.length; i++) {
            differentsizes[i] = new PQHeap<Integer>();
            for (int j = 0; j < i; j++) {
                differentsizes[i].insert((Integer) j);
            }
        }
    }

    /** Test size() on empty PQ. */
    @Test
    public void testSizeEmpty() {
        assertEquals("Empty PQ should have size of 0", 0, empty.size());
    }

    /** Test size() on non-empty PQ. */
    @Test
    public void testSizeNonEmpty() {
        // Correct size regardless of how heap is built
        assertEquals("PQ not the correct size", HEAP_SIZE, smalltolarge.size());
        assertEquals("PQ not the correct size", HEAP_SIZE, largetosmall.size());

        // Correct size regardless of presence of equal priorities
        assertEquals("PQ not the correct size", HEAP_SIZE, allsame.size());
        assertEquals("PQ not the correct size", HEAP_SIZE, randomgen.size());

        // Check all sizes 0 - (HEAP_SIZE - 1), 
        // covers cases where heap is different # of levels
        for (int i = 0; i < differentsizes.length; i++) {
            assertEquals(differentsizes[i].size(), i);
        }
    }
    /** Test isEmpty() on empty PQ. */
    @Test
    public void testIsEmptyWithEmpty() {
        assertTrue("isEmpty() returns not empty when is empty", 
                empty.isEmpty());
    }

    /** Test isEmpty() on non-empty PQ. */
    @Test
    public void testIsEmptyNonEmpty() {
        // Correct result regardless of how heap is built       
        assertTrue("isEmpty() return empty when is non-empty", 
                !smalltolarge.isEmpty());
        assertTrue("isEmpty() return empty when is non-empty", 
                !largetosmall.isEmpty());
        // Correct result regardless of how heap is built
        assertTrue("isEmpty() return empty when is non-empty", 
                !allsame.isEmpty());
        assertTrue("isEmpty() return empty when is non-empty", 
                !randomgen.isEmpty());

        // Checks all sizes of PQ 1 - 99, size 1 is boundary condition
        assertTrue("Empty array is not empty", differentsizes[0].isEmpty());
        for (int i = 1; i < differentsizes.length; i++) {
            assertTrue("isEmpty() returns empty when PQ is not empty", 
                    !differentsizes[i].isEmpty());
        }
    }

    /** Test clear() on empty PQ. */
    @Test
    public void testClearEmpty() {
        empty.clear();
        assertEquals("Size not updated correctly on clear() for empty list", 
                0, empty.size());
        assertTrue("PQ is not empty after clear()", empty.isEmpty());
    }

    /** Test clear() on non-empty PQ. */
    @Test
    public void testClearNonEmpty() {
        smalltolarge.clear();
        assertEquals("Size not updated correctly on clear()", 0, 
                smalltolarge.size());
        assertTrue("PQ is not empty after clear()", smalltolarge.isEmpty());

        largetosmall.clear();
        assertEquals("Size not updated correctly on clear()", 0, 
                largetosmall.size());
        assertTrue("PQ is not empty after clear()", largetosmall.isEmpty());

        allsame.clear();
        assertEquals("Size not updated correctly on clear()", 0, 
                allsame.size());
        assertTrue("PQ is not empty after clear()", allsame.isEmpty());

        randomgen.clear();
        assertEquals("Size not updated correctly on clear()", 0, 
                randomgen.size());
        assertTrue("PQ is not empty after clear()", randomgen.isEmpty());

        // Checks clear on a range of different sized PQs, 
        // size 1 is boundary condition
        for (int i = 0; i < differentsizes.length; i++) {
            differentsizes[i].clear();
            assertEquals("Size not updated correctly on clear()", 0, 
                    differentsizes[i].size());
            assertTrue("PQ is not empty after clear()", 
                    differentsizes[i].isEmpty());           
        }
    }

    /** Test peek() on empty PQ (check that error is thrown). */
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

    /** Test peek() on non-empty MaxPQ. */
    @Test
    public void testPeekNonEmpty() {
        assertEquals("Best element is incorrect for heap built from small to "
                + "large values", (Integer) 0, smalltolarge.peek());
        assertEquals("Best element is incorrect for heap built from large to "
                + "small values", (Integer) 0, largetosmall.peek());
        assertEquals("Best element is incorrect for heap built with all "
                + "identical values", (Integer) 0, allsame.peek());
        assertEquals("Best element is incorrect for heap built with largest "
                + "value put in first", (Integer) 0, randomgen.peek());
        // Insert another value on random to check largest-inserted-last 
        // condition
        randomgen.insert((Integer) (-1)); 
        assertEquals("Best element is incorrect for heap built with largest "
                + "value put in last", (Integer) (-1), randomgen.peek());

        // Now check that the max value is true over a range of values
        for (int i = 0; i < differentsizes.length; i++) {
            Integer j;
            try {
                j = differentsizes[i].peek();
                assertEquals("Best element was incorrect when values vary.", 
                        0, (int) j);
            } catch (QueueEmptyException q) {
                assertEquals("QueueEmptyException thrown at wrong place", 0, i);
            }
        }
    }

    /**Test removeMax() on empty PQ (check that error is thrown). */
    @Test
    public void testRemoveEmpty() {
        Integer i = null;
        try {
            i = empty.remove();
            fail("Expected a QueueEmptyException to be thrown");
        } catch (QueueEmptyException q) {
            assertEquals("remove() returns a value with empty list", 
                    null, i);
            assertEquals("remove() changes queue size with empty list", 0, 
                    empty.size());
            assertTrue("remove() makes empty queue no longer empty", 
                    empty.isEmpty());
        }
    }

    /**Test removeMax() on non-empty PQ. */
    @Test
    public void removeSmallToLarge() {
        // Note the removed value should also be the size of the remaining PQ
        // This construction means we don't have to rely on peek() to know 
        // what size to check for. This construction allows us to not rely on 
        // size() to know the size of the remaining PQ but still requires us to 
        // use size() to check that the size is as expected. Tests the range of 
        // values up to but not including empty PQ.
        Integer removed = smalltolarge.remove();
        assertEquals("Remove didn't return correct value.", 0, (int) removed);
        assertEquals("Remove didn't update size.", (HEAP_SIZE - 1), 
                smalltolarge.size());
        while (!smalltolarge.isEmpty()) {
            removed++;
            assertEquals("Remove didn't return correct value.", removed, 
                    smalltolarge.remove());
            assertEquals("Remove didn't update size.", 
                    (HEAP_SIZE - 1) - (int) removed, smalltolarge.size());
        }
    }

    /**Test removing from the large to small heap. */
    @Test
    public void removeLargeToSmall() {
        // Note the removed value should also be the size of the remaining PQ
        // This construction means we don't have to rely on peek() to know what 
        // size to check for This construction allows us to not rely on size() 
        // to know the size of the remaining PQ but still requires us to use 
        // size() to check that the size is as expected. Tests the range of 
        // values up to but not including empty PQ.
        Integer removed = largetosmall.remove();
        assertEquals("Remove didn't return correct value.", 0, (int) removed);
        assertEquals("Remove didn't update size.", (HEAP_SIZE - 1), 
                largetosmall.size());
        while (!largetosmall.isEmpty()) {
            removed++;
            assertEquals("Remove didn't return correct value.", removed, 
                    largetosmall.remove());
            assertEquals("Remove didn't update size.", 
                    (HEAP_SIZE - 1) - (int) removed, largetosmall.size());
        }
    }

    /**Test removing from all same heap. */
    @Test
    public void removeAllSame() {
        int size = HEAP_SIZE;
        while (!allsame.isEmpty()) {
            size--;
            assertEquals("Remove didn't return correct value.", (Integer) 0, 
                    allsame.remove());
            assertEquals("Remove didn't update size.", size, allsame.size());
        }
    }

    /**Test that remove returns the same value as peek. */
    @Test
    public void removeRemovesSameAsPeek() {
        // Removes from the random list. This test is quite interdependent.
        int random, removed;
        while (!randomgen.isEmpty()) {
            random = randomgen.peek();
            removed = randomgen.remove();
            assertTrue("The value returned by remove() was not the same as "
                    + "that returned by peek()", random == (removed));   
        }
    }

    /**Test insert from empty list. */
    @Test
    public void testInsertEmpty() {
        empty.insert(0);
        assertFalse("Previously empty is still empty after insert", 
                empty.isEmpty());
        assertEquals("Size does not update with insert of empty size", 
                1, empty.size());
        try {
            assertEquals("Best element not updated after insert on empty list", 
                    (Integer) 0, empty.peek());
        } catch (QueueEmptyException q) {
            fail("Best element was not updated and QueueEmptyException was "
                    + "thrown");
        }
    }

    /**Test insert() on non-empty lists. */

    /**Inserts values smaller than the max value. */
    @Test
    public void testInsertBiggerValsNonEmpty() {
        for (int i = 1; i <= HEAP_SIZE; i++) {
            smalltolarge.insert(i + HEAP_SIZE);
            largetosmall.insert(i + HEAP_SIZE);
            randomgen.insert(i + HEAP_SIZE);

            // Check that size gets updated
            assertEquals("Size not updated on insert", HEAP_SIZE + i, 
                    smalltolarge.size());
            assertEquals("Size not updated on insert", HEAP_SIZE + i, 
                    largetosmall.size());
            assertEquals("Size not updated on insert", HEAP_SIZE + i, 
                    randomgen.size());

            // Check that maxVal is unchanged
            assertEquals("Best element changed on insert of value > best "
                    + "element", (Integer) 0, smalltolarge.peek());
            assertEquals("Best element changed on insert of value > best "
                    + "element", (Integer) 0, largetosmall.peek());
            assertEquals("Best element changed on insert of value > best "
                    + "element", (Integer) 0, randomgen.peek());

        }
    }

    /**Inserts values larger than/ equal to the max value. */
    @Test
    public void testInsertSmallerValsNonEmpty() {
        for (int i = (HEAP_SIZE - 1); i <= 1; i++) {
            smalltolarge.insert(i - HEAP_SIZE);
            largetosmall.insert(i - HEAP_SIZE);
            randomgen.insert(i - HEAP_SIZE);

            // Check that size gets updated
            assertEquals("Size not updated on insert", HEAP_SIZE + i, 
                    smalltolarge.size());
            assertEquals("Size not updated on insert", HEAP_SIZE + i, 
                    largetosmall.size());
            assertEquals("Size not updated on insert", HEAP_SIZE + i, 
                    randomgen.size());

            // Check that maxVal is correct
            assertEquals("Best element not updated when insert value < best "
                    + "element", (Integer) (i - HEAP_SIZE), 
                    smalltolarge.peek());
            assertEquals("Best element not updated when insert value < best "
                    + "element", (Integer) (i - HEAP_SIZE), 
                    largetosmall.peek());
            assertEquals("Best element not updated when insert value < best "
                    + "element", (Integer) (i - HEAP_SIZE), randomgen.peek());

        }
    }

    /**Test inserting duplicates. */
    @Test
    public void testInertionOfDuplicates() {
        // Repeatedly insert 0's to allsame
        for (int i = 1; i <= (HEAP_SIZE / 2 + 1); i++) {
            allsame.insert(0);
            assertEquals("Size not updated when inserting duplicates", 
                    HEAP_SIZE + i, allsame.size());
            assertEquals("Best element changed when inserting a duplicate "
                    + "value", (Integer) 0, allsame.peek());
        }

        //Repeatedly insert duplicate random values to randomgen
        int random;
        for (int i = 1; i < HEAP_SIZE; i++) {
            // Ensures repeats and that (HEAP_SIZE - 1) is the largest value
            random = (Integer) (int) (Math.random() * (HEAP_SIZE - 1));
            randomgen.insert((Integer) random);

            assertEquals("Size not updated when inserting duplicates", 
                    HEAP_SIZE + i, randomgen.size());
            assertEquals("Best element changed when inserting superminimal "
                    + "duplicate value", (Integer) 0, randomgen.peek());
        }
    }
    
    /**Test init method. */
    @Test
    public void testInit() {
        empty.init(LIST);
        smalltolarge.init(SMALL_BIG);
        largetosmall.init(ORDER);
        allsame.init(SAME);
        assertTrue("Empty queue is still empty after init()", !empty.isEmpty());
        assertEquals("Size of queue does not match size of Collection in "
                + "init()", (HEAP_SIZE / 2 + 1), empty.size());
        assertEquals("Best element is incorrect after init to empty list", 
                (Integer) 1, empty.peek());
        assertTrue("Non-empty queue is empty after init()", 
                !smalltolarge.isEmpty());
        assertEquals("Size of queue does not match size of Collection in "
                + "init()", (HEAP_SIZE / 2 + 1), smalltolarge.size());
        assertEquals("Best element is incorrect after init to non-empty list", 
                (Integer) 1, smalltolarge.peek());
        assertTrue("Non-empty queue is empty after init()", 
                !largetosmall.isEmpty());
        assertEquals("Size of queue does not match size of Collection in "
                + "init()", (HEAP_SIZE / 2 + 1), largetosmall.size());
        assertEquals("Best element is incorrect after init to non-empty list", 
                (Integer) 1, largetosmall.peek());
        assertTrue("Non-empty queue is empty after init()", !allsame.isEmpty());
        assertEquals("Size of queue does not match size of Collection in "
                + "init()", (HEAP_SIZE / 2 + 1), allsame.size());
        assertEquals("Best eleemnt is incorrect after init to non-empty list", 
                (Integer) 1, allsame.peek());
    }
}
