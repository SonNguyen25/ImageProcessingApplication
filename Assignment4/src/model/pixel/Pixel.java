package model.pixel;

import java.util.List;

/**
 * This interface represents the operations offered by the pixel model.
 * One object of the model represents one pixel of an image.
 */
public interface Pixel {

  /**
   * Get the rgb representation of the pixel.
   * @return the rgb representation of the pixel.
   */
  List<Integer> getColor();

  /**
   * Get the maximum value of the three components for the pixel.
   * @return the maximum value of the three components for the pixel.
   */
  int maxVal();

  /**
   * Get the average of the three components for the pixel.
   * @return the average of the three components the pixel.
   */
  int intensity();

  /**
   * Get the luma (the levels of brightness) of the pixel.
   * @return the luma of the pixel.
   */
  int luma();

  /**
   * Change all rgb values of a pixel to the luma value, the intensity value, max value of RGB,
   * the red component, the blue component, or the green component.
   */
  void setRGB(String type);

  /**
   * Copy the pixel and return its copy.
   * @return the copy of the pixel.
   */
  Pixel copy();
}
