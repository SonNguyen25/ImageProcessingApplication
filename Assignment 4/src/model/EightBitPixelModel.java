package model;

import java.util.List;

/**
 * The class that extends the AbstractPixelModel abstract class.
 * The class represents the model for a pixel with a 2D array of pixels.
 * You can perform operations related to pixels with the inherited methods.
 */
public class EightBitPixelModel extends AbstractPixelModel {

  /**
   * A constructor taking in the structure of the pixel (its list of RGB values).
   * @param rgb represent the list of RGB values.
   */
  public EightBitPixelModel(List<Integer> rgb) {
    super(rgb);
  }

}
