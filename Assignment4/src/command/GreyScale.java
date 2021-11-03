package command;

import java.util.List;
import model.image.ImageModel;
import model.storage.ImageLibrary;

/**
 * This is GreyScale class that functions as a command for
 * changing the image into a grey scale image as one of the component.
 */
public class GreyScale implements ImageCommand {
  String name;
  String nameOutput;
  String way;

  /**
   * This is the constructor for the GreyScale that takes in the image name and use the given
   * component to change it into a grey scale and save a new one with the name output
   * in the library.
   * @param name The input image name.
   * @param nameOut The name of the output image that is saved in the storage.
   * @param way The demanded grey-component.
   */
  public GreyScale(String name, String nameOut, String way) {
    this.name = name;
    this.nameOutput = nameOut;
    this.way = way;
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
    if (stringCommands.contains(nameOutput)) {
      System.out.println("The name cannot be the command's name");
      throw new IllegalArgumentException();
    }
    ImageModel model = library.contain(name);
    if (model == null) {
      System.out.println("Cannot find immage");
      throw new IllegalArgumentException("Cannot find immage");
    }
    model.greyScale(way);
    library.put(this.nameOutput, model);
    System.out.println(name + " has been changed successfully as " + way + " component "
            + nameOutput);
  }
}
