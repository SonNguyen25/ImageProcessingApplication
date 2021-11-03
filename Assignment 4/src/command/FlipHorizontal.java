package command;

import java.util.List;

import model.image.ImageModel;
import model.storage.ImageLibrary;

public class FlipHorizontal implements ImageCommand{
  String fileNameIn;
  String fileNameOut;

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
  public void go(ImageLibrary library, List<String> stringCommands) throws IllegalArgumentException{
    if (stringCommands.contains(fileNameOut)) {
      System.out.println("The name cannot be the command's name");
      throw new IllegalArgumentException();
    }
    ImageModel model = library.contain(fileNameIn);
    if (model == null) {
      System.out.println("Cannot find immage");
      throw new IllegalArgumentException("Cannot find immage");
    }
    model.flip("horizontal");
    library.put(this.fileNameOut, model);
    System.out.println(fileNameIn + " has been horizontally flipped as " + fileNameOut);
  }
}
