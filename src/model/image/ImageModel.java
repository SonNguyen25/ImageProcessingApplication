package model.image;

import model.operations.Operations.OperationType;
import model.pixel.Pixel;

/**
 * This interface represents the operations offered by the image model.
 * One object of the model represents one model of an image's type and its operations.
 */
public interface ImageModel {

  /**
   * This is the method to pass the operation type and possible passed arguments.
   * @param type The command types.
   * @param change The change as integer.
   * @param direction The change as string indicator.
   * @param maskedImage The masked image for the operations.
   */
  void getUpdated(OperationType type, int change, String direction, ImageModel maskedImage);


  /**
   * Get the width of the image.
   * @return the width of the image.
   */
  int getWidth();

  /**
   * Get the height of the image.
   * @return the height of the image.
   */
  int getHeight();

  /**
   * Get the pixels of the image.
   * @return the pixels of the image.
   */
  Pixel[][] getPixels();

  /**
   * Copy the Image and outputs the copy.
   */
  ImageModel clone();
}
