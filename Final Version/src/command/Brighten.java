package command;

import java.util.List;

import model.image.ImageModel;
import model.operations.Operations;
import model.storage.ImageLibrary;

/**
 * This is Brighten class that functions as a command for increasing the brightness of the image.
 */
public class Brighten implements ImageCommand {
  private final int brightness;
  private final String fileNameIn;
  private final String fileNameOut;

  /**
   * This is the constructor for the brighten that takes in the increasing amount of the brightness
   * and which file will be brightened and the name that will be put in the library.
   *
   * @param brightness  Amount of brightness increased.
   * @param fileNameIn  Brighten image file name.
   * @param fileNameOut The name of the changed image that stores in the library.
   */
  public Brighten(int brightness, String fileNameIn, String fileNameOut) {
    this.brightness = brightness;
    this.fileNameIn = fileNameIn;
    this.fileNameOut = fileNameOut;
  }

  /**
   * This method is used to execute the brighten command in the model to increase the brightness.
   *
   * @param library        The library that stores all models.
   * @param stringCommands The list of command's names.
   * @throws IllegalArgumentException When the command fails, it will occur.
   */
  @Override
  public void process(ImageLibrary library, List<String> stringCommands)
          throws IllegalArgumentException {
    try {
      if (stringCommands.contains(fileNameOut)) {
        throw new IllegalArgumentException("The name cannot be the command's name");
      }
      ImageModel model = library.contain(fileNameIn);
      if (model == null) {
        throw new IllegalArgumentException("Cannot find image");
      }
      model.getUpdated(Operations.OperationType.Brighten, brightness, null, null);
      library.put(this.fileNameOut, model);
      System.out.println(fileNameIn + " has been brighten with an amount of "
              + this.brightness + " as " + fileNameOut);
    } catch (IllegalArgumentException e) {
      System.out.println("Cannot find image (Name cannot be the command's name)");
    }
  }
}
