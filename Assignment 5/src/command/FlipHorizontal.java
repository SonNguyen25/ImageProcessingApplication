package command;

import java.util.List;

import model.image.ImageModel;
import model.storage.ImageLibrary;

/**
 * This is FlipHorizontal class that functions as a command for
 * flip the image horizontally.
 */
public class FlipHorizontal implements ImageCommand {
  String fileNameIn;
  String fileNameOut;

  /**
   * This is the constructor for the FlipHorizontal that takes in the image name and horizontally
   * flip the name in the library and save a new one with the name output in the library.
   * @param fileNameIn The input image name.
   * @param fileNameOut The output image name.
   */
  public FlipHorizontal(String fileNameIn, String fileNameOut) {
    this.fileNameIn = fileNameIn;
    this.fileNameOut = fileNameOut;
  }

  /**
   * This method is used to execute the horizontally flipping command in the model to flip
   * the image horizontally.
   * @param library The library that stores all models.
   * @param stringCommands The list of command's names.
   * @throws IllegalArgumentException When the command fails, it will occur.
   */
  @Override
  public void process(ImageLibrary library, List<String> stringCommands)
          throws IllegalArgumentException {
    if (stringCommands.contains(fileNameOut)) {
      System.out.println("The name cannot be the command's name");
      throw new IllegalArgumentException();
    }
    ImageModel model = library.contain(fileNameIn);
    if (model == null) {
      System.out.println("Cannot find image");
      throw new IllegalArgumentException("Cannot find image");
    }
    model.flip("horizontal");
    library.put(this.fileNameOut, model);
    System.out.println(fileNameIn + " has been horizontally flipped as " + fileNameOut);
  }
}
