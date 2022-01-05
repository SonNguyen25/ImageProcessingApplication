package command;

import java.util.List;

import model.image.ImageModel;
import model.operations.Operations;
import model.storage.ImageLibrary;

/**
 * This is Blur class that functions as a command for blur the image.
 */
public class Blur implements ImageCommand {
  private final String fileNameIn;
  private final String fileNameOut;
  private final String mask;

  /**
   * This is the constructor for the Blur that takes in the image name and blur the image
   * according to the masked image and save a new one with the name output in the library.
   *
   * @param fileNameIn  The input image name.
   * @param fileNameOut The output image name.
   * @param mask        The Masked Image.
   */
  public Blur(String fileNameIn, String mask, String fileNameOut) {
    this.fileNameIn = fileNameIn;
    this.fileNameOut = fileNameOut;
    this.mask = mask;
  }

  /**
   * This is the constructor for the Blur that takes in the image name and blur the image
   * and save a new one with the name output in the library.
   *
   * @param fileNameIn  The input image name.
   * @param fileNameOut The output image name.
   */
  public Blur(String fileNameIn, String fileNameOut) {
    this.fileNameIn = fileNameIn;
    this.fileNameOut = fileNameOut;
    this.mask = "";
  }

  /**
   * This method is used to execute the blur command in the model to blur the image.
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
      ImageModel maskedImage = null;
      if (!mask.equals("")) {
        try {
          maskedImage = library.contain(mask);
        } catch (NullPointerException e) {
          throw new IllegalArgumentException("Can't find Masked Image");
        }
      }
      if (model == null) {
        throw new IllegalArgumentException("Cannot find image");
      }
      model.getUpdated(Operations.OperationType.Blur, 0, "",
              (!(mask.equals(""))) ? maskedImage : null);
      library.put(this.fileNameOut, model);
      System.out.println(fileNameIn + " has been blurred as " + fileNameOut);
    } catch (IllegalArgumentException e) {
      System.out.println("Cannot find image/masked image (Name cannot be the command's name)");
    }
  }
}
