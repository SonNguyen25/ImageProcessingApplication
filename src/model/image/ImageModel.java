package model.image;

/**
 * This interface represents the operations offered by the image model.
 * One object of the model represents one model of an image's type and its operations.
 */
public interface ImageModel {

  /**
   * Brighten or darken the image with the amount inputted by the user.
   * @param amount represents the amount the user want to brighten/darken the image.
   */
  void brighten(int amount);

  /**
   * Flip the image vertically or horizontally.
   * @param direction represents the direction of the flip, enter "horizontal" for horizontal flip
   *                  and "vertical" for vertical flip.
   */
  void flip(String direction);

  /**
   * Convert the image colors into greyscale.
   * @param ways represents different way to turn the image into greyscale, enter:
   *             "luma" to convert it using the luma value,
   *             "intensity" to convert it using the intensity value,
   *             "value" to convert it using the maximum  value of rgb of a pixel,
   *             "red" to convert it by changing every rgb values of a pixel into its red value,
   *             "green" to convert it by changing every rgb values of a pixel into its green value,
   *             "blue" to convert it by changing every rgb values of a pixel into its blue value.
   */
  void greyScale(String ways);
}
