import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class contains tests related to eightbitpixel model class.
 */
public class EightBitPixelModelTest {
  Pixel p;
  Pixel p1;
  Pixel p2;
  Pixel p3;
  Pixel p4;
  Pixel p5;

  /**
   * Setting up variables for testing.
   */
  @Before
  public void setup() {
    p = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 2, 3)));
    p1 = new EightBitPixelModel(new ArrayList<>(Arrays.asList(255, 0, 96)));
    p2 = new EightBitPixelModel(new ArrayList<>(Arrays.asList(0, 12, 68)));
    p3 = new EightBitPixelModel(new ArrayList<>(Arrays.asList(10, 0, 66)));
    p4 = new EightBitPixelModel(new ArrayList<>(Arrays.asList(10, 44, 88)));
    p5 = new EightBitPixelModel(new ArrayList<>(Arrays.asList(44, 123, 99)));
  }

  /**
   * Test the method getColor().
   */
  @Test
  public void testGetColor() {
    List<Integer> rgb = new ArrayList<>(Arrays.asList(1, 2, 3));
    assertEquals(p.getColor(), rgb);
    List<Integer> rgb1 = new ArrayList<>(Arrays.asList(255, 0, 96));
    assertEquals(p1.getColor(), rgb1);
  }

  /**
   * Test the maxVal method.
   */
  @Test
  public void testMaxVal() {
    assertEquals(p.maxVal(), 3);
    assertEquals(p1.maxVal(), 255);
  }

  /**
   * Test intensity method.
   */
  @Test
  public void testIntensity() {
    assertEquals(p.intensity(), (1 + 2 + 3) / 3);
    assertEquals(p1.intensity(), (255 + 96) / 3);
    assertEquals(p3.intensity(), 25);
  }

  /**
   * Test luma method.
   */
  @Test
  public void testLuma() {
    assertEquals(p3.luma(), 7);
    assertEquals(p2.luma(), 13);
  }

  /**
   * Test every case of setRGB method.
   */
  @Test
  public void testSetRGB() {
    //maxValue
    p1.setRGB("max");
    List<Integer> ex = new ArrayList<>(Arrays.asList(255, 255, 255));
    assertEquals(p1.getColor(), ex);

    //luma
    p3.setRGB("luma");
    List<Integer> ex1 = new ArrayList<>(Arrays.asList(7, 7, 7));
    assertEquals(p3.getColor(), ex1);

    //intensity
    p.setRGB("intensity");
    List<Integer> ex2 = new ArrayList<>(Arrays.asList(2, 2, 2));
    assertEquals(p.getColor(), ex2);

    //red
    p2.setRGB("red");
    List<Integer> ex3 = new ArrayList<>(Arrays.asList(0, 0, 0));
    assertEquals(p2.getColor(), ex3);

    //blue
    p4.setRGB("blue");
    List<Integer> ex4 = new ArrayList<>(Arrays.asList(88, 88, 88));
    assertEquals(p4.getColor(), ex4);

    //green
    p5.setRGB("green");
    List<Integer> ex5 = new ArrayList<>(Arrays.asList(123, 123, 123));
    assertEquals(p5.getColor(), ex5);
  }

  /**
   * Test putting an invalid argument (String represent a value) in the method setRGB.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetRGBInvalid() {
    p.setRGB("23");
  }

  /**
   * Test putting an invalid argument (String isn't a case) in the method setRGB.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetRGBInvalid1() {
    p.setRGB("adwdasfdgs");
  }

  /**
   * Test putting an invalid argument (String with characters) in the method setRGB.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetRGBInvalid2() {
    p.setRGB("/.?@");
  }

  /**
   * Test having a list of rgb values that is oversize.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOversizeRGB() {
    new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
  }

  /**
   * Test having an undersized rgb values list.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUndersizedRGB() {
    new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 2)));
  }

  /**
   * Test null rgb list.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullRGB() {
    new EightBitPixelModel(null);
  }

  /**
   * Test copy method.
   */
  @Test
  public void testCopy() {
    boolean result = p.getColor().get(0) == p.copy().getColor().get(0)
            && p.getColor().get(1) == p.copy().getColor().get(1)
            && p.getColor().get(2) == p.copy().getColor().get(2)
            && p.getColor().size() == p.copy().getColor().size();
    assertTrue(result);
  }

}
