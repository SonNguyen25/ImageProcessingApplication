package model;


import java.awt.Color;
import java.awt.image.BufferedImage;

import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import model.image.ImageModel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method
 * as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
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
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Convert image models into Buffered Image.
   *
   * @param fileType represents the filetype.
   * @param model    represents the model of the image.
   * @return a buffered image correspondingly.
   */
  public static BufferedImage toBufferedImage(String fileType, ImageModel model) {
    BufferedImage newImage = null;
    if (fileType.equalsIgnoreCase("png")) {
      newImage = new BufferedImage(model.getWidth(), model.getHeight(),
              BufferedImage.TYPE_4BYTE_ABGR);
      for (int i = 0; i < model.getHeight(); i++) {
        for (int k = 0; k < model.getWidth(); k++) {
          List<Integer> rgb = model.getPixels()[i][k].getColor();
          Color color = null;
          if (rgb.size() < 4) {
            color = new Color(rgb.get(0), rgb.get(1), rgb.get(2));
          } else {
            color = new Color(rgb.get(0), rgb.get(1), rgb.get(2), rgb.get(3));
          }

          newImage.setRGB(k, i, color.getRGB());
        }
      }
    } else {
      newImage = new BufferedImage(model.getWidth(), model.getHeight(),
              BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < model.getHeight(); i++) {
        for (int k = 0; k < model.getWidth(); k++) {
          List<Integer> rgb = model.getPixels()[i][k].getColor();
          Color color;
          if (rgb.size() < 4) {
            color = new Color(rgb.get(0), rgb.get(1), rgb.get(2));
          } else {
            if (rgb.get(3) == 255) {
              color = new Color(rgb.get(0), rgb.get(1), rgb.get(2));
            } else {
              color = Color.white;
            }
          }
          newImage.setRGB(k, i, color.getRGB());
        }
      }
    }
    return newImage;
  }

  /**
   * A demo main.
   * @param args represents arguments.
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}

