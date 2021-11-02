package command.Loader;

import model.image.ImageModel;



public abstract class AbstractRGBLoader implements Loader {
  protected String in;

  public AbstractRGBLoader(String in) throws IllegalStateException{
    if (in == null) {
      throw new IllegalStateException();
    }
    this.in = in;
  }

  @Override
  public abstract ImageModel load() throws IllegalStateException;

}
