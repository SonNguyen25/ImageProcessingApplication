package command;

import command.ImageCommand;
import command.Loader.Loader;
import command.Loader.PPMLoader;
import model.image.ImageModel;
import model.storage.ImageLibrary;

public class PPMLoad extends AbstractLoad {
  String path;
  String fileName;

  public PPMLoad(String path, String fileName) {
    super(path, fileName);

  }



  @Override
  public void go(ImageLibrary library) throws IllegalStateException{
    Loader image = new PPMLoader(this.path);
    ImageModel model = null;
    try {
      model = image.load();
      library.put(this.fileName, model);
    } catch (IllegalStateException e) {
      throw new IllegalStateException("Loading fails: Incorrect Path！");
    }
  }
}
