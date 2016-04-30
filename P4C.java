import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * P4.
 * 
 * @author Jon Karyo, Calvin Knowlton, David Feldman, Derek Fischer jkaryo1,
 *         cknowlt3, dfeldma9, dfisch11 P4 600.226
 */
public class P4C {

    /**
     * Convert an image to a graph of Pixels with edges between north, south,
     * east and west neighboring pixels.
     * 
     * @param image
     *            the image to convert
     * @param pd
     *            the distance object for pixels
     * @return the graph that was created
     */
    static WGraphP4<Pixel> imageToGraph(BufferedImage image,
            Distance<Pixel> pd) {
        WGraphP4<Pixel> g = new WGraphP4<Pixel>();
        ArrayList<Pixel> pix = new ArrayList<Pixel>();
        int height = image.getHeight();
        int width = image.getWidth();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Pixel v2 = new Pixel(col, row,
                        image.getRGB(col, row));
                pix.add(v2);
                GVertex<Pixel> g2 = new GVertex<Pixel>(v2, g.nextID());
                g.addVertex(g2);
                if (col > 0) {
                    Pixel v1 = pix.get(pix.size() - 2);
                    double d = pd.distance(v1, v2);
                    GVertex<Pixel> g1 = new GVertex<Pixel>(v1, g.id() - 2);
                    g.addEdge(g1, g2, d);
                    System.out.println(g1.data().row() + " " + g1.data().col());
                    System.out.println(g2.data().row() + " " + g2.data().col());
                }
                if (row > 0) {
                    Pixel v1 = pix.get(pix.size() - width - 1);
                    double d = pd.distance(v1, v2);
                    GVertex<Pixel> g1 = new GVertex<Pixel>(v1, g.id() - width - 1);
                    g.addEdge(g1, g2, d);
                }
            }
        }
        
        /*for (WEdge<Pixel> i : g.allEdges()) {
            Pixel d = i.source().data();
            Pixel e = i.end().data();
            // System.out.println(d.col() + " " + d.row());
            System.out.println(d.row() + " " + d.col());
            System.out.println(e.row() + " " + e.col());
        }*/

        return g;
    }

    /**
     * Return a list of edges in a minimum spanning forest by implementing
     * Kruskal's algorithm using fast union/finds.
     * 
     * @param g
     *            the graph to segment
     * @param kvalue
     *            the value to use for k in the merge test
     * @return a list of the edges in the minimum spanning forest
     */

    static List<WEdge<Pixel>> segmenter(WGraph<Pixel> g, double kvalue) {
        List<WEdge<Pixel>> edges = new ArrayList<WEdge<Pixel>>();
        PriorityQueue<WEdge<Pixel>> queue = new PQHeap<WEdge<Pixel>>();
        GraphPartition part = new GraphPartition(g.allVertices(), kvalue);
        WEdge<Pixel> temp;
        int sourceID;
        int endID;
        boolean suc;

        queue.init(g.allEdges());

        for (int i = queue.size(); i > 0; i--) {
            temp = queue.remove();
            // System.out.println(temp.weight());
            sourceID = temp.source().id();
            endID = temp.end().id();
            suc = part.union(sourceID, endID);

            if (suc) {
                edges.add(temp);
            }
        }
        return edges;
    }

    /**
     * the main function.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {

        final int gray = 0x0DCDCDC;

        try {
            // the line that reads the image file

            BufferedImage image = ImageIO.read(new File(args[0]));
            WGraphP4<Pixel> g = imageToGraph(image, new PixelDistance());
            List<WEdge<Pixel>> res = segmenter(g, Double.parseDouble(args[1]));
            WGraphP4<Pixel> kruskals = new WGraphP4<>();

            System.out.print("result =  " + res.size() + "\n");
            System.out.print(
                    "NSegments =  " + (g.numVerts() - res.size()) + "\n");
            
            // make a background image to put a segment into
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    image.setRGB(j, i, gray);
                }
            }

            // Make a new graph will all the edges (adds all the vertices)
            for (WEdge<Pixel> e : res) {
                kruskals.addEdge(e);
            }

            //System.out.print("result =  " + kruskals.allVertices().toString() + "\n");
            
            // After you have a spanning tree connected component x,
            // you can generate an output image like this:
            /*for (GVertex<Pixel> i : kruskals.allVertices()) {
                Pixel d = i.data();
                // System.out.println(d.col() + " " + d.row());
                image.setRGB(d.col(), d.row(), d.value());
            }*/
            Iterator<GVertex<Pixel>> iter = kruskals.allVertices().iterator();
            for (int row = 0; row < image.getHeight(); row++) {
                for (int col = 0; col < image.getWidth(); col++) {
                    image.setRGB(col, row, iter.next().data().value());
                }
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
        }
    }

}
