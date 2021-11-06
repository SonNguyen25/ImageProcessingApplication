import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;

import model.image.ImageModel;
import model.image.PPMModel;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;
import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class contains tests related to ImageViewImpl class.
 */
public class ImageViewImplTest {

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    Pixel[][] pix = new Pixel[2][2];
    pix[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    ImageModel mock = new PPMModel(2, 2, 255, pix);
    ImageLibrary storage = new ImageStorage();
    storage.put("mock", mock);
    ImageView view = new ImageViewImpl(storage);
    assertEquals(view.toString("mock"), "P3\n" +
            "2 2 \n" +
            "255\n" +
            "1 1 3 8 1 1" + System.lineSeparator() +
            "2 1 4 5 4 3" + System.lineSeparator());
  }

  /**
   * Test storage/library null for constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullLibConstructor() {
    Appendable ap = new StringBuilder();
    new ImageViewImpl(null, ap);
  }

  /**
   * Test appendable null for constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullAPConstructor() {
    ImageLibrary storage = new ImageStorage();
    new ImageViewImpl(storage, null);
  }
}