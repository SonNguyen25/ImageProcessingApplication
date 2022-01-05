import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.image.IOModel;
import model.image.ImageModel;
import model.image.PPMModel;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;
import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import view.ImageView;
import view.ImageViewImpl;

import static model.operations.Operations.OperationType.Downscale;
import static org.junit.Assert.assertEquals;

/**
 * Testing classes for downscaling and its related operations.
 */
public class DownscaleTest {
  ImageModel mock;
  ImageModel mock1;
  Pixel[][] pix;
  Pixel[][] pix1;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


  /**
   * Setting up streams before testing.
   */
  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }


  /**
   * Set up all the testing variables before running the testing methods.
   */
  @Before
  public void setUp() {
    pix = new Pixel[2][2];
    pix[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock = new IOModel(2, 2, 255, pix);
    pix1 = new Pixel[2][2];
    pix1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock1 = new IOModel(2, 2, 255, pix1);
  }

  @Test
  public void testDownscale() {
    mock.getUpdated(Downscale, 1, "1", null);
    Pixel[][] ds = new Pixel[1][1];
    ds[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    String ds1 = "";
    for (int i = 0; i < 1; i++) {
      for (int j = 0; j < 1; j++) {
        ds1 += (ds[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString = "";
    for (int k = 0; k < 1; k++) {
      for (int l = 0; l < 1; l++) {
        mockString += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(ds1, mockString);
    assertEquals(mock.getHeight(), 1);
    assertEquals(mock.getWidth(), 1);
  }

  /**
   * Test downscale in files.
   */
  @Test
  public void testDownscaleFile() {
    ImageProcess.main(new String[]{"-file",
        "C:\\Users\\admin\\OneDrive\\Public\\downscale\\script.txt"});

    assertEquals(outContent.toString(), "Input File Type: png" + System.lineSeparator() +
            "C:\\Users\\admin\\OneDrive\\Public\\downscale\\ood.png " +
            "has been loaded successfully as ood" + System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been downscale into an image of height: 10, width: 10 as oodTen" +
            System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been downscale into an image of height: 100, width: 100 as oodHundred" +
            System.lineSeparator() +
            "Action Completed! \n" +
            "oodTen has been saved successfully" +
            System.lineSeparator() +
            "Action Completed! \n" +
            "oodHundred has been saved successfully" +
            System.lineSeparator() +
            "Action Completed! " + "\n");
  }

  /**
   * Test downscale in text-based controller.
   */
  @Test
  public void testDownscaleText() {
    Readable in = new StringReader("downscale 1 1 mock q");
    Appendable output = new StringBuilder();

    ImageLibrary lib = new ImageStorage();

    Pixel[][] pix = new Pixel[2][2];
    pix[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    ImageModel mock = new PPMModel(2, 2, 255, pix);
    lib.put("mock", mock);
    ImageView view = new ImageViewImpl(lib, output);
    ImageController controller5 = new ImageControllerImpl(lib, view, in);
    controller5.process();
    assertEquals(outContent.toString(),
            "mock has been downscale into an image of height: 1, width: 1 as q" +
                    System.lineSeparator());
  }
}
