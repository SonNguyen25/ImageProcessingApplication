import java.io.InputStreamReader;
import java.util.Map;

import controller.ImageControllerImpl;
import model.ImageModel;
import model.PPMModel;
import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import view.PPMView;

public class ImageProcess {
  public static void main(String[] args) {
    ImageLibrary library = new ImageStorage();
    new ImageControllerImpl(library, new PPMView(library), new InputStreamReader(System.in)).process();
  }
}
