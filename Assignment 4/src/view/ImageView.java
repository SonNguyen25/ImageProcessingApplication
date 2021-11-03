package view;

import java.io.IOException;

/**
 * This interface should visualize the model into a writable file and send messages for any possible
 * error outputs.
 */
public interface ImageView {
  /**
   * This method returns a string with the transformation of the input file into a writable ppm
   * file.
   * @param fileName The input file name.
   * @return A string of the model.
   */
  String toString(String fileName);

  /**
   * This method is to render message to the users for giving information about the program or any
   * errors.
   * @param message The message that needs to be transmitted.
   * @throws IOException When the rendering fails, it will occur.
   */
  void renderMessage(String message) throws IOException;
}
