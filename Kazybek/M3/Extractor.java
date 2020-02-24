import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.FileNotFoundException;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Kazybek Mizam (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 02/23/2020  
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) throws FileNotFoundException {
      File file = new File(filename);
      Scanner scan = new Scanner(file);
      
      points = new Point[scan.nextInt()];
      
      for (int i = 0; i < points.length; i++) {
         Scanner sc = new Scanner(scan.nextLine());
         
         int x = scan.nextInt();
         int y = scan.nextInt();
         
         points[i] = new Point(x , y);
      }
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      Iterator itr = lines.iterator();
      
      Point[] newPoints = Arrays.copyOf(points, points.length);
      Line line = new Line();
      
      for (int i = 3; i < newPoints.length; i++) {
         for (int j = 2; j < i; j++) {
            for (int m = 1; m < j; m++) {
               for (int n = 0; n < m; n++) {
                  line.add(newPoints[i]);
                  line.add(newPoints[j]);
                  line.add(newPoints[m]);
                  line.add(newPoints[n]);
                  if (line.length() == 4) {
                     lines.add(line);
                  } 
                  line = new Line();
               }
            }
         }
      }
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      
      Point[] cpoints = Arrays.copyOf(points, points.length);
      int l = cpoints.length;
          
      for (int i = 0; i < l; i++) {
         Arrays.sort(cpoints, points[i].slopeOrder);
         int num = 0;
         for (int j = 0; j < l - 1; j += num) {
            num = 0;
            for (int m = j; m < l 
                     && points[i].slopeOrder.compare(cpoints[j], 
                              cpoints[m]) == 0; m++) {
               num++;
            }
            if (num >= 3) {
               Line line = new Line();
               line.add(points[i]);
               for (int n = 0; n < num; n++) {
                  line.add(cpoints[n + j]);
               }
               lines.add(line);
            }
         }
      }
      return lines;
   }
}
