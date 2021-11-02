package command;

import java.util.HashMap;

public class FlipVertical implements ImageCommand{
  String fileNameIn;
  String fileNameOut;

  public FlipVertical(String fileNameIn, String fileNameOut) {
    this.fileNameIn = fileNameIn;
    this.fileNameOut = fileNameOut;
  }

  @Override
  public void go(HashMap<String, ImageModel> library) {
    ImageModel model = library.getOrDefault(fileNameIn, null);
    if (model == null) {
      throw new IllegalStateException("Cannot find immage");
    }

    model.flip("vertical");
    library.put(this.fileNameOut, model);
  }
}
