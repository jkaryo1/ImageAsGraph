/**Pixel Object.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */
public class Pixel {
    
    /**Pixel's row.*/
    private int row;
    /**Pixel's column.*/
    private int col;
    /**Pixel's value.*/
    private Integer value;
    
    /**Constructor.
     * @param r row
     * @param c col
     * @param v value
     */
    public Pixel(int r, int c, Integer v) {
        this.row = r;
        this.col = c;
        this.value = v;
    }
}