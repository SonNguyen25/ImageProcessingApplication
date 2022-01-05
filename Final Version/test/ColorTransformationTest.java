import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


import model.operations.ColorTransformation;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class to test related methods in class ColorTransformation.
 */
public class ColorTransformationTest {

  /**
   * Test process method along with mask image.
   */
  @Test
  public void testprocess() {
    //no mask image
    double[][] a = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
    Pixel[][] pix1 = new Pixel[2][2];
    pix1[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix1[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix1[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix1[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));

    ColorTransformation as = new ColorTransformation(a, pix1);

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
        mockString6 += (as.process(null)[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(lumaString, mockString6);

    //mask image included
    Pixel[][] pix2 = new Pixel[2][2];
    pix2[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    pix2[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    pix2[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    pix2[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));

    ColorTransformation ax = new ColorTransformation(a, pix2);

    Pixel[][] lumas = new Pixel[2][2];
    lumas[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    lumas[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(3, 3, 3)));
    lumas[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    lumas[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(4, 4, 4)));

    Pixel[][] pix = new Pixel[2][2];
    pix[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    pix[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));
    pix[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 255, 255)));
    pix[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 0, 0)));

    String lumaStrings = "";
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        lumaStrings += (lumas[i][j].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    String mockString = "";
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        mockString += (ax.process(pix)[k][l].getColor().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "(", ")")));
      }
    }
    assertEquals(lumaStrings, mockString);
  }
}