import java.util.ArrayList;
import java.util.Arrays;

import model.image.AbstractImageModel;
import model.image.ImageModel;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;

public class MockImageModel implements ImageModel {
  private int height;
  private int width;
  private int maxValue;
  private Pixel[][] pixels;

  /**
   * This constructor creates a MockImageModel pixel array used for testing.
   */
  public MockImageModel() {
    this.width = 2;
    this.height = 2;
    this.maxValue = 255;
    this.pixels = new Pixel[height][width];
    this.pixels[0][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(1, 1, 3)));
    this.pixels[0][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(8, 1, 1)));
    this.pixels[1][0] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(2, 1, 4)));
    this.pixels[1][1] = new EightBitPixelModel(new ArrayList<>(Arrays.asList(5, 4, 3)));
  }

  /**
   * Brighten or darken the image with the amount inputted by the user.
   *
   * @param amount represents the amount the user want to brighten/darken the image.
   */
  @Override
  public void brighten(int amount) {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < this.pixels[i][j].getColor().size(); k++) {
          this.pixels[i][j].getColor().set(k, this.pixels[i][j].getColor().get(k) + amount);
        }
      }
    }
    ensureVal(this.pixels);
  }



  /**
   * Ensure the minimum value and maximum value of rgb doesn't go out of range.
   * @param pixels represent the 2D array of pixels.
   * @return the Pixel list after its rgb values are ensured to be in the correct range.
   */
  protected Pixel[][] ensureVal(Pixel[][] pixels) {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        for (int k = 0; k < pixels[i][j].getColor().size(); k++) {
          if (pixels[i][j].getColor().get(k) < 0) {
            pixels[i][j].getColor().set(k, 0);
          } else if (this.pixels[i][j].getColor().get(k) > this.maxValue) {
            pixels[i][j].getColor().set(k, this.maxValue);
          }
        }
      }
    }
    return pixels;
  }

  /**
   * Flip the image vertically or horizontally.
   *
   * @param direction represents the direction of the flip, enter "horizontal" for horizontal flip
   *                  and "vertical" for vertical flip.
   */
  @Override
  public void flip(String direction) {

  }

  /**
   * Convert the image colors into greyscale.
   *
   * @param ways represents different way to turn the image into greyscale, enter:
   *             "luma" to convert it using the luma value,
   *             "intensity" to convert it using the intensity value,
   *             "value" to convert it using the maximum  value of rgb of a pixel,
   *             "red" to convert it by changing every rgb values of a pixel into its red value,
   *             "green" to convert it by changing every rgb values of a pixel into its green value,
   *             "blue" to convert it by changing every rgb values of a pixel into its blue value.
   */
  @Override
  public void greyScale(String ways) {

  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public Pixel[][] getPixels() {
    return new Pixel[2][2];
  }
}
