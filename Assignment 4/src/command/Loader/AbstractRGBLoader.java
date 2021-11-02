package command.Loader;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import model.PPMModel;
import model.Pixel.EightBitPixelModel;
import model.Pixel.Pixel;

public abstract class AbstractRGBLoader implements Loader{
  private String in;

  public AbstractRGBLoader(String in) throws IllegalStateException{
    if (in == null) {
      throw new IllegalStateException();
    }
    this.in = in;
  }

  @Override
  public PPMModel load() throws IllegalStateException{

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
      if (s.charAt(0)!='#') {
        builder.append(s+System.lineSeparator());
      }
    }
    scanner = new Scanner(builder.toString());

    String token;

    token = scanner.next();

    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
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
    return new PPMModel(height, width, imageBoard);
  }
}
