package command;

import java.util.List;

import model.image.ImageModel;
import model.operations.Operations;
import model.storage.ImageLibrary;

/**
 * This is GreyScale class that functions as a command for
 * changing the image into a grey scale image as one of the component.
 */
public class GreyScale implements ImageCommand {
  private final String name;
  private final String nameOutput;
  private final String way;
  private final String mask;

  /**
   * This is the constructor for the GreyScale that takes in the image name and use the given
   * component to change it into a grey scale based on the masked image and save a new one
   * with the name output in the library.
   *
   * @param name    The input image name.
   * @param nameOut The name of the output image that is saved in the storage.
   * @param way     The demanded grey-component.
   * @param mask    The masked image.
   */
  public GreyScale(String name, String mask, String nameOut, String way) {
    this.name = name;
    this.nameOutput = nameOut;
    this.way = way;
    this.mask = mask;
  }

  /**
   * This is the constructor for the GreyScale that takes in the image name and use the given
   * component to change it into a grey scale and save a new one with the name output
   * in the library.
   *
   * @param name    The input image name.
   * @param nameOut The name of the output image that is saved in the storage.
   * @param way     The demanded grey-component.
   */
  public GreyScale(String name, String nameOut, String way) {
    this.name = name;
    this.nameOutput = nameOut;
    this.way = way;
    this.mask = "";
  }

  /**
   * This method is used to execute the vertically flipping command in the model to flip
   * the image vertically.
   *
   * @param library        The library that stores all models.
   * @param stringCommands The list of command's names.
   * @throws IllegalArgumentException When the command fails, it will occur.
   */
  @Override
  public void process(ImageLibrary library, List<String> stringCommands)
          throws IllegalArgumentException {
    try {
      if (stringCommands.contains(nameOutput)) {
        throw new IllegalArgumentException("The name cannot be the command's name");
      }
      ImageModel model = library.contain(name);
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
      model.getUpdated(Operations.OperationType.GreyScale, 0, way,
              (!(mask.equals(""))) ? maskedImage : null);
      library.put(this.nameOutput, model);
      System.out.println(name + " has been changed successfully as " + way + " component "
              + nameOutput);
    } catch (IllegalArgumentException e) {
      System.out.println("Cannot find image/masked image (Name cannot be the command's name)");
    }
  }
}
