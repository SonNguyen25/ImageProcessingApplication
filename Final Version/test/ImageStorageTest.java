import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import model.image.ImageModel;
import model.image.PPMModel;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;
import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import static org.junit.Assert.assertEquals;


/**
 * Test class contain test related to the ImageLibrary interface and ImageStorage class.
 */
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
    assertEquals(storage.contain("name").getHeight(), 2);
    assertEquals(storage.contain("name").getWidth(), 2);
    String testS = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        testS += (p[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String inLib = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        inLib += (storage.contain("name").getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(inLib, testS);
  }


  /**
   * Test the contains method to see if it really contains the image inside the storage.
   */
  @Test
  public void testContain() {
    ImageLibrary storage = new ImageStorage();
    Pixel[][] p = new Pixel[2][2];
    p[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    p[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    p[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    p[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    ImageModel test = new PPMModel(2, 2, 255, p);
    storage.put("name", test);
    assertEquals(storage.contain("name").getHeight(), 2);
    assertEquals(storage.contain("name").getWidth(), 2);
    String testS = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        testS += (p[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String inLib = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        inLib += (storage.contain("name").getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(inLib, testS);
  }

  /**
   * Test null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    ImageLibrary lib = new ImageStorage();
    lib.put("", null);
  }
}