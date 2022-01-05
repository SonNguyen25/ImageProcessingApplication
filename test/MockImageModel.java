
import model.image.ImageModel;

import model.operations.Operations;
import model.pixel.Pixel;

/**
 * This class is created for testing purposes.
 * Specifically, to test inputs got delivered correctly.
 */
public class MockImageModel implements ImageModel {
  private final StringBuilder log;

  /**
   * This constructor creates a log to test for inputs from controller.
   */
  public MockImageModel(StringBuilder log) {
    if (log == null) {
      throw new IllegalArgumentException("Null log!");
    }
    this.log = log;
  }

  @Override
  public void getUpdated(Operations.OperationType type, int change, String direction,
                         ImageModel masked) {
    this.log.append(String.format("amount = %d", change));
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public Pixel[][] getPixels() {
    return new Pixel[2][2];
  }

  /**
   * Copy the Image and outputs the copy.
   */
  @Override
  public ImageModel clone() {
    return this;
  }
}
