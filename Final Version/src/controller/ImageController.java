package controller;

/**
 * This is the controller of the whole Image processing program. It plays the role as connecting the
 * information between the model and the view classes. It allows the users to input command to
 * change the image and save it.
 */
public interface ImageController {

  /**
   * This is the execution method of the whole controller.
   * @throws IllegalStateException When the input causes error.
   */
  void process() throws IllegalStateException;


}
