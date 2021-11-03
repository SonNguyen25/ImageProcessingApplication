package command;

import java.util.List;

import command.Loader.Loader;
import command.Loader.PPMloader;
import model.image.ImageModel;
import model.storage.ImageLibrary;

public class Load implements ImageCommand{
  String path;
  String fileName;

  public Load(String path, String fileName) {
    this.path = path;
    this.fileName = fileName;

  }

  /**
   * This method is used to execute the load command in the model to load an image from a system
   * path and put into the library.
   * @param library The library that stores all models.
   * @param stringCommands The list of command's names.
   * @throws IllegalArgumentException When the command fails, it will occur.
   */
  @Override
  public void go(ImageLibrary library, List<String> stringCommands) throws IllegalStateException{
    if (stringCommands.contains(fileName)) {
      System.out.println("The name cannot be the command's name");
      throw new IllegalArgumentException();
    }
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
      System.out.println(path + " has been loaded successfully as " + fileName);
    } catch (Exception e) {
      System.out.println("Loading fails: Incorrect Path！");
      throw new IllegalStateException("Loading fails: Incorrect Path！");
    }
  }
}
