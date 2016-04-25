import java.util.Collection;
import java.util.Comparator;

/**
 * A Priority Queue Heap.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 * @param <T> the generic data
 */
public class PQHeap<T extends Comparable<? super T>> implements 
    PriorityQueue<T> {

    /**
     * Code taken from Joanne Selinksi for default Comparator.
     * @param <T> is the generic data
     */
    private static class DefaultComparator<T extends Comparable<T>> 
            implements Comparator<T> {
        /**Compare method.
         * @param t1 T one
         * @param t2 T two
         * @return the result of the compare
         */
        public int compare(T t1, T t2) {
            return t1.compareTo(t2);
        }
    }
    /**Maximum size of heap.*/
    private int maxSize;
    /**Current size of heap.*/
    private int currSize;
    /**Comparator.*/
    private Comparator<T> comp;
    /**Heap array.*/
    private T[] heap;


    /**
     * Initialize the priority queue heap.
     * @param h the heap.
     * @param s the current size
     * @param m the maximum size
     */
    public PQHeap(T[] h, int s, int m) {
        this.heap = h;
        this.currSize = s;
        this.maxSize = m;
        this.comp = new DefaultComparator<T>();
    }

    /**
     * Initialize the priority queue heap with comparator.
     * @param h the heap
     * @param s the current size
     * @param m the maximum size
     * @param c the comparator
     */
    public PQHeap(T[] h, int s, int m, Comparator<T> c) {
        this.heap = h;
        this.currSize = s;
        this.maxSize = m;
        this.comp = c;
    }

    @Override
    public void insert(T t) {
    }

    @Override
    public T remove() throws QueueEmptyException {
        return null;
    }

    @Override
    public T peek() throws QueueEmptyException {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.currSize == 0;
    }

    @Override
    public int size() {
        return this.currSize;
    }

    @Override
    public void clear() {
    }

    @Override
    public void init(Collection<T> values) {
    }
}
