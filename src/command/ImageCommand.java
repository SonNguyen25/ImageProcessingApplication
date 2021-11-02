package command;


import model.storage.ImageLibrary;

public interface ImageCommand {
  void go(ImageLibrary library) throws IllegalStateException;
}
