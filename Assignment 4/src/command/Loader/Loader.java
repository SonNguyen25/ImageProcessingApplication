package command.Loader;

import model.image.ImageModel;

public interface Loader {
  ImageModel load() throws IllegalStateException;
}
