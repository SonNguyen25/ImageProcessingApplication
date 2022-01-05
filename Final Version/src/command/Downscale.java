package command;

import java.util.List;

import model.image.IOModel;
import model.image.ImageModel;
import model.operations.Operations;
import model.storage.ImageLibrary;

/**
 * This is Brighten class that functions as a command for increasing the brightness of the image.
 */
public class Downscale implements ImageCommand {
  private final int height;
  private final int width;
  private final String fileNameIn;
  private final String fileNameOut;

  /**
   * This is the constructor for the downscale that takes in the size of the new image
   * and output the new image into the library.
   *
   * @param height      The new Image Height.
   * @param width       The new Image Width.
   * @param fileNameIn  The Input Image Name.
   * @param fileNameOut The Output Image Name.
   */
  public Downscale(int height, int width, String fileNameIn, String fileNameOut) {
    this.height = height;
    this.width = width;
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
      model.getUpdated(Operations.OperationType.Downscale, height, String.valueOf(width), null);
      ImageModel newModel = new IOModel(height, width, 255, model.getPixels());
      library.put(this.fileNameOut, newModel);
      System.out.println(fileNameIn + " has been downscale into an image of "
              + "height: " + height + ", width: " + width + " as " + fileNameOut);
    } catch (IllegalArgumentException e) {
      System.out.println("Cannot find image (Name cannot be the command's name)");
    }
  }
}
