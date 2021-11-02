package command;

import java.util.HashMap;

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
  public void go(HashMap<String, ImageModel> library) {
    ImageModel model = library.getOrDefault(fileNameIn, null);
    if (model == null) {
      throw new IllegalStateException("Cannot find immage");
    }

    model.brighten(this.Brightness);
    library.put(this.fileNameOut, model);
  }
}
