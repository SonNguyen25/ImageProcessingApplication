package command;

import command.Loader.Loader;
import command.Loader.PPMLoader;
import model.image.ImageModel;
import model.storage.ImageLibrary;

public abstract class AbstractLoad implements ImageCommand {
  String path;
  String fileName;

  public AbstractLoad(String path, String fileName) {
    this.path = path;
    this.fileName = fileName;

  }

  public abstract void go(ImageLibrary library) throws IllegalStateException;
}
