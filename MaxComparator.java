import java.util.Comparator;

/**
 * Simple maximum comparator.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 * @param <T> the generic data
 */
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