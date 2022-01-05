package command.loader;

import model.image.ImageModel;

/**
 * This is the abstract that might can be compatible with different image types' loading (for images
 * that only have 3 channels r, g, b). It can load a PPM file. Other image type in the future can
 * use a class to extends it and overwrite the load function or adding small pieces.
 */

public abstract class AbstractRGBLoader implements Loader {
  protected String in;

  /**
   * This is the constructor for the Abstract class that takes in a String as the input file's path.
   * @param in The input file path.
   * @throws IllegalStateException When the input is null, it will appear.
   */
  public AbstractRGBLoader(String in)
          throws IllegalArgumentException {
    if (in == null) {
      System.out.println("Cannot find INPUT path.");
      throw new IllegalArgumentException();
    }
    this.in = in;
  }

  /**
   * This is an abstract method that allows the Loader to load the path's file and return it into
   * as an ImageModel.
   * @return ImageModel that represents the input image file.
   * @throws IllegalStateException When the loading fails, it will occur.
   */
  public abstract ImageModel load() throws IllegalStateException;

}
