package model.image;
import javax.imageio.ImageIO;

import model.pixel.Pixel;

/**
 * The class that extends the AbstractImageModel abstract class.
 * The class represents the model for an image of type BMP
 * and its structure are inherited from the abstract class
 * with height, width, and a 2D array of pixels.
 * You can perform image operations with the inherited methods.
 */
public class BMPModel extends AbstractImageModel {

  public BMPModel(int height, int width, int maxValue, Pixel[][] pixels) {
    super(height, width, maxValue, pixels);
  }
}
