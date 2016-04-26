import java.util.Comparator;

public class MaxComparator<T extends Comparable<? super T>> 
        implements Comparator<T> {
    /**Compare method.
     * @param t1 T one
     * @param t2 T two
     * @return the result of the compare
     */
    public int compare(T t1, T t2) {
        return -t1.compareTo(t2);
    }
}