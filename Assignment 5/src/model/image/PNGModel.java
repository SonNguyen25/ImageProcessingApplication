package model.image;

import model.pixel.Pixel;

public class PNGModel extends AbstractImageModel {

  /**
   * This constructor takes in the structure of the image that is used for image operations.
   * @param height represents the height of the image
   * @param width represents the width of the image
   * @param pixels represents all the pixels in the image
   */
  public PNGModel(int height, int width, int maxValue, Pixel[][] pixels) {
    super(height, width, maxValue, pixels);
  }

}
