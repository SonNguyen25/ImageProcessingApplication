package model.image;

import model.operations.Operations.OperationType;
import model.operations.Operator;
import model.pixel.Pixel;

/**
 * This class is an abstract implementation of the ImageModel interface. This class
 * contains methods for image operations and any related helper methods. Also,
 * this abstract class helps remove any redundancy in any of the other models for other types
 * of images.
 */
public abstract class AbstractImageModel implements ImageModel {
  private int width;
  private int height;
  private final int maxValue;
  private Pixel[][] pixels;

  /**
   * This constructor takes in the structure of the image that is used for image operations.
   *
   * @param height represents the height of the image
   * @param width  represents the width of the image
   * @param pixels represents all the pixels in the image
   */
  public AbstractImageModel(int height, int width, int maxValue, Pixel[][] pixels) {
    this.maxValue = maxValue;
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("Invalid dimensions of image!");
    }
    this.height = height;
    this.width = width;
    if (pixels == null || pixels.length > height || pixels.length < height
            || pixels[0].length > width || pixels[0].length < width) {
      throw new IllegalArgumentException("Null pixels or invalid amount of pixels according " +
              "to the given height and width!");
    }
    this.pixels = pixels;
  }

  /**
   * Get the width of the image.
   *
   * @return the width of the image.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height of the image.
   *
   * @return the height of the image.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Get the pixels of the image.
   *
   * @return the pixels of the image.
   */
  @Override
  public Pixel[][] getPixels() {
    Pixel[][] pixelCopy = this.pixels;
    return pixelCopy;
  }

  /**
   * Copy the Image and outputs the copy.
   */
  @Override
  public ImageModel clone() {
    ImageModel copy;
    Pixel[][] pixelCopy = new Pixel[this.height][this.width];
    for (int i = 0; i < this.pixels.length; i++) {
      for (int k = 0; k < this.pixels[i].length; k++) {
        pixelCopy[i][k] = this.pixels[i][k].copy();
      }
    }
    copy = returnModel(this.height, this.width, this.maxValue, pixelCopy);
    return copy;
  }

  /**
   * Return the model initiated.
   *
   * @param height   represents the height of the image
   * @param width    represents the width of the image
   * @param maxValue represents the max value of rgb of the image
   * @param pixels   represents the list of pixels of the image
   */
  protected abstract ImageModel returnModel(int height, int width, int maxValue, Pixel[][] pixels);

  /**
   * This is the method to pass the operation type and possible passed arguments.
   *
   * @param type      The command types.
   * @param change    The change as integer.
   * @param direction The change as string indicator.
   * @param maskedImage The masked image for the operations.
   */
  @Override
  public void getUpdated(OperationType type, int change, String direction, ImageModel maskedImage) {
    this.pixels = new Operator(this.clone().getPixels(),
            (maskedImage == null) ? null : maskedImage.clone().getPixels())
            .update(type, change, direction);
    if (type == OperationType.Downscale) {
      this.height = change;
      this.width = Integer.valueOf(direction);
    }
  }
}
