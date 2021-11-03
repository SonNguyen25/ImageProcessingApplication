package command;

import java.util.List;
import model.image.ImageModel;
import model.storage.ImageLibrary;

public class GreyScale implements ImageCommand {
  String name;
  String nameOutput;
  String way;
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
  public void go(ImageLibrary library, List<String> stringCommands)
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
    System.out.println(name + " has been changed successfully as " + way + " component " +
            nameOutput);
  }
}
