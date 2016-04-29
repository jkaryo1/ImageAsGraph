/**Pixel Object.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */
public class Pixel {
    
    /**Value for a full byte.*/
    public static final int FULL = 0xFF;
    /**For second byte taken out.*/
    public static final int SECOND = 8;
    /**For third byte taken out.*/
    public static final int THIRD = 16;
    /**For fourth byte taken out.*/
    public static final int FOURTH = 24;
    
    /**Pixel's row.*/
    private int row;
    /**Pixel's column.*/
    private int col;
    /**Pixel's value.*/
    private int value;
    
    /**Constructor.
     * @param r row
     * @param c col
     * @param v value
     */
    public Pixel(int c, int r, int v) {
        this.row = r;
        this.col = c;
        this.value = v;
    }
    
    /**
     * Get row method.
     * @return the row
     */
    public int row() {
        return this.row;
    }
    
    /**
     * Get col method.
     * @return the row
     */
    public int col() {
        return this.col;
    }
    
    /**
     * Get value method.
     * @return the row
     */
    public int value() {
        return this.value;
    }
    
    /**Gets third byte.
     * 
     * @return third byte's value
     */
    public int red() {
        return (this.value >> THIRD) & FULL;
    }
    
    /**Gets second byte.
     * 
     * @return second byte's value
     */
    public int green() {
        return (this.value >> SECOND) & FULL;
    }
    
    /**Gets first byte.
     * 
     * @return first byte's value
     */
    public int blue() {
        return this.value & FULL;
    }
    
    /**Gets fourth byte.
     * 
     * @return fourth byte's value
     */
    public int alpha() {
        return (this.value >> FOURTH) & FULL;
    }
}