/**Stores min and max rgb values of a partition.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */
public class MinMax {
    
    /**Minimum R value.*/
    private int minR;
    /**Minimum G value.*/
    private int minG;
    /**Minimum B value.*/
    private int minB;
    /**Maximum R value.*/
    private int maxR;
    /**Maximum G value.*/
    private int maxG;
    /**Maximum B value.*/
    private int maxB;
    
    /**Constructor.
     * @param p Pixel*/
    public MinMax(Pixel p) {
        this.minR = p.red();
        this.minG = p.green();
        this.minB = p.blue();
        this.maxR = p.red();
        this.maxG = p.green();
        this.maxB = p.blue();
    }
    
    /**Second Constructor.
     * @param a MinMax
     * @param b MinMax
     */
    public MinMax(MinMax a, MinMax b) {
        this.minR = Math.min(a.minR(), b.minR());
        this.minG = Math.min(a.minG(), b.minG());
        this.minB = Math.min(a.minB(), b.minB());
        this.maxR = Math.max(a.maxR(), b.maxR());
        this.maxG = Math.max(a.maxG(), b.maxG());
        this.maxB = Math.max(a.maxB(), b.maxB());
    }
    
    /**Set min and max values appropriately.
     * 
     * @param m other MinMax
     */
    public void set(MinMax m) {
        if (m.minR() < this.minR) {
            this.minR = m.minR;
        }
        if (m.minG() < this.minG) {
            this.minG = m.minG;
        }
        if (m.minB() < this.minB) {
            this.minB = m.minB;
        }
        if (m.maxR() > this.maxR) {
            this.maxR = m.maxR();
        }
        if (m.maxG() > this.maxG) {
            this.maxG = m.maxG();
        }
        if (m.maxB() > this.maxB) {
            this.maxB = m.maxB();
        }
    }
    
    /**Accessor method.
     * @return min*/
    public int minR() {
        return this.minR;
    }
    
    /**Accessor method.
     * @return min*/
    public int minG() {
        return this.minG;
    }
    
    /**Accessor method.
     * @return min*/
    public int minB() {
        return this.minB;
    }
    
    /**Accessor method.
     * @return max*/
    public int maxR() {
        return this.maxR;
    }
    
    /**Accessor method.
     * @return max*/
    public int maxG() {
        return this.maxG;
    }
    
    /**Accessor method.
     * @return max*/
    public int maxB() {
        return this.maxB;
    }
    
    /**Returns difference array.
     * @return difference array*/
    public int[] diff() {
        return new int[]{this.maxR - this.minR, this.maxG - this.minG, 
            this.maxB - this.minB};
    }
}