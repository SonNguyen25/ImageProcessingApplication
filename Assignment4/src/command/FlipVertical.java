package command;

import java.util.List;

import model.image.ImageModel;
import model.storage.ImageLibrary;

/**
 * This is FlipHorizontal class that functions as a command for
 * flip the image vertically.
 */
public class FlipVertical implements ImageCommand {
  String fileNameIn;
  String fileNameOut;

  /**
   * This is the constructor for the FlipVertical that takes in the image name and vertically
   * flip the name in the library and save a new one with the name output in the library.
   * @param fileNameIn The input image name.
   * @param fileNameOut The output image name.
   */
  public FlipVertical(String fileNameIn, String fileNameOut) {
    this.fileNameIn = fileNameIn;
    this.fileNameOut = fileNameOut;
  }

  /**
   * This method is used to execute the vertically flipping command in the model to flip
   * the image vertically.
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
      System.out.println("Cannot find immage");
      throw new IllegalArgumentException("Cannot find immage");
    }
    model.flip("vertical");
    library.put(this.fileNameOut, model);
    System.out.println(fileNameIn + " has been vertically flipped as " + fileNameOut);
  }
}
