/**
 * * PixelDistance class implementation of Distance.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */
public class PixelDistance implements Distance<Pixel> {

    @Override
    public double distance(Pixel one, Pixel two) {
        int b1 = one.value() & 0xFF;
        int g1 = (one.value() >> 8) & 0xFF;
        int r1 = (one.value() >> 16) & 0xFF;
        int e1 = (one.value() >> 24) & 0xFF;
        int b2 = two.value() & 0xFF;
        int g2 = (two.value() >> 8) & 0xFF;
        int r2 = (two.value() >> 16) & 0xFF;
        int e2 = (two.value() >> 24) & 0xFF;

        int b = (int) Math.pow((b1-b2), 2);
        int g = (int) Math.pow((g1-g2), 2);
        int r = (int) Math.pow((r1-r2), 2);
        int e = (int) Math.pow((e1-e2), 2);

        return b + g + r + e;
    }
    
}