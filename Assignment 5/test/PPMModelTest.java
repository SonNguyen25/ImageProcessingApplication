import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import model.image.ImageModel;
import model.image.PPMModel;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class to test all operations and methods related to the PPMModel Class.
 */
public class PPMModelTest {
  ImageModel mock;
  ImageModel mock1;
  ImageModel mock2;
  ImageModel mock3;
  ImageModel mock4;
  ImageModel mock5;
  ImageModel mock6;
  Pixel[][] pix;
  Pixel[][] pix1;
  Pixel[][] pix2;
  Pixel[][] pix3;
  Pixel[][] pix4;
  Pixel[][] pix5;
  Pixel[][] pix6;

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
    mock = new PPMModel(2, 2, 255, pix);
    pix1 = new Pixel[2][2];
    pix1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock1 = new PPMModel(2, 2, 255, pix1);
    pix2 = new Pixel[2][2];
    pix2[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix2[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix2[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix2[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock2 = new PPMModel(2, 2, 255, pix2);
    pix3 = new Pixel[2][2];
    pix3[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix3[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix3[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix3[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock3 = new PPMModel(2, 2, 255, pix3);
    pix4 = new Pixel[2][2];
    pix4[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix4[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix4[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix4[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock4 = new PPMModel(2, 2, 255, pix4);
    pix5 = new Pixel[2][2];
    pix5[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix5[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix5[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix5[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    mock5 = new PPMModel(2, 2, 255, pix5);
    pix6 = new Pixel[6][8];
    mock6 = new PPMModel(6, 8, 255, pix6);
  }


  /**
   * Test brighten method, to see if it can darken/brighten the pixels.
   * Also test if the method prevent the RGB values to go beneath 0 and above the maximum value
   * of RGB of the image.
   */
  @Test
  public void brighten() {
    //brightening
    mock.brighten(1);
    Pixel[][] brighten = new Pixel[2][2];
    brighten[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 2, 4)));
    brighten[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(9, 2, 2)));
    brighten[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 2, 5)));
    brighten[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(6, 5, 4)));
    String brightening = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        brightening += (brighten[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(brightening, mockString);


    //brightening to a level higher than the maximum value of RGB
    mock.brighten(255);
    Pixel[][] brighten1 = new Pixel[2][2];
    brighten1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    brighten1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    brighten1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    brighten1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    String brightening1 = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        brightening1 += (brighten1[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mock1String = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mock1String += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(brightening1, mock1String);

    //darkening
    mock.brighten(-1);
    Pixel[][] brighten2 = new Pixel[2][2];
    brighten2[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(254, 254, 254)));
    brighten2[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(254, 254, 254)));
    brighten2[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(254, 254, 254)));
    brighten2[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(254, 254, 254)));
    String brightening2 = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        brightening2 += (brighten2[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mock1String1 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mock1String1 += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(brightening2, mock1String1);

    //darken past the minimum value for RGB, test if it ensures the RGB values won't go below 0.
    mock.brighten(-255);
    Pixel[][] brighten3 = new Pixel[2][2];
    brighten3[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    brighten3[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    brighten3[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    brighten3[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    String brightening3 = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        brightening3 += (brighten3[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mock1String2 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mock1String2 += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(brightening3, mock1String2);
  }

  /**
   * Test flip method.
   */
  @Test
  public void testFlip() {
    //horizontal
    mock.flip("horizontal");
    Pixel[][] flip = new Pixel[2][2];
    flip[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    flip[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    flip[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    flip[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    String flipping = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        flipping += (flip[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(flipping, mockString);

    //vertical
    mock.flip("vertical");
    Pixel[][] flip1 = new Pixel[2][2];
    flip1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    flip1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    flip1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    flip1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    String flipping1 = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        flipping1 += (flip1[i][j].getColor().stream()
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
    assertEquals(flipping1, mockString1);
  }

  /**
   * Test greyScale method and all the ways to greyscale an image.
   */
  @Test
  public void testGreyScale() {
    //red
    mock.greyScale("red");
    Pixel[][] red = new Pixel[2][2];
    red[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
    red[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 8, 8)));
    red[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 2, 2)));
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
    mock1.greyScale("green");
    Pixel[][] green = new Pixel[2][2];
    green[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
    green[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
    green[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
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
    mock2.greyScale("blue");
    Pixel[][] blue = new Pixel[2][2];
    blue[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 3, 3)));
    blue[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
    blue[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 4, 4)));
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
    mock3.greyScale("max");
    Pixel[][] max = new Pixel[2][2];
    max[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 3, 3)));
    max[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 8, 8)));
    max[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 4, 4)));
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
    mock4.greyScale("intensity");
    Pixel[][] intensity = new Pixel[2][2];
    intensity[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
    intensity[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 3, 3)));
    intensity[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 2, 2)));
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
    mock5.greyScale("luma");
    Pixel[][] luma = new Pixel[2][2];
    luma[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
    luma[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 2, 2)));
    luma[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
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
   * Test putting an invalid argument (String represent a value) in the method greyScale.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGreyScaleInvalid() {
    mock.greyScale("23");
  }

  /**
   * Test putting an invalid argument (String isn't a case) in the method greyScale.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGreyScaleInvalid1() {
    mock.greyScale("adwdasfdgs");
  }

  /**
   * Test putting an invalid argument (String with characters) in the method greyScale.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGreyScaleInvalid2() {
    mock.greyScale("/.?@");
  }

  /**
   * Test getWidth method.
   */
  @Test
  public void testGetWidth() {
    assertEquals(mock.getWidth(), 2);
    assertEquals(mock1.getWidth(), 2);
    assertEquals(mock6.getWidth(), 8);
  }

  /**
   * Test getHeight method.
   */
  @Test
  public void testGetHeight() {
    assertEquals(mock.getHeight(), 2);
    assertEquals(mock1.getHeight(), 2);
    assertEquals(mock6.getHeight(), 6);
  }

  /**
   * Test getPixel method.
   */
  @Test
  public void testGetPixel() {
    assertTrue(mock.getPixels().equals(pix));
    assertTrue(mock2.getPixels().equals(pix2));
    assertTrue(mock6.getPixels().equals(pix6));
  }

  /**
   * Test clone method.
   */
  @Test
  public void testClone() {
    assertEquals(mock.clone().getWidth(), (mock.getWidth()));
    assertEquals(mock.clone().getHeight(), (mock.getHeight()));
    String mockClone = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        mockClone += (mock.clone().getPixels()[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockReal = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockReal += (mock.getPixels()[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(mockClone, mockReal); //compare the array of pixels.
  }

  /**
   * Test negative height as an argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHeight() {
    new PPMModel(-1, 0, 255, pix);
  }

  /**
   * Test negative width as an argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWidth() {
    new PPMModel(1, -1, 255, pix);
  }

  /**
   * Test null Pixel list as an argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    new PPMModel(1, -1, 255, null);
  }
}

