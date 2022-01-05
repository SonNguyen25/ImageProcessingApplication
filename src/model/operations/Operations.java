package model.operations;

import model.pixel.Pixel;

/**
 * This is an interface for receiving different kinds of commands toward the pixels.
 */
public interface Operations {

  /**
   * This is different types of operations that can be used for commands.
   */
  enum OperationType {
    GreyScale, Flip, Brighten, Blur, Sharpen, LumaGreyScale, Sepia, Downscale
  }

  /**
   * This is a method for updating the pixels by given operation types.
   * @param type Required command for operation types.
   * @param change Changes might needed as integer.
   * @param direction String indicator might needed.
   * @return An updated 2-D Pixels.
   */
  Pixel[][] update(OperationType type, int change, String direction);
}
