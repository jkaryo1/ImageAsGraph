/**
 * * PixelDistance class implementation of Distance.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */
public class PixelDistance implements Distance<Pixel> {

    /**Value for a full byte.*/
    public static final int FULL = 0xFF;
    /**For second byte taken out.*/
    public static final int SECOND = 8;
    /**For third byte taken out.*/
    public static final int THIRD = 16;
    /**For fourth byte taken out.*/
    public static final int FOURTH = 24;
    
    @Override
    public double distance(Pixel one, Pixel two) {
        int b1 = one.value() & FULL;
        int g1 = (one.value() >> SECOND) & FULL;
        int r1 = (one.value() >> THIRD) & FULL;
        int e1 = (one.value() >> FOURTH) & FULL;
        int b2 = two.value() & FULL;
        int g2 = (two.value() >> SECOND) & FULL;
        int r2 = (two.value() >> THIRD) & FULL;
        int e2 = (two.value() >> FOURTH) & FULL;

        int b = (int) Math.pow((b1 - b2), 2);
        int g = (int) Math.pow((g1 - g2), 2);
        int r = (int) Math.pow((r1 - r2), 2);
        int e = (int) Math.pow((e1 - e2), 2);

        return b + g + r + e;
    }
    
}