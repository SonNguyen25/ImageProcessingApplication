package controller;

/**
 * Represents a ViewListener interface that contains operations to
 * parse the user GUI inputs and deliver changes to the model/library/view based off of
 * these inputs.
 */
public interface ViewListener {

  /**
   * Load the file into the library, and updates the corresponding view.
   * @param filepath represents the filepath.
   * @param currentName represents the current name of the file being load.
   */
  void loadEvent(String filepath, String currentName);

  /**
   * Save the file into the user's chosen directory.
   * @param filepath represents the filepath.
   * @param currentName represents the current name of the file being save.
   */
  void saveEvent(String filepath, String currentName);

  /**
   * Blurring the image, and updates the view.
   */
  void blurEvent(String model, String newName);

  /**
   * Sharpening the image, and updates the view.
   */
  void sharpenEvent(String model, String newName);

  /**
   * Brighten the image or darken it with the given level of increment and update the view.
   */
  void brightenEvent(int level, String model, String newName);

  /**
   * Apply sepia operation on the image and update the view.
   */
  void sepiaEvent(String model, String newName);

  /**
   * Apply the greyscale operation on the image and update the view.
   */
  void greyscaleEvent(String model, String newName);

  /**
   * Apply the greyscale operation by using components stated by the user and update the view.
   */
  void componentEvent(String model, String newName, String way);

  /**
   * Flip the image according to the way stated by the user and update the view.
   */
  void flipEvent(String model, String newName, String way);

  /**
   * Downsize the image according to the new dimensions stated by the user and update the view.
   */
  void downsizeEvent(String model, String newName, int height, int width);
}
