
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import model.operations.Operations;
import model.operations.Operations.OperationType;
import model.operations.Operator;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;

import static model.operations.Operations.OperationType.Blur;
import static model.operations.Operations.OperationType.Brighten;
import static model.operations.Operations.OperationType.Flip;
import static model.operations.Operations.OperationType.GreyScale;
import static model.operations.Operations.OperationType.Sepia;
import static model.operations.Operations.OperationType.Sharpen;
import static org.junit.Assert.assertEquals;

/**
 * Test class to test methods related to Operator class.
 */
public class OperatorTest {
  Pixel[][] pix;
  Pixel[][] pix1;
  Pixel[][] pix2;
  Pixel[][] pix3;
  Pixel[][] pix4;
  Pixel[][] pix5;
  Operations op;
  Operations op1;
  Operations op2;
  Operations op3;
  Operations op4;
  Operations op5;

  /**
   * Setting up variables for the test.
   */
  @Before
  public void setUp() {
    pix = new Pixel[2][2];
    pix[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    op = new Operator(pix, null);
    pix1 = new Pixel[2][2];
    pix1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    op1 = new Operator(pix1, null);
    pix2 = new Pixel[2][2];
    pix2[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix2[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix2[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix2[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    op2 = new Operator(pix2, null);
    pix3 = new Pixel[2][2];
    pix3[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix3[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix3[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix3[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    op3 = new Operator(pix3, null);
    pix4 = new Pixel[2][2];
    pix4[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix4[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix4[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix4[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    op4 = new Operator(pix4, null);
    pix5 = new Pixel[2][2];
    pix5[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix5[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix5[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix5[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
    op5 = new Operator(pix5, null);
  }

  /**
   * Test operations on update.
   */
  @Test
  public void updatelumagreyscale() {
    Pixel[][] luma = new Pixel[2][2];
    luma[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 1)));
    luma[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 3, 3)));
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
        mockString6 += (op.update(OperationType.LumaGreyScale, 0, "")[k][l]
                .getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(lumaString, mockString6);
  }

  /**
   * Test flip.
   */
  @Test
  public void testFlip() {
    //horizontal
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
    Pixel[][] a = op.update(Flip, 0, "horizontal");
    String mockString = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString += (a[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(flipping, mockString);

    //vertical
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
    Pixel[][] b = op.update(Flip, 0, "vertical");
    String mockString1 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString1 += (b[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(flipping1, mockString1);
  }

  /**
   * Test components greyscale.
   */
  @Test
  public void testcomponent() {
    //red
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
        mockString1 += (op.update(GreyScale, 0, "red")[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(redString, mockString1);

    //green
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
        mockString2 += (op1.update(GreyScale, 0, "green")[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(greenString, mockString2);

    //blue
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
        mockString3 += (op2.update(GreyScale, 0, "blue")[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(blueString, mockString3);

    //maxValue
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
        mockString4 += (op3.update(GreyScale, 0, "max")[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(maxString, mockString4);

    //intensity
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
        mockString5 += (op4.update(GreyScale, 0, "intensity")[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(intensityString, mockString5);

    //luma
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
    Pixel[][] a = (op5.update(GreyScale, 0, "luma"));
    String mockString6 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString6 += (a[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(lumaString, mockString6);
  }

  /**
   * Test brighten.
   */
  @Test
  public void testbright() {
    //brightening
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
    Pixel[][] a = (op.update(Brighten, 1, ""));
    String mockString = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString += a[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")"));
      }
    }
    assertEquals(brightening, mockString);


    //brightening to a level higher than the maximum value of RGB
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
    Pixel[][] b = (op.update(Brighten, 255, ""));
    String mock1String = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mock1String += (b[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(brightening1, mock1String);

    //darkening
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

    Pixel[][] c = (op.update(Brighten, -1, ""));
    String mock1String1 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mock1String1 += (c[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(brightening2, mock1String1);

    //darken past the minimum value for RGB, test if it ensures the RGB values won't go below 0.
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
    Pixel[][] d = (op.update(Brighten, -255, ""));
    String mock1String2 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mock1String2 += (d[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(brightening3, mock1String2);
  }

  /**
   * Test for null list of pixels.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullPixels() {
    new Operator(null, null);
  }

  /**
   * Test sepia.
   */
  @Test
  public void testsepia() {
    Pixel[][] sepia = new Pixel[2][2];
    sepia[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 2, 1)));
    sepia[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 4, 3)));
    sepia[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 3, 3)));
    sepia[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(6, 6, 3)));
    String sepiaString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        sepiaString += (sepia[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    Pixel[][] a = op.update(Sepia, 0, "");
    String mockString6 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString6 += (a[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(sepiaString, mockString6);
  }


  /**
   * Test blur.
   */
  @Test
  public void testblur() {
    Pixel[][] blur = new Pixel[2][2];
    blur[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 0, 0)));
    blur[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 0, 0)));
    blur[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 1)));
    blur[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 0)));
    String blurString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        blurString += (blur[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    Pixel[][] a = op.update(Blur, 0, "");
    String mockString6 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString6 += (a[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(blurString, mockString6);
  }

  /**
   * Test sharpen.
   */
  @Test
  public void testSharpen() {
    Pixel[][] sharp = new Pixel[2][2];
    sharp[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 2, 4)));
    sharp[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(10, 2, 3)));
    sharp[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(6, 2, 5)));
    sharp[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(9, 4, 5)));
    String sharpString = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        sharpString += (sharp[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    Pixel[][] a = op.update(Sharpen, 0, "");
    String mockString6 = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString6 += (a[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(sharpString, mockString6);
  }

  /**
   * Test putting an invalid argument (String represent a value) in the method greyScale.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGreyScaleInvalid() {
    op.update(GreyScale, 0, "23");
  }


  /**
   * Test putting an invalid argument (String isn't a case) in the method greyScale.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGreyScaleInvalid1() {
    op.update(GreyScale, 0, "adwdasfdgs");
  }

  /**
   * Test putting an invalid argument (String with characters) in the method greyScale.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGreyScaleInvalid2() {
    op.update(GreyScale, 0, "/.?@");
  }

  /**
   * Test flip with invalid directions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFlipInvalid() {
    op.update(Flip, 0, "/.?@031asdas");
  }


}