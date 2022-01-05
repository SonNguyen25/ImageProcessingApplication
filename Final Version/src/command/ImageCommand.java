package command;

import java.util.List;

import model.storage.ImageLibrary;

/**
 * This is the interface for commands. All commands will implement this interface and the go
 * methods allows the change to be done.
 */
public interface ImageCommand {

  /**
   * This method is used to execute the given command in the model.
   *
   * @param library        The library that stores all models.
   * @param stringCommands The list of command's names.
   * @throws IllegalArgumentException When the command fails, it will occur.
   */
  void process(ImageLibrary library, List<String> stringCommands) throws IllegalArgumentException;
}
