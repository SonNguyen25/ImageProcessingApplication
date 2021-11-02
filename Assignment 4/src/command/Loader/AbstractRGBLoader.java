package command.Loader;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class AbstractRGBLoader implements Loader{
  private String in;

  public AbstractRGBLoader(String in) throws IllegalStateException{
    if (in == null) {
      throw new IllegalStateException();
    }
    this.in = in;
  }

  @Override
  public Pixel[][] load() {

    Scanner scanner = null;
    File file = new File(this.in);
    try {
      scanner = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      System.out.println("File "+ file + " not found!");
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
    Pixel[][] imageBoard = new Pixel[width][height];

    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int r = scanner.nextInt();
        int g = scanner.nextInt();
        int b = scanner.nextInt();
        Pixel[i][j] = new int[]{r,g,b};
      }
    }
    return imageBoard;
  }
}
