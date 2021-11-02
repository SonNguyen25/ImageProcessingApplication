import java.io.InputStreamReader;

import controller.ImageControllerImpl;
import model.storage.ImageLibrary;
import model.storage.ImageStorage;
import view.Viewer;

public class ImageProcess {
  public static void main(String[] args) {
    ImageLibrary library = new ImageStorage();
    new ImageControllerImpl(library, new Viewer(library), new InputStreamReader(System.in)).process();
  }
}
