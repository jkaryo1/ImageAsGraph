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
        int b1 = one.blue();
        int g1 = one.green();
        int r1 = one.red();
        int e1 = one.alpha();
        int b2 = one.blue();
        int g2 = one.green();
        int r2 = one.red();
        int e2 = one.alpha();

        int b = (int) Math.pow((b1 - b2), 2);
        int g = (int) Math.pow((g1 - g2), 2);
        int r = (int) Math.pow((r1 - r2), 2);
        int e = (int) Math.pow((e1 - e2), 2);

        return b + g + r + e;
    }
    
}