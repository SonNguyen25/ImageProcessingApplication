
import model.image.ImageModel;

import model.pixel.Pixel;

/**
 * This class is created for testing purposes.
 * Specifically, to test inputs got delivered correctly.
 */
public class MockImageModel implements ImageModel {
  private final StringBuilder log;

  /**
   * This constructor creates a log to test for inputs from controller.
   */
  public MockImageModel(StringBuilder log) {
    if (log == null) {
      throw new IllegalArgumentException("Null log!");
    }
    this.log = log;
  }


  /**
   * Brighten or darken the image with the amount inputted by the user.
   * Changed for testing inputs from controller.
   *
   * @param amount represents the amount the user want to brighten/darken the image.
   */
  @Override
  public void brighten(int amount) {
    this.log.append(String.format("amount = %d", amount));
  }

  /**
   * Flip the image vertically or horizontally.
   *
   * @param direction represents the direction of the flip, enter "horizontal" for horizontal flip
   *                  and "vertical" for vertical flip.
   */
  @Override
  public void flip(String direction) {
    //no need
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
    //no need
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

  /**
   * Copy the Image and outputs the copy.
   */
  @Override
  public ImageModel clone() {
    return this;
  }
}
