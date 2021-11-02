import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import model.image.ImageModel;
import model.image.PPMModel;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;
import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ImageStorageTest {

  /**
   * Test the put method to see if it really puts the image inside the storage.
   */
  @Test
  public void testPut() {
    ImageLibrary storage = new ImageStorage();
    Pixel[][] p = new Pixel[2][2];
    p[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    p[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    p[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    p[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    ImageModel test = new PPMModel(2, 2, 255, p);
    storage.put("name", test);
    assertEquals(storage.contain("name"), test);
  }

  /**
   * Test the remove method to see if it really removes the image inside the storage.
   */
  @Test
  public void testRemove() {
    ImageLibrary storage = new ImageStorage();
    Pixel[][] p = new Pixel[2][2];
    p[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    p[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    p[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    p[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    ImageModel test = new PPMModel(2, 2, 255, p);
    storage.put("name", test);
    assertEquals(storage.contain("name"), test);
    storage.remove("name", test);
    assertTrue(storage.contain("name").equals(null));
  }
}