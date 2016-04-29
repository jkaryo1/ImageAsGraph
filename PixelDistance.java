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
        int b2 = two.blue();
        int g2 = two.green();
        int r2 = two.red();
        int e2 = two.alpha();
        
        System.out.println(b1 + " " + b2);
        System.out.println(g1 + " " + g2);
        System.out.println(r1 + " " + r2);
        System.out.println(e1 + " " + e2);

        int b = (int) Math.pow((b1 - b2), 2);
        int g = (int) Math.pow((g1 - g2), 2);
        int r = (int) Math.pow((r1 - r2), 2);
        int e = (int) Math.pow((e1 - e2), 2);

        return b + g + r + e;
    }
    
}