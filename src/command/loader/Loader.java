package command.loader;

import model.image.ImageModel;

/**
 * This is the interface for Loader that loads model from an input path. It allows for loading
 * different types of images.
 */
public interface Loader {


  /**
   * This is an abstract method that allows the Loader to load the path's file and return it into
   * as an ImageModel.
   * @return ImageModel that represents the input image file.
   * @throws IllegalStateException When the loading fails, it will occur.
   */
  ImageModel load() throws IllegalStateException;
}
