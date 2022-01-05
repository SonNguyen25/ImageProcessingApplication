import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

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

import static org.junit.Assert.assertEquals;

/**
 * Testing class for partial image manipulation through controller.
 */
public class MaskedControllerTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


  /**
   * Setting up streams before testing.
   */
  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  /**
   * Test parse script with mask images.
   */
  @Test
  public void testMaskScript() {
    ImageProcess.main(new String[]{"-file",
        "C:\\Users\\admin\\OneDrive\\Public\\maskedImage\\script.txt"});

    assertEquals(outContent.toString(), "Input File Type: png" + System.lineSeparator() +
            "C:\\Users\\admin\\OneDrive\\Public\\maskedImage\\ood.png " +
            "has been loaded successfully as ood" + System.lineSeparator() +
            "Action Completed! \n" +
            "Input File Type: png" + System.lineSeparator() +
            "C:\\Users\\admin\\OneDrive\\Public\\maskedImage\\mask.png " +
            "has been loaded successfully as mask" + System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been blurred as oodBlur" + System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been sharpened as oodSharp" + System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been sepia-toned as oodSepia" + System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been greyscale as oodGreyscale" + System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been changed successfully as intensity component oodIntensity" +
            System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been changed successfully as max component oodMax" + System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been changed successfully as luma component oodLuma" + System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been changed successfully as red component oodRed" + System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been changed successfully as green component oodGreen" +
            System.lineSeparator() +
            "Action Completed! \n" +
            "ood has been changed successfully as blue component oodBlue" + System.lineSeparator() +
            "Action Completed! \n" +
            "oodBlur has been saved successfully" + System.lineSeparator() +
            "Action Completed! \n" +
            "oodSharp has been saved successfully" + System.lineSeparator() +
            "Action Completed! \n" +
            "oodSepia has been saved successfully" + System.lineSeparator() +
            "Action Completed! \n" +
            "oodGreyscale has been saved successfully" + System.lineSeparator() +
            "Action Completed! \n" +
            "oodIntensity has been saved successfully" + System.lineSeparator() +
            "Action Completed! \n" +
            "oodMax has been saved successfully" + System.lineSeparator() +
            "Action Completed! \n" +
            "oodLuma has been saved successfully" + System.lineSeparator() +
            "Action Completed! \n" +
            "oodRed has been saved successfully" + System.lineSeparator() +
            "Action Completed! \n" +
            "oodGreen has been saved successfully" + System.lineSeparator() +
            "Action Completed! \n" +
            "oodBlue has been saved successfully" + System.lineSeparator() +
            "Action Completed! \n");
  }

  /**
   * Test mask image manipulation in text-based controller.
   */
  @Test
  public void testTextMask() {
    Readable in = new StringReader("blur mock mockBlur blur mock mask mockMaskBlur " +
            "sharpen mock mockSharp sharpen mock mask mockMaskSharp sepia mock mockSepia " +
            "sepia mock mask mockMaskSepia greyscale mock mockGrey " +
            "greyscale mock mask mockMaskGrey luma-component mock mockLuma " +
            "luma-component mock mask mockMaskLuma value-component mock mockValue " +
            "value-component mock mask mockMaskValue intensity-component mock mockIntensity " +
            "intensity-component mock mask mockMaskIntensity red-component mock mockRed " +
            "red-component mock mask mockMaskRed green-component mock mockGreen " +
            "green-component mock mask mockMaskGreen blue-component mock mockBlue " +
            "blue-component mock mask mockMaskBlue q");
    Appendable output = new StringBuilder();

    ImageLibrary lib = new ImageStorage();
    Pixel[][] pix1 = new Pixel[2][2];
    pix1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    pix1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    pix1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    pix1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    ImageModel mask = new IOModel(2, 2, 255, pix1);
    lib.put("mask", mask);

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
    assertEquals(outContent.toString(), "mock has been blurred as mockBlur" +
            System.lineSeparator() +
            "mock has been blurred as mockMaskBlur" + System.lineSeparator() +
            "mock has been sharpened as mockSharp" + System.lineSeparator() +
            "mock has been sharpened as mockMaskSharp" + System.lineSeparator() +
            "mock has been sepia-toned as mockSepia" + System.lineSeparator() +
            "mock has been sepia-toned as mockMaskSepia" + System.lineSeparator() +
            "mock has been greyscale as mockGrey" + System.lineSeparator() +
            "mock has been greyscale as mockMaskGrey" + System.lineSeparator() +
            "mock has been changed successfully as luma component mockLuma"
            + System.lineSeparator() +
            "mock has been changed successfully as luma component mockMaskLuma" +
            System.lineSeparator() +
            "mock has been changed successfully as max component mockValue" +
            System.lineSeparator() +
            "mock has been changed successfully as max component mockMaskValue" +
            System.lineSeparator() +
            "mock has been changed successfully as intensity component mockIntensity" +
            System.lineSeparator() +
            "mock has been changed successfully as intensity component mockMaskIntensity" +
            System.lineSeparator() +
            "mock has been changed successfully as red component mockRed" +
            System.lineSeparator() +
            "mock has been changed successfully as red component mockMaskRed" +
            System.lineSeparator() +
            "mock has been changed successfully as green component mockGreen" +
            System.lineSeparator() +
            "mock has been changed successfully as green component mockMaskGreen" +
            System.lineSeparator() +
            "mock has been changed successfully as blue component mockBlue" +
            System.lineSeparator() +
            "mock has been changed successfully as blue component mockMaskBlue" +
            System.lineSeparator());
  }

}
