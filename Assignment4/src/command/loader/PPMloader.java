package command.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import model.image.ImageModel;
import model.image.PPMModel;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;

/**
 * This is the PPMLoader that is compatible with PPM image loading (for images
 * that only have 3 channels r, g, b).
 */
public class PPMloader extends AbstractRGBLoader {
  private String in;

  /**
   * This is the constructor for the PPMLoader that takes in a string of the file path.
   * @param in The file path.
   */
  public PPMloader(String in) {
    super(in);
    this.in = in;
  }

  /**
   * This is an abstract method that allows the Loader to load the path's file and return it into
   * as an ImageModel.
   * @return ImageModel that represents the input image file.
   * @throws IllegalStateException When the loading fails, it will occur.
   */
  @Override
  public ImageModel load() throws IllegalStateException {

    Scanner scanner = null;
    File file = new File(this.in);
    try {
      scanner = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      System.out.println("File " + file + " not found!");
      throw new IllegalStateException("File " + file + " not found!");
    }


    StringBuilder builder = new StringBuilder();

    while (scanner.hasNextLine()) {
      String s = scanner.nextLine();
      try {
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      } catch (Exception e) {
        throw new IllegalStateException();
      }

    }
    scanner = new Scanner(builder.toString());

    String token;

    token = scanner.next();

    if (!token.equals("P3")) {
      throw new IllegalStateException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = scanner.nextInt();
    int height = scanner.nextInt();
    int maxValue = scanner.nextInt();
    Pixel[][] imageBoard = new Pixel[height][width];

    for (int i = 0; i < imageBoard.length; i++) {
      for (int j = 0; j < imageBoard[i].length; j++) {
        int r = scanner.nextInt();
        int g = scanner.nextInt();
        int b = scanner.nextInt();
        if (r < 0 || r > 255 || g < 0 || g > 255 || g < 0 || g > 255 ) {
          System.out.println("Destructed file, try another one!");
          throw new IllegalStateException("Destructed file, try another one!");
        }
        imageBoard[i][j] = new EightBitPixelModel(Arrays.asList(r,g,b));
      }
    }
    return new PPMModel(height, width, imageBoard);
  }
}
