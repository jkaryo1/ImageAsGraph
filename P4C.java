import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * P4.
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer
 * jkaryo1, cknowlt3, dfeldma9, dfisch11
 * P4
 * 600.226
 */
public class P4C {


    /** Convert an image to a graph of Pixels with edges between
     *  north, south, east and west neighboring pixels.
     *  @param image the image to convert
     *  @param pd the distance object for pixels
     *  @return the graph that was created
     */
    static WGraph<Pixel> imageToGraph(BufferedImage image, Distance<Pixel> pd) {
        WGraphP4<Pixel> g = new WGraphP4<Pixel>();
        ArrayList<Pixel> pix = new ArrayList<Pixel>();
        pix.add(new Pixel(0, 0, image.getRGB(0, 0)));
        g.addVertex(pix.get(0));
        for (int row = 0; row < image.getHeight(); row++) {
            for (int col = 0; col < image.getWidth(); col++) {
                if (col < image.getWidth() - 1) {
                    Pixel v1 = pix.get(row * image.getWidth() + col);
                    Pixel v2 = new Pixel(row, col + 1, image.getRGB(row, col));
                    if (row == 0) {
                        pix.add(v2);
                    }
                    g.addVertex(v2);
                    GVertex<Pixel> g1 = new GVertex<Pixel>(v1, g.id() - 1);
                    GVertex<Pixel> g2 = new GVertex<Pixel>(v2, g.id());
                    g.addEdge(g1, g2, pd.distance(v1, v2));
                }
            }
            if (row < image.getHeight() - 1) {
                for (int col = 0; col < image.getWidth(); col++) {
                    Pixel v1 = pix.get(row * image.getWidth() + col);
                    Pixel v2 = new Pixel(row + 1, col, image.getRGB(row, col));
                    pix.add(v2);
                    g.addVertex(v2);
                    GVertex<Pixel> g1 = 
                            new GVertex<Pixel>(v1, g.id() - image.getWidth());
                    GVertex<Pixel> g2 = new GVertex<Pixel>(v2, g.id());
                    g.addEdge(g1, g2, pd.distance(v1, v2));
                }
            }
        }
        
        return g;
    }


    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @param g the graph to segment
     *  @param kvalue the value to use for k in the merge test
     *  @return a list of the edges in the minimum spanning forest
     */

    static List<WEdge<Pixel>> segmenter(WGraph<Pixel> g, double kvalue) {
        return null;
    }

    /**
     * the main function.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        final int gray = 0x202020;

        try {
          // the line that reads the image file

            BufferedImage image = ImageIO.read(new File(args[0]));
            System.out.println("hey");
            WGraph<Pixel> g = imageToGraph(image, new PixelDistance());
            System.out.println("hi");
            List<WEdge<Pixel>> res = segmenter(g, Double.parseDouble(args[1]));
            WGraph<Pixel> kruskals = new WGraphP4<>();

            System.out.print("result =  " + res.size() + "\n");
            System.out.print("NSegments =  "
                             + (g.numVerts() - res.size()) + "\n");

            // make a background image to put a segment into
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    image.setRGB(j, i, gray);
                }
            }

            //Make a new graph will all the edges (adds all the vertices)
            for (WEdge<Pixel> e : g.kruskals()) {
                kruskals.addEdge(e);
            }
            
            // After you have a spanning tree connected component x, 
            // you can generate an output image like this:
            for (GVertex<Pixel> i: kruskals.allVertices())  {
                Pixel d = i.data();
                image.setRGB(d.col(), d.row(), d.value());
            }

            File f = new File("output.png");
            ImageIO.write(image, "png", f);

            // You'll need to do that for each connected component,
            // writing each one to a different file, clearing the
            // image buffer first

        } catch (IOException e) {
            System.out.print("Missing File!\n");

            // log the exception
            // re-throw if desired
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Missing File! :(\n");
        }
    }

}
