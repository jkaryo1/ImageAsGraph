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
     * @param h the heap.
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
        	return this.heap.remove(1);
        }
        T temp = this.heap.get(1);
        this.heap.set(1, this.heap.get(this.size));
        this.siftdown(1);
        return temp;
    }
    
    /**Move element in given location down to appropriate position.
     * @param pos position
     */
    public void siftdown(int pos) {
    	int left = this.getLeft(pos);
    	while (left < this.size && this.comp.compare(this.heap.get(pos), this.heap.get(left)) > 0) {
    		T temp = this.heap.get(pos);
    		this.heap.set(pos, this.heap.get(left));
    		this.heap.set(left, temp);
    		this.siftdown(left);
    		pos = left;
    		left = this.getLeft(left);
    	}
    }

    @Override
    public T peek() throws QueueEmptyException {
        return null;
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
    }

    @Override
    public void init(Collection<T> values) {
    }
}
