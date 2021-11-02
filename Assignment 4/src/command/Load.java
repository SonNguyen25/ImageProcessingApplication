package command;

import command.Loader.Loader;
import command.Loader.PPMloader;
import model.ImageModel;
import model.storage.ImageLibrary;

public class Load implements ImageCommand{
  String path;
  String fileName;

  public Load(String path, String fileName) {
    this.path = path;
    this.fileName = fileName;

  }

  @Override
  public void go(ImageLibrary library) throws IllegalStateException{
    String fileType;
    int startPos = this.path.indexOf(".");
    fileType = path.substring(startPos + 1);
    System.out.println("Input File Type: " + fileType);
    Loader image;
    if (fileType.equalsIgnoreCase("ppm")) {
      image = new PPMloader(this.path);
    } else {
      image = null; // placeholder for other type of image.
    }

    ImageModel model = null;
    try {
      model = image.load();
      library.put(this.fileName, model);
    } catch (Exception e) {
      throw new IllegalStateException("Loading fails: Incorrect Path！");
    }
  }
}
