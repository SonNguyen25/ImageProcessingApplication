package command;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import javax.imageio.ImageIO;

import model.image.ImageModel;
import model.storage.ImageLibrary;
import view.ImageViewImpl;

/**
 * This is Save class that functions as a command for saving the image into the given system path.
 */
public class Save implements ImageCommand {
  private final String savingPath;
  private final String fileNameIn;

  /**
   * This is the constructor for the Save that takes in the image name and return an image file
   * with the given output path to the computer.
   *
   * @param savingPath The output image path.
   * @param fileNameIn The input image name.
   */
  public Save(String savingPath, String fileNameIn) {
    this.savingPath = savingPath;
    this.fileNameIn = fileNameIn;
  }

  /**
   * This method is used to execute the save command in the model to save the given image in the
   * library to a system output path.
   *
   * @param library        The library that stores all models.
   * @param stringCommands The list of command's names.
   * @throws IllegalArgumentException When the command fails, it will occur.
   */
  @Override
  public void process(ImageLibrary library, List<String> stringCommands)
          throws IllegalArgumentException {
    try {
      ImageModel model = library.contain(fileNameIn);
      if (model == null) {
        throw new IllegalArgumentException("Cannot find image");
      }
      try {
        int end = this.savingPath.lastIndexOf(".");
        String fileType = savingPath.substring(end + 1);
        String[] typesSupported = ImageIO.getReaderFormatNames();
        boolean isSupported = false;
        for (int i = 0; i < typesSupported.length; i++) {
          if (fileType.equalsIgnoreCase(typesSupported[i]) ||
                  fileType.equalsIgnoreCase("ppm")) {
            isSupported = true;
          }
        }

        try {
          if (!isSupported) {
            throw new IllegalStateException();
          }
        } catch (IllegalStateException e) {
          System.out.println("Save file type is not supported!");
        }

        if (fileType.equalsIgnoreCase("ppm")) {
          File out = new File(this.savingPath);
          FileOutputStream imageOut = new FileOutputStream(out);
          imageOut.write(new ImageViewImpl(library).toString(this.fileNameIn).getBytes());
          imageOut.flush();
          imageOut.close();
          System.out.println(fileNameIn + " has been saved successfully");
        } else {
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
            System.out.println(fileNameIn + " has been saved successfully");
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
            System.out.println(fileNameIn + " has been saved successfully");
          }


          try {
            ImageIO.write(newImage, fileType, new File(this.savingPath));
          } catch (IOException e) {
            throw new IllegalStateException("Saving fails!!!");
          }
        }


      } catch (IllegalStateException | IOException e) {
        System.out.println("Saving fails!!!");
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Cannot find image");
    }
  }
}
