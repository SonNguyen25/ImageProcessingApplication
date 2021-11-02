package command;

import java.util.HashMap;

import command.Loader.Loader;
import command.Loader.PPMloader;

public class PPMLoad implements ImageCommand{
  String path;
  String fileName;

  public PPMLoad(String path, String fileName) {
    this.path = path;
    this.fileName = fileName;

  }

  @Override
  public void go(HashMap<String, ImageModel> library) {
    Loader image = new PPMloader(this.path);
    ImageModel model = new ImageModel(image.load());
    library.put(this.fileName, model);
  }
}
