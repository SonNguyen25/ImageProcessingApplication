package command.Loader;

import model.image.ImageModel;
import model.image.PPMModel;
import model.pixel.Pixel;


public class PPMLoader extends AbstractRGBLoader {
  public PPMLoader(String in) {
    super(in);
  }

  @Override
  protected ImageModel returnModel(int height, int width, int maxValue, Pixel[][] imageBoard) {
    return new PPMModel(height, width, maxValue, imageBoard);
  }

}
