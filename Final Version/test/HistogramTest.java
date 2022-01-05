import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;
import view.ImageHistogram;


/**
 * This is the test class for the histogram.
 */
public class HistogramTest {

  /**
   * Test for null pixels.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testHistError() {
    ImageHistogram histogram = new ImageHistogram(null);
  }


  /**
   * Test the histogram creation process.
   */
  @Test
  public void testHistogram() {
    Pixel p1 = new EightBitPixelModel(Arrays.asList(103,200,24));
    Pixel p2 = new EightBitPixelModel(Arrays.asList(54,132,156));
    Pixel p3 = new EightBitPixelModel(Arrays.asList(231,32,78));
    Pixel p4 = new EightBitPixelModel(Arrays.asList(12,203,241));
    Pixel p5 = new EightBitPixelModel(Arrays.asList(147,123,241));
    Pixel p6 = new EightBitPixelModel(Arrays.asList(103,32,241));
    Pixel[][] pixels = new Pixel[][]{new Pixel[]{p1,p2,p3}, new Pixel[]{p4,p5,p6}};
    ImageHistogram histogram = new ImageHistogram(pixels);
    int[][] points = histogram.getIndexes();
    int[][] result = new int[4][256];
    result[0][109] = 1;
    result[0][114] = 1;
    result[0][113] = 1;
    result[0][152] = 1;
    result[0][170] = 1;
    result[0][125] = 1;

    result[1][103] = 2;
    result[1][54] = 1;
    result[1][231] = 1;
    result[1][12] = 1;
    result[1][147] = 1;

    result[2][200] = 1;
    result[2][132] = 1;
    result[2][32] = 2;
    result[2][203] = 1;
    result[2][123] = 1;

    result[3][24] = 1;
    result[3][156] = 1;
    result[3][78] = 1;
    result[3][241] = 3;

    for (int i = 0; i < points.length; i++) {
      for (int k = 0; k < points[i].length; k++) {
        assertEquals(result[i][k], points[i][k]);


      }
    }

  }
}
