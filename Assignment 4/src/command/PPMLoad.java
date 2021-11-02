package command;

import java.util.HashMap;

import command.Loader.Loader;
import command.Loader.PPMloader;
import model.ImageModel;
import model.PPMModel;
import model.storage.ImageLibrary;

public class PPMLoad implements ImageCommand{
  String path;
  String fileName;

  public PPMLoad(String path, String fileName) {
    this.path = path;
    this.fileName = fileName;

  }

  @Override
  public void go(ImageLibrary library) throws IllegalStateException{
    Loader image = new PPMloader(this.path);
    ImageModel model = null;
    try {
      model = image.load();
      library.put(this.fileName, model);
    } catch (IllegalStateException e) {
      throw new IllegalStateException("Loading fails: Incorrect Path！");
    }
  }
}
