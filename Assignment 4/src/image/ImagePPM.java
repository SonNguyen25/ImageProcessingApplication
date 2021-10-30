package image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import image.Image;

public class ImagePPM implements Image {
  private int width;
  private int height;
  private List<int[][]> rgb;

  public ImagePPM(String filename) throws FileNotFoundException {
//    this.rgb = new ArrayList<int[][]>();
    this.readImage(filename);
  }

  @Override
  public void readImage(String filename) throws FileNotFoundException {
    Scanner sc;
    sc = new Scanner(new FileInputStream(filename));
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    this.width = sc.nextInt();
    this.height = sc.nextInt();
    int maxValue = sc.nextInt();
    int[][] red = new int[height][width];
    int[][] green = new int[height][width];
    int[][] blue = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        red[i][j] = sc.nextInt();
        green[i][j] = sc.nextInt();
        blue[i][j] = sc.nextInt();
      }
    }
    this.rgb = new ArrayList<int[][]>(Arrays.asList(red, green, blue));
  }


  @Override
  public int width() {
    return this.width;
  }

  @Override
  public int height() {
    return this.height;
  }

}
