package command;

import java.util.HashMap;

import model.ImageModel;
import model.storage.ImageLibrary;

public class FlipVertical implements ImageCommand{
  String fileNameIn;
  String fileNameOut;

  public FlipVertical(String fileNameIn, String fileNameOut) {
    this.fileNameIn = fileNameIn;
    this.fileNameOut = fileNameOut;
  }

  @Override
  public void go(ImageLibrary library) throws IllegalStateException{
    ImageModel model = library.contain(fileNameIn);
    if (model == null) {
      throw new IllegalStateException("Cannot find immage");
    }
    model.flip("vertical");
    library.put(this.fileNameOut, model);
  }
}
