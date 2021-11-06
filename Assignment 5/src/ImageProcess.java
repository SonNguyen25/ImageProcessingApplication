import java.io.InputStreamReader;

import controller.ImageControllerImpl;
import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import view.ImageViewImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This is the main class for running the whole program. The main class allows for different
 * image file inputs.
 */
public class ImageProcess {

  /**
   * The main class to run the whole program.
   * @param args represents the users' inputs as command line arguments.
   */
  public static void main(String[] args) {
    if (args.length == 0 ) {
      ImageLibrary library = new ImageStorage();
      new ImageControllerImpl(library, new ImageViewImpl(library),
              new InputStreamReader(System.in)).process();

    } else {
      switch (args[0]) {
        case "-help":
        case "-h":
          System.out.println("Image processing program: \n"
                  + "-h or --help    for getting basic instructions on this program. \n"
                  + "-file or -f     for inputting a list of operations and output a file as the " +
                  "commands.");
          break;
        case "-file":
        case "-f":
          File file = new File(args[1]);
          InputStream in = null;
          try {
            in = new FileInputStream(file);
          } catch (IOException e) {
            e.printStackTrace();
          }
          ImageLibrary library = new ImageStorage();
          new ImageControllerImpl(library, new ImageViewImpl(library),
                  new InputStreamReader(in)).process();
          break;
        default:
          System.out.println("Getting help by entering -help!");
          break;
      }
    }
  }
}