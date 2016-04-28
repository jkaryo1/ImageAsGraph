/**
 * PixelDistance class implementation of Distance.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 * @param <Pixel> the abstract data type.
 */
public class PixelDistance<Pixel> implements Distance<Pixel> {

    @Override
    public double distance(Pixel one, Pixel two) {
        int b1 = one.value() & 0xFF;
        int g1 = ((int)one >> 8) & 0xFF;
        int r1 = ((int) one >> 16) & 0xFF;
        return 0;
    }
    
}