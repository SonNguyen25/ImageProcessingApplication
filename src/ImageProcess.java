import java.io.InputStreamReader;



import controller.PPMController;
import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import view.PPMView;

public class ImageProcess {
  public static void main(String[] args) {
    ImageLibrary library = new ImageStorage();
//    if (args[0].endsWith(".ppm")) {
      new PPMController(library, new PPMView(library), new InputStreamReader(System.in)).process();
//    }
  }
}