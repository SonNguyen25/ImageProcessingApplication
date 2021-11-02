package command.Loader;

import model.image.ImageModel;

public interface Loader<T> {
  ImageModel load() throws IllegalStateException;
}
