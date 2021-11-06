package command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import model.image.ImageModel;
import model.storage.ImageLibrary;
import view.ImageViewImpl;

/**
 * This is Save class that functions as a command for saving the image into the given system path.
 */
public class Save implements ImageCommand {
  String savingPath;
  String fileNameIn;

  /**
   * This is the constructor for the Save that takes in the image name and return an image file
   * with the given output path to the computer.
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
   * @param library The library that stores all models.
   * @param stringCommands The list of command's names.
   * @throws IllegalArgumentException When the command fails, it will occur.
   */
  @Override
  public void process(ImageLibrary library, List<String> stringCommands)
          throws IllegalArgumentException {
    ImageModel model = library.contain(fileNameIn);
    if (model == null) {
      System.out.println("Cannot find image");
      throw new IllegalArgumentException("Cannot find image");
    }
    try {
      File out = new File(this.savingPath);
      FileOutputStream imageOut = new FileOutputStream(out);
      imageOut.write(new ImageViewImpl(library).toString(this.fileNameIn).getBytes());
      imageOut.flush();
      imageOut.close();
      System.out.println(fileNameIn + " has been saved successfully");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
