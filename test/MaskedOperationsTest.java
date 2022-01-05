import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import model.image.IOModel;
import model.image.ImageModel;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;

import static model.operations.Operations.OperationType.Blur;
import static model.operations.Operations.OperationType.GreyScale;
import static model.operations.Operations.OperationType.LumaGreyScale;
import static model.operations.Operations.OperationType.Sepia;
import static model.operations.Operations.OperationType.Sharpen;
import static org.junit.Assert.assertEquals;

/**
 * Testing class for masked operations.
 */
public class MaskedOperationsTest {
  ImageModel mock;
  ImageModel mask1;
  ImageModel mask2;
  ImageModel mask3;
  ImageModel mock4;
  ImageModel mock5;
  ImageModel mock2;
  ImageModel mock3;
  ImageModel mock1;
  Pixel[][] pix;
  Pixel[][] pix1;
  Pixel[][] pix2;
  Pixel[][] pix3;
  Pixel[][] pix4;
  Pixel[][] pix5;
  Pixel[][] pix6;
  Pixel[][] pix7;
  Pixel[][] pix8;


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
    pix1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    pix1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    pix1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    pix1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    mask1 = new IOModel(2, 2, 255, pix1);
    pix2 = new Pixel[2][2];
    pix2[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    pix2[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    pix2[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    pix2[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    mask2 = new IOModel(2, 2, 255, pix2);
    pix3 = new Pixel[2][2];
    pix3[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    pix3[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    pix3[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    pix3[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    mask3 = new IOModel(2, 2, 255, pix3);
    pix4 = new Pixel[2][2];
    pix4[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix4[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix4[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix4[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock4 = new IOModel(2, 2, 255, pix4);
    pix5 = new Pixel[2][2];
    pix5[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix5[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix5[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix5[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock5 = new IOModel(2, 2, 255, pix5);
    pix6 = new Pixel[2][2];
    pix6[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix6[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix6[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix6[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock1 = new IOModel(2, 2, 255, pix6);
    pix7 = new Pixel[2][2];
    pix7[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix7[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix7[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix7[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock2 = new IOModel(2, 2, 255, pix7);
    pix8 = new Pixel[2][2];
    pix8[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix8[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix8[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix8[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock3 = new IOModel(2, 2, 255, pix8);
  }

  /**
   * Test blurring image with mask.
   */
  @Test
  public void testBlur() {
    //test mask
    mock.getUpdated(Blur, 0, "", mask1);
    Pixel[][] blur = new Pixel[2][2];
    blur[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    blur[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 0, 0)));
    blur[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    blur[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 0)));
    String blurString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        blurString += (blur[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString6 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString6 += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(blurString, mockString6);

    //test full white mask
    mock4.getUpdated(Blur, 0, "", mask2);
    Pixel[][] blur1 = new Pixel[2][2];
    blur1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    blur1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    blur1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    blur1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    String blurString1 = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        blurString1 += (blur1[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString += (mock4.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(blurString1, mockString);

    //test full black mask
    mock5.getUpdated(Blur, 0, "", mask3);
    Pixel[][] blur2 = new Pixel[2][2];
    blur2[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 0, 0)));
    blur2[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 0, 0)));
    blur2[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 1)));
    blur2[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 0)));
    String blurString2 = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        blurString2 += (blur2[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString1 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString1 += (mock5.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(blurString2, mockString1);

  }

  /**
   * Test sharpen image with masks.
   */
  @Test
  public void testSharpen() {
    //test mask
    mock.getUpdated(Sharpen, 0, "", mask1);
    Pixel[][] sharp = new Pixel[2][2];
    sharp[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    sharp[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(9, 2, 2)));
    sharp[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    sharp[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(7, 4, 4)));
    String sharpString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        sharpString += (sharp[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString6 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString6 += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(sharpString, mockString6);

    //test white mask
    mock4.getUpdated(Sharpen, 0, "", mask2);
    Pixel[][] sharp1 = new Pixel[2][2];
    sharp1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    sharp1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    sharp1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    sharp1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    String sharpString1 = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        sharpString1 += (sharp1[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString += (mock4.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(sharpString1, mockString);

    //test black mask
    mock5.getUpdated(Sharpen, 0, "", mask3);
    Pixel[][] sharp2 = new Pixel[2][2];
    sharp2[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 2, 4)));
    sharp2[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(10, 2, 3)));
    sharp2[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(6, 2, 5)));
    sharp2[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(9, 4, 5)));
    String sharpString2 = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        sharpString2 += (sharp2[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString1 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString1 += (mock5.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(sharpString2, mockString1);
  }

  /**
   * Test Sepia with masks.
   */
  @Test
  public void testSepia() {
    //test mask
    mock.getUpdated(Sepia, 0, "", mask1);
    Pixel[][] sepia = new Pixel[2][2];
    sepia[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    sepia[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 4, 3)));
    sepia[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    sepia[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(6, 6, 3)));
    String sepiaString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        sepiaString += (sepia[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString6 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString6 += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(sepiaString, mockString6);

    //test white mask
    mock5.getUpdated(Sepia, 0, "", mask2);
    Pixel[][] sepia2 = new Pixel[2][2];
    sepia2[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    sepia2[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    sepia2[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    sepia2[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    String sepiaString2 = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        sepiaString2 += (sepia2[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString1 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString1 += (mock5.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(sepiaString2, mockString1);

    //test black mask
    mock4.getUpdated(Sepia, 0, "", mask3);
    Pixel[][] sepia1 = new Pixel[2][2];
    sepia1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 2, 1)));
    sepia1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 4, 3)));
    sepia1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 3, 3)));
    sepia1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(6, 6, 3)));
    String sepiaString1 = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        sepiaString1 += (sepia1[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString += (mock4.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(sepiaString1, mockString);
  }

  /**
   * Test luma greyscale as a filter with mask.
   */
  @Test
  public void testLumaGreyscale() {
    //luma
    mock5.getUpdated(LumaGreyScale, 0, "", mask1);
    Pixel[][] luma = new Pixel[2][2];
    luma[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    luma[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 3, 3)));
    luma[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    luma[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 4, 4)));
    String lumaString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        lumaString += (luma[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString6 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString6 += (mock5.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(lumaString, mockString6);
  }

  /**
   * Test greyScale and all the ways to greyscale an image with masks.
   */
  @Test
  public void testGreyScale() {
    //red
    mock.getUpdated(GreyScale, 0, "red", mask1);
    Pixel[][] red = new Pixel[2][2];
    red[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    red[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 8, 8)));
    red[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    red[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 5, 5)));
    String redString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        redString += (red[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString1 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString1 += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(redString, mockString1);

    //green
    mock1.getUpdated(GreyScale, 0, "green", mask1);
    Pixel[][] green = new Pixel[2][2];
    green[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    green[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
    green[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    green[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 4, 4)));
    String greenString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        greenString += (green[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString2 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString2 += (mock1.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(greenString, mockString2);

    //blue
    mock2.getUpdated(GreyScale, 0, "blue", mask1);
    Pixel[][] blue = new Pixel[2][2];
    blue[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    blue[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
    blue[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    blue[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 3, 3)));
    String blueString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        blueString += (blue[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString3 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString3 += (mock2.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(blueString, mockString3);

    //maxValue
    mock3.getUpdated(GreyScale, 0, "max", mask1);
    Pixel[][] max = new Pixel[2][2];
    max[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    max[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 8, 8)));
    max[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    max[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 5, 5)));
    String maxString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        maxString += (max[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString4 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString4 += (mock3.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(maxString, mockString4);

    //intensity
    mock4.getUpdated(GreyScale, 0, "intensity", mask1);
    Pixel[][] intensity = new Pixel[2][2];
    intensity[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    intensity[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 3, 3)));
    intensity[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    intensity[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 4, 4)));
    String intensityString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        intensityString += (intensity[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString5 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString5 += (mock4.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(intensityString, mockString5);

    //luma
    mock5.getUpdated(GreyScale, 0, "luma", mask1);
    Pixel[][] luma = new Pixel[2][2];
    luma[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    luma[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 2, 2)));
    luma[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    luma[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 4, 4)));
    String lumaString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        lumaString += (luma[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString6 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString6 += (mock5.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(lumaString, mockString6);
  }

}
