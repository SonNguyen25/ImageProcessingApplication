import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import controller.ImageGUIControllerImpl;
import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import view.ImageView;
import view.ImageViewGUIImpl;
import view.ImageViewImpl;
import view.ViewGUI;

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
    ImageLibrary library = new ImageStorage();
    ImageView view = new ImageViewImpl(library, System.out);

    if (args.length == 0 ) {
      ViewGUI viewGUI = new ImageViewGUIImpl(library);
      ImageController program = new ImageGUIControllerImpl(library, viewGUI);
      program.process();
    } else {
      switch (args[0]) {
        case "-text":
          new ImageControllerImpl(library, view,
                  new InputStreamReader(System.in)).process();
          break;
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
          new ImageControllerImpl(library, view,
                  new InputStreamReader(in)).process();
          break;
        default:
          System.out.println("Get help by entering -help!");
          break;
      }
    }
  }
}