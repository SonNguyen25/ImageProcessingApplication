package command;

import java.util.List;

import command.loader.Loader;
import command.loader.PPMLoader;
import model.image.ImageModel;
import model.storage.ImageLibrary;

/**
 * This is Load class that functions as a command for loading the image into a changeable image as
 * model.
 */
public class Load implements ImageCommand {
  String path;
  String fileName;

  /**
   * This is the constructor for the Load that takes in the image path and create a model for it and
   * save one with the name output in the library.
   * @param path The input image path.
   * @param fileName The output image name.
   */
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
  public void process(ImageLibrary library, List<String> stringCommands)
          throws IllegalStateException {
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
      image = new PPMLoader(this.path);
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
