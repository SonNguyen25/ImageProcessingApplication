package model.image;

import model.pixel.Pixel;

/**
 * This class is an abstract implementation of the ImageModel interface. This class
 * contains methods for image operations and any related helper methods. Also,
 * this abstract class helps remove any redundancy in any of the other models for other types
 * of images.
 */
public abstract class AbstractImageModel implements ImageModel {
  private final int width;
  private final int height;
  private final int maxValue;
  private final Pixel[][] pixels;

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
    if (pixels == null) {
      throw new IllegalArgumentException("Null pixels!");
    }
    this.pixels = pixels;
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
   *
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
   * @param direction represents the direction of the flip, enter horizontal for horizontal flip
   *                  and vertical for vertical flip.
   */
  @Override
  public void flip(String direction) {
    if (direction.equals("horizontal")) {
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width / 2; j++) {
          Pixel temporary = this.pixels[i][j];
          this.pixels[i][j] = this.pixels[i][this.width - j - 1];
          this.pixels[i][this.width - j - 1] = temporary;
        }
      }
    } else if (direction.equals("vertical")) {
      for (int i = 0; i < this.height / 2; i++) {
        for (int j = 0; j < this.width; j++) {
          Pixel temporary = this.pixels[i][j];
          this.pixels[i][j] = this.pixels[this.height - i - 1][j];
          this.pixels[this.height - i - 1][j] = temporary;
        }
      }
    } else {
      throw new IllegalArgumentException("Invalid direction!");
    }
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
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.pixels[i][j].setRGB(ways);
      }
    }
    ensureVal(this.pixels);
  }

  /**
   * Get the width of the image.
   *
   * @return the width of the image.
   */
  @Override
  public int getWidth() {
    int copy = this.width;
    return copy;
  }

  /**
   * Get the height of the image.
   *
   * @return the height of the image.
   */
  @Override
  public int getHeight() {
    int copy = this.height;
    return copy;
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
    copy = new PPMModel(height, width, this.maxValue, pixelCopy);
    return copy;
  }

}
