package command.loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import model.image.IOModel;
import model.image.ImageModel;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;

/**
 * This is the IOLoader that is compatible with most image types except PPM loading.
 */
public class IOLoader extends AbstractRGBLoader {

  /**
   * This is the constructor for the IOLoader that takes in a string of the file path.
   * @param in The file path.
   */
  public IOLoader(String in) {
    super(in);
  }

  /**
   * This is a method that allows the Loader to load the path's file and return it into
   * as an ImageModel.
   * @return ImageModel that represents the input image file.
   * @throws IllegalStateException When the loading fails, it will occur.
   */
  @Override
  public ImageModel load() throws IllegalStateException {
    File file = new File(this.in);
    BufferedImage image = null;
    try {
      image = ImageIO.read(file);
    } catch (IOException e) {
      System.out.println("Loading FAILED!!!");
      throw new IllegalStateException();
    }

    int width = image.getWidth();
    int height = image.getHeight();

    Pixel[][] pixels = new Pixel[height][width];

    for (int i = image.getMinX(); i < pixels.length; i++) {
      for (int k = image.getMinY(); k < pixels[i].length; k++) {
        int pixel = image.getRGB(k,i);
        int r = (pixel >> 16) & 0xff;
        int g = (pixel >> 8) & 0xff;
        int b = pixel & 0xff;
        int a = (pixel >> 24) & 0xff;
        pixels[i][k] = new EightBitPixelModel(Arrays.asList(r,g,b,a));
      }
    }

    return new IOModel(height, width, 255, pixels);


  }


}
