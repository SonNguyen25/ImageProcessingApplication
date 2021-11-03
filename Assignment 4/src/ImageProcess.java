import java.io.InputStreamReader;

import controller.ImageControllerImpl;
import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import view.ImageViewImpl;

/**
 * This is the main class for running the whole program. The main class allows for different
 * image file inputs.
 */
public class ImageProcess {
  /**
   * The main class to run the whole program.
   * @param args The users' inputs.
   */
  public static void main(String[] args) {
    ImageLibrary library = new ImageStorage();
    new ImageControllerImpl(library, new ImageViewImpl(library),
            new InputStreamReader(System.in)).process();
  }
}
