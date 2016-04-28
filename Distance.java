/**
 * Distance interface.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 * @param <T> the abstract data type.
 */
interface Distance<T> {
    /**
     * returns the distance between two objects.
     * @param one the first object
     * @param two the second object
     * @return the distance between
     */
    double distance(T one, T two);
}