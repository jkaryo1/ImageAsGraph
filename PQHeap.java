import java.util.Collection;
import java.util.ArrayList;
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
    private static class DefaultComparator<T extends Comparable<? super T>> 
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
    
    /**Current size of heap.*/
    private int size;
    /**Comparator.*/
    private Comparator<T> comp;
    /**Heap array.*/
    private ArrayList<T> heap;


    /**
     * Initialize the priority queue heap.
     */
    public PQHeap() {
        this.heap = new ArrayList<T>();
        this.heap.add(null);
        this.size = 0;
        this.comp = new DefaultComparator<T>();
    }

    /**
     * Initialize the priority queue heap with comparator.
     * @param c the comparator
     */
    public PQHeap(Comparator<T> c) {
        this.heap = new ArrayList<T>();
        this.heap.add(null);
        this.size = 0;
        this.comp = c;
    }
    
    /**Gets index of the right child.
     * @param pos position
     * @return right index
     */
    public int getRight(int pos) {
        int right = 2 * pos + 1;
        if (right > this.size) {
            return -1;
        }
        return right;
    }
    
    /**Gets index of the left child.
     * @param pos position
     * @return left index
     */
    public int getLeft(int pos) {
        int left = 2 * pos;
        if (left > this.size) {
            return -1;
        }
        return left;
    }
    
    /**Gets index of parent.
     * @param pos position
     * @return parent index
     */
    public int getParent(int pos) {
        if (pos == 1) {
            return -1;
        }
        return pos / 2;
    }

    @Override
    public void insert(T t) {
        this.size++;
        int pos = this.size;
        this.heap.add(t);
        int par = this.getParent(pos);
        while ((par > 0) && this.comp.compare(t, this.heap.get(par)) < 0) {
            T temp = this.heap.get(par);
            this.heap.set(par, t);
            this.heap.set(pos, temp);
            pos = par;
            par = this.getParent(pos);
        }
    }

    @Override
    public T remove() throws QueueEmptyException {
        if (this.size == 0) {
            throw new QueueEmptyException();
        }
        if (this.size == 1) {
            this.size--;
            return this.heap.remove(1);
        }
        T temp = this.heap.get(1);
        this.size--;
        this.heap.set(1, this.heap.remove(this.size + 1));
        this.siftdown(1);
        return temp;
    }
    
    /**Move element in given location down to appropriate position.
     * @param pos position
     */
    public void siftdown(int pos) {
        int left = this.getLeft(pos);
        int right = this.getRight(pos);
        while (left <= this.size && left > 0) {
            int side;
            if (right <= this.size && right > 0) {
                side = this.comp.compare(this.heap.get(left), 
                        this.heap.get(right));
            } else {
                side = -1;
            }
            T temp = this.heap.get(pos);
            int insLoc = left;
            if (side >= 0) {
                insLoc = right;
            }
            if (this.comp.compare(this.heap.get(pos), 
                        this.heap.get(insLoc)) > 0) {
                this.heap.set(pos, this.heap.get(insLoc));
                this.heap.set(insLoc, temp);
                pos = insLoc;
                left = this.getLeft(pos);
                right = this.getRight(pos);
            } else {
                break;
            }
        }
    }

    @Override
    public T peek() throws QueueEmptyException {
        if (this.size == 0) {
            throw new QueueEmptyException();
        }
        return this.heap.get(1);
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.heap = new ArrayList<T>();
        this.heap.add(null);
    }

    @Override
    public void init(Collection<T> values) {
        this.clear();
        for (T val : values) {
            this.heap.add(val);
        }
        this.size = this.heap.size() - 1;
        for (int i = this.size / 2; i > 0; i--) {
            this.siftdown(i);
        }
    }
    
    /**ToString.
     * @return toString
     * */
    public String toString() {
        return this.heap.toString();
    }
}
