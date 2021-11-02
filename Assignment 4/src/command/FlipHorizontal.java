package command;

import model.image.ImageModel;
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
      throw new IllegalStateException("Cannot find image");
    }
    model.flip("horizontal");
    library.put(this.fileNameOut, model);
  }
}
