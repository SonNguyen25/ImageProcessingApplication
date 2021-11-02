package command.Loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import model.image.ImageModel;
import model.image.PPMModel;
import model.pixel.EightBitPixelModel;
import model.pixel.Pixel;

public class PPMLoader extends AbstractRGBLoader {

  public PPMLoader(String in) {
    super(in);
  }

  @Override
  public ImageModel load() throws IllegalStateException {

    Scanner scanner = null;
    File file = new File(this.in);
    try {
      scanner = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File "+ file + " not found!");
    }


    StringBuilder builder = new StringBuilder();

    while(scanner.hasNextLine()) {
      String s = scanner.nextLine();
      try {
        if (s.charAt(0)!='#') {
          builder.append(s+System.lineSeparator());
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
        imageBoard[i][j] = new EightBitPixelModel(Arrays.asList(r,g,b));
      }
    }
    return new PPMModel(height, width, maxValue, imageBoard);
  }
}
