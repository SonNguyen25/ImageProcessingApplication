package command;

import java.awt.*;
import java.util.HashMap;

public class FlipHorizontal implements ImageCommand{
  String fileNameIn;
  String fileNameOut;

  public FlipHorizontal(String fileNameIn, String fileNameOut) {
    this.fileNameIn = fileNameIn;
    this.fileNameOut = fileNameOut;
  }

  @Override
  public void go(HashMap<String, ImageModel> library) {
    ImageModel model = library.getOrDefault(fileNameIn, null);
    if (model == null) {
      throw new IllegalStateException("Cannot find immage");
    }

    model.flip("horizontal"s);
    library.put(this.fileNameOut, model);
  }
}
