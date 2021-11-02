package command;

import java.util.HashMap;

import model.ImageModel;
import model.storage.ImageLibrary;

public interface ImageCommand {
  void go(ImageLibrary library) throws IllegalStateException;
}
