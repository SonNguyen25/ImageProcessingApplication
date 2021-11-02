package command;

import java.util.HashMap;

import model.ImageModel;
import model.storage.ImageLibrary;

public class Brighten implements ImageCommand{
  int Brightness;
  String fileNameIn;
  String fileNameOut;
  public Brighten(int Brightness, String fileNameIn, String fileNameOut) {
    this.Brightness = Brightness;
    this.fileNameIn = fileNameIn;
    this.fileNameOut = fileNameOut;
  }

  @Override
  public void go(ImageLibrary library) throws IllegalStateException{
    ImageModel model = library.contain(fileNameIn);
    if (model == null) {
      throw new IllegalStateException("Cannot find immage");
    }
    model.brighten(this.Brightness);
    library.put(this.fileNameOut, model);
  }
}
