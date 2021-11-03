package model.image;

import model.pixel.Pixel;

/**
 * The class that extends the AbstractImageModel abstract class.
 * The class represents the model for an image of type PPM
 * and its structure are inherited from the abstract class
 * with height, width, and a 2D array of pixels.
 * You can perform image operations with the inherited methods.
 */
public class PPMModel extends AbstractImageModel {

  /**
   * This constructor takes in the structure of the image that is used for image operations.
   * @param height represents the height of the image
   * @param width represents the width of the image
   * @param pixels represents all the pixels in the image
   */
  public PPMModel(int height, int width, Pixel[][] pixels) {
    super(height, width, pixels);
  }

}
