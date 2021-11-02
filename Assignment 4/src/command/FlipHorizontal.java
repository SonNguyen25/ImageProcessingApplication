package command;

import java.awt.*;
import java.util.HashMap;

import model.ImageModel;
import model.storage.ImageLibrary;

public class FlipHorizontal implements ImageCommand{
  String fileNameIn;
  String fileNameOut;

  public FlipHorizontal(String fileNameIn, String fileNameOut) {
    this.fileNameIn = fileNameIn;
    this.fileNameOut = fileNameOut;
  }

  @Override
  public void go(ImageLibrary library) throws IllegalStateException{
    ImageModel model = library.contain(fileNameIn);
    if (model == null) {
      throw new IllegalStateException("Cannot find immage");
    }
    model.flip("horizontal");
    library.put(this.fileNameOut, model);
  }
}
